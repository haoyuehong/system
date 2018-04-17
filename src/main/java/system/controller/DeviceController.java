package system.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.Service.DeviceFunctionService;
import system.Service.DeviceImageService;
import system.Service.DeviceService;
import system.common.JsonData;
import system.dto.DeviceNumCount;
import system.dto.DevicePositionVO;
import system.model.Device;
import system.model.DeviceImage;
import system.param.DeviceParam;
import system.utils.BeanValidator;
import system.utils.CodeGetter;
import system.utils.LatLngUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:49 2018/3/23
 */
@Controller
@RequestMapping("/sys/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceImageService deviceImageService;

    /**
     * 新增或保存编辑设备
     * @param device 设备信息
     * @return
     */
    @RequestMapping(value = "/save.do",method = RequestMethod.POST)
    @ResponseBody
    public JsonData save(Device device){
        BeanValidator.check(device);
        device.setBuildTime(new Date());
        JsonData jsonData = LatLngUtils.getLatAndLngByAddress(device.getDeviceAddress());
        if(jsonData.getStatus()  == 1){
            Map<String,BigDecimal> map = (Map<String,BigDecimal>)jsonData.getData();
            BigDecimal lng = map.get("lng");
            BigDecimal lat = map.get("lat");
            device.setDeviceLatitude(lng+","+lat);
        }
        device.setDeviceCode(CodeGetter.codeGet());
        device.setDeviceMap(false);
        deviceService.add(device);
        return JsonData.createSuccess("添加成功");
    }

    /**
     * 删除设备
     */
    @RequestMapping(value = "/{id}.do",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonData delete(@PathVariable("id") Integer id){
        deviceService.delete(id);
        return JsonData.createSuccess("删除成功");
    }

    /**
     * 查询设备
     */
    @RequestMapping(value = "/list.do",method = RequestMethod.GET)
    @ResponseBody
    public JsonData list(DeviceParam param){
        BeanValidator.check(param);
        PageInfo<Device> pageInfo = deviceService.getAll(param);
        return JsonData.createSuccess(pageInfo);
    }

    /**
     * 根据地区id获取设备数量排名
     */
    @RequestMapping(value = "/deviceNumRang.do",method = RequestMethod.GET)
    @ResponseBody
    public JsonData deviceNumRang(Integer areaId){
        List<DeviceNumCount> deviceNumCounts = deviceService.deviceNumRang(areaId);
        return JsonData.createSuccess(deviceNumCounts);
    }

    /**
     * 根据地区id或学校id查询设备位置返回设备所在位置经纬度
     */
    @RequestMapping("/position.do")
    @ResponseBody
    public JsonData position(Integer areaId){
        List<DevicePositionVO> position = deviceService.position(areaId);
        return JsonData.createSuccess(position);
    }

    /**
     * 根据设备码和图片类型获取设备图片
     */
    @RequestMapping("/devImage")
    @ResponseBody
    public JsonData image(Integer deviceCode,Integer imageType){
        List<DeviceImage> deviceImages = deviceImageService.findBydeviceCode(deviceCode,imageType);
        return JsonData.createSuccess(deviceImages);
    }
}
