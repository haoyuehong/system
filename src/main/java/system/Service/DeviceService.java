package system.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.common.JsonData;
import system.dao.DeviceMapper;
import system.model.Device;
import system.param.DeviceListParam;
import system.param.PageParam;
import system.utils.CodeGetter;
import system.utils.ExcelUtil;
import system.utils.LatLngUtils;

import java.util.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:56 2018/3/14
 */
@Service
@Slf4j
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

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
    public PageInfo<Device> getAll(PageParam pageParam,DeviceListParam deviceListParam){
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List< Device> deviceList = deviceMapper.findList(deviceListParam);
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
                            String s = value.get(5);
                            if(StringUtils.isNotBlank(s)){
                                if(s.equals("是")){
                                    device.setDeviceMap((byte) 1);
                                }else{
                                    device.setDeviceMap((byte)0);
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



}
