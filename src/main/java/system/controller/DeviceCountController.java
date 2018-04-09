package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.Service.DeviceFunctionService;
import system.common.JsonData;
import system.dto.DeviceFunctionVO;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 13:57 2018/3/27
 */
@RestController
@RequestMapping("/sys/deviceRun")
public class DeviceCountController {

    @Autowired
    private DeviceFunctionService functionService;


    /**
     * 根据设备码获取设备基本信息和设备运行时长以及参与人数
     */
    @RequestMapping("/function.do")
    public JsonData deviceFunction(String deviceCode){
        DeviceFunctionVO functionVO = functionService.findByDeviceCode(deviceCode);
        return JsonData.createSuccess(functionVO);
    }

    /**
     * 根据地区或学校查询设备运行情况

    @RequestMapping("/count.do")
    public JsonData deviceFunctionCount(String buildType,Integer groupId ){
        List<DeviceFunctionVO> functionVOS = functionService.deviceFunctionCount(buildType,groupId);
        return JsonData.createSuccess(functionVOS);
    }
     */




}