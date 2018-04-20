package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import system.Service.AreaService;
import system.Service.DeviceFunctionService;
import system.Service.DeviceService;
import system.common.JsonData;
import system.dto.DeviceFunctionBase;
import system.dto.DeviceFunctionVO;
import system.dto.SiteVO;
import system.model.Device;

import java.util.ArrayList;
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
    @Autowired
    private AreaService areaService;
    @Autowired
    private DeviceService deviceService;


    /**
     * 根据设备码获取设备基本信息和设备运行时长以及参与人数
     */
    @RequestMapping(value = "/function.do",method = RequestMethod.GET)
    public JsonData deviceFunction(String deviceCode){
        DeviceFunctionVO functionVO = functionService.findByDeviceCode(deviceCode);
        return JsonData.createSuccess(functionVO);
    }

    /**
     * 根据地区id获取其下有场馆的地区或学校并获取该场馆内所有设备运行情况并按开机时间降序排序，如果开机时间相同则按参与人次降序排序
     */
    @RequestMapping("/site.do")
    public JsonData deviceFunctionCount(Integer groupId,Integer level){
        List<SiteVO> site = functionService.site(groupId,level);
        /*for(SiteVO siteVO : site){
            List<DeviceFunctionBase> bases = new ArrayList<>();
            List<Device> deviceList = deviceService.findByGroupId(siteVO.getGroupId());
            for(Device device : deviceList){
                DeviceFunctionBase deviceFunctionBase = functionService.findByDeviceCode(device.getDeviceCode());
                bases.add(deviceFunctionBase);
            }
            siteVO.setDeviceFunctionBases(bases);
        }*/
        return JsonData.createSuccess(site);
    }

    /**
     * 根据场馆id获取该场馆内所有设备运行情况并按开机时间降序排序，如果开机时间相同则按参与人次降序排序
     */
    @RequestMapping("/devicefunction.do")
    public JsonData devicefunction(Integer groupId){
        List<DeviceFunctionBase> bases = new ArrayList<>();
        List<Device> deviceList = deviceService.findByGroupId(groupId);
        for(Device device : deviceList){
            DeviceFunctionBase deviceFunctionBase = functionService.findFunctionBaseByDeviceCode(device.getDeviceCode());
           if(deviceFunctionBase != null){
               bases.add(deviceFunctionBase);
           }
        }
        return JsonData.createSuccess(bases);
    }
}