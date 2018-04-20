package system.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import system.Service.AreaService;
import system.dto.DeviceFunctionVO;
import system.model.DeviceFunction;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 14:52 2018/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DeviceFunctionMapperTest {

    @Autowired
    private DeviceFunctionMapper deviceFunctionMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private AreaService areaService;


    @Test
    public void findByDeviceCode() {
        DeviceFunctionVO functionVO = deviceFunctionMapper.findByDeviceCode("201832624127");
        System.out.println(functionVO.getHstoryNum());
    }

    @Test
    public void getSonArea(){
        List<Integer> sonAreaIds = areaService.getSonAreaIds(514000, 4);
        System.out.println(sonAreaIds.size());
    }
}