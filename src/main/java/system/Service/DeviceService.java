package system.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.common.JsonData;
import system.dao.AreaMapper;
import system.dao.DeviceMapper;
import system.dao.SchoolMapper;
import system.dto.DeviceNumCount;
import system.dto.DevicePositionVO;
import system.enums.BuildAreaEnum;
import system.model.Area;
import system.model.Device;
import system.model.School;
import system.param.DeviceParam;
import system.utils.CodeGetter;
import system.utils.ExcelUtil;
import system.utils.LatLngUtils;

import java.util.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:56 2018/3/14
 */

@Slf4j
@Service
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private AreaService areaService;

    /**
     * 添加或修改设备
     */
    public void add(Device device){
        if(device.getId() == null){
            deviceMapper.insertSelective(device);
        }else{
            deviceMapper.updateByPrimaryKeySelective(device);
        }
    }

    /**
     *获取设备列表
     */
    public PageInfo<Device> getAll(DeviceParam deviceParam){
        PageHelper.startPage(deviceParam.getPage(), deviceParam.getRows());
        List< Device> deviceList;
        Integer groupId = deviceParam.getGroupId();
        List<Integer> sonAreaIds = areaService.getSonAreaIds(groupId);
        deviceList = deviceMapper.findByGroupIds(deviceParam,sonAreaIds);
        return new PageInfo<>(deviceList);
    }

    /**
     * 删除设备
     * @param deviceId 设备id
     */
    public void delete(Integer deviceId){
        deviceMapper.deleteByPrimaryKey(deviceId);
    }

    /**
     * 根据设备id获取设备信息
     */
    public Device findById(Integer deviceId){
        return deviceMapper.selectByPrimaryKey(deviceId);
    }

    /**
     * 导入设备
     */
    @Transactional
    public JsonData export(String fileName){
        List<Device> deviceList = new ArrayList<>();
        try {
            JsonData export = ExcelUtil.export(fileName, 0, 1);
            Map map = export.toMap();
            if((Integer)map.get("status") == 1){
                Map<String,List<String>> resultMap = (Map<String, List<String>>) map.get("data");
                if(resultMap.isEmpty()){
                    log.error("【设备导入】导入的数据为空");
                    return JsonData.createError("导入的数据为空");
                }else{
                    Iterator<Map.Entry<String, List<String>>> iterator = resultMap.entrySet().iterator();
                    while (iterator.hasNext()){
                        Device device = new Device();
                        Map.Entry<String, List<String>> entry = iterator.next();
                        List<String> value = entry.getValue();
                        JsonData jsonData = LatLngUtils.getLatAndLngByAddress(value.get(2));
                        Map jsonMap = jsonData.toMap();
                        Integer status = (Integer) jsonMap.get("status");
                        if(status == 0){
                            //根据地区名或学校名查询地区id或学校id
                            String blo_school = value.get(5);
                            if(blo_school.replace(" ","").equals("是")){
                                String schoolCode = schoolMapper.findBySchoolName(value.get(0).replace(" ", "")).getSchoolCode();
                                device.setGroupId(Integer.parseInt(schoolCode));
                            }else{
                                Integer areaId = areaMapper.findByAreaName(value.get(0).replace(" ","")).getAreaid();
                                device.setGroupId(areaId);
                            }

                            //设备名
                           if(StringUtils.isNotBlank(value.get(1))){
                               device.setDeviceName(value.get(1).replace(" ",""));
                           }else{
                               log.error("【设备导入】第"+entry.getKey()+"行设备名为空");
                               return JsonData.createError("第"+entry.getKey()+"行设备名为空");
                           }
                           //设备码
                            device.setDeviceCode(CodeGetter.codeGet());
                           //设备地址
                            if(StringUtils.isNotBlank(value.get(2))){
                                device.setDeviceAddress(value.get(2).replace(" ",""));
                            }else{
                                log.error("【设备导入】第"+entry.getKey()+"行地址名为空");
                                return JsonData.createError("第"+entry.getKey()+"行地址名为空");
                            }
                            Map<String, String> data = (Map<String, String>) jsonMap.get("data");
                            if(data.isEmpty()){
                                log.error("【设备导入】第"+entry.getKey()+"行百度没找到这个地址,address={}",value.get(2));
                                return JsonData.createError("糟糕，第"+entry.getKey()+"行百度没找到这个地址");
                            }else{
                                if(StringUtils.isNotBlank(data.get("lat")) || StringUtils.isNotBlank(data.get("lng"))){
                                    device.setDeviceLatitude(data.get("lng")+","+data.get("lat"));
                                }else{
                                    log.error("【设备导入】第"+entry.getKey()+"行百度没找到这个地址,address={}",value.get(2));
                                    return JsonData.createError("糟糕，第"+entry.getKey()+"行百度没找到这个地址");
                                }
                            }
                            device.setBuildTime(new Date());
                            String s = value.get(4);
                            if(StringUtils.isNotBlank(s)){
                                if(s.replace(" ","").equals("是")){
                                    device.setDeviceMap(true);
                                }else{
                                    device.setDeviceMap(false);
                                }
                            }else{
                                log.error("【设备导入】第"+entry.getKey()+"行是否在地图上显示为空");
                                return JsonData.createError("第"+entry.getKey()+"行是否在地图上显示为空");
                            }
                        }
                        deviceList.add(device);
                    }
                    if(deviceList.size() > 0){
                        Boolean br = bachSave(deviceList);
                        if(br == true){
                            return JsonData.createSuccess("恭喜，您成功导入了"+deviceList.size()+"条数据");
                        }else{
                            log.error("【设备导入】写入数据库出错");
                            return JsonData.createSuccess("写入数据库出错");
                        }
                    }else{
                        log.error("【设备导入】表格中无数据");
                        return JsonData.createError("表格中无数据");
                    }
                }
            }
            return export;
        } catch (Exception e) {
            return JsonData.createError("未知错误");
        }
    }

    @Transactional
    public Boolean bachSave(List<Device> deviceList){
        try {
            for(Device device : deviceList){
                deviceMapper.insertSelective(device);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<DeviceNumCount> deviceNumRang(Integer areaId){
        List<DeviceNumCount> list = Lists.newArrayList();
        Area area = areaService.findByAreaId(areaId);
        if(area.getArealevel() < 3){
            //根据地区id查询其子地区
            List<Area> areaList = areaService.findSonAreaByAreaId(areaId);
            for(Area sonArea : areaList){
                //根据地区id统计设备数量
                Integer deviceNum = countDeviceNum(sonArea.getId());
                if(deviceNum>0){
                    DeviceNumCount deviceNumCount = new DeviceNumCount();
                    deviceNumCount.setAreaName(sonArea.getArea());
                    deviceNumCount.setDeviceNum(deviceNum);
                    list.add(deviceNumCount);
                }
            }
        }else{
            List<School> schools = schoolMapper.findByAreaId(areaId);
            for(School school : schools){
                DeviceNumCount deviceNumCount = deviceMapper.countNumBySchoolCodes(school.getSchoolCode());
                list.add(deviceNumCount);
            }
        }
        //排序
        Collections.sort(list, new Comparator<DeviceNumCount>() {
            @Override
            public int compare(DeviceNumCount o1, DeviceNumCount o2) {
                return o2.getDeviceNum() - o1.getDeviceNum();
            }
        });
        return list;
    }

    /**
     * 根据地区id查询设备数量
     * @param groupId
     * @return
     */
    public Integer countDeviceNum(Integer groupId){
        //根据地区id查询其子地区及学校
        List<Integer> sonAreaIds = areaService.getSonAreaIds(groupId);
        //统计这些学校的设备
        if(sonAreaIds.size()>0){
            return deviceMapper.countNumByAreaIds(sonAreaIds);
        }else{
            return 0;
        }
    }

    /**
     * 根据地区查询设备位置
     */
    public List<DevicePositionVO> position(Integer areaId){
        List<Integer> list = areaService.getSonAreaIds(areaId);
        return deviceMapper.findPositionByGroupIds(list);
    }

    /**
     * 根据设备groupId查询设备
     */
    public List<Device> findByGroupId(Integer groupId){
        return deviceMapper.findByGroupId(groupId);
    }




}
