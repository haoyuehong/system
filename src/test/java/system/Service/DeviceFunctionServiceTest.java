package system.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import system.dto.DeviceFunctionVO;
import system.dto.DevicePositionVO;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 17:03 2018/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DeviceFunctionServiceTest {
    /*@Autowired
    private DeviceFunctionService deviceFunctionService;
    @Autowired
    private DeviceService deviceService;*/
    /*@Autowired
    private DeviceService deviceService;

    @Test
    public void findByDeviceCode() {
       *//* DeviceFunctionVO functionVO = deviceFunctionService.findByDeviceCode("201832624127");
        System.out.println(functionVO.getHstoryNum());*//*
        List<DevicePositionVO> position = deviceService.position(511421);
        for(DevicePositionVO devicePositionVO : position){
            System.out.println(devicePositionVO.getLnglats().get(0)+"纬度"+devicePositionVO.getLnglats().get(1));
        }
    }*/
}