package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.Service.DeviceService;
import system.common.JsonData;
import system.model.Device;
import system.utils.BeanValidator;
import system.utils.LatLngUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:49 2018/3/23
 */
@Controller
@RequestMapping("/sys/device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    /**
     * 新增或保存编辑设备
     * @param device 设备信息
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonData save(Device device){
        device.setBuildTime(new Date());
        JsonData jsonData = LatLngUtils.getLatAndLngByAddress(device.getDeviceAddress());
        Map map = jsonData.toMap();
        if((Integer)map.get("status")  == 0){
            Map<String, BigDecimal> data = (Map<String, BigDecimal>) map.get("data");
            BigDecimal lng = data.get("lng");
        }
        device.setDeviceLatitude();
        BeanValidator.check(device);
        deviceService.add(device);
        return JsonData.createError("添加成功");
    }

    /**
     * 删除设备
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonData delete(@PathVariable(value = "id")Integer id){
        deviceService.delete(id);
        return JsonData.createError("删除成功");
    }

    /**
     * 查询设备
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonData list(){

    }





}
