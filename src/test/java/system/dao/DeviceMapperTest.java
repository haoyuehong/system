package system.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import system.dto.DeviceNumCount;
import system.model.Device;
import system.param.DeviceListParam;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:25 2018/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DeviceMapperTest {

    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    public void findList() {
        DeviceListParam deviceListParam = new DeviceListParam();
        deviceListParam.setGroupId(620100);
        deviceListParam.setDeviceAddress("第一中学");
        deviceListParam.setDeviceName("123");
        List<Device> list = deviceMapper.findList(deviceListParam);
        System.out.println(list.size());
    }

    @Test
    public void findByGroupIds(){
        DeviceListParam deviceListParam = new DeviceListParam();
        //deviceListParam.setDeviceAddress("第一中学");
        deviceListParam.setDeviceName("620100");
        List<Integer> list = new ArrayList<>();
        list.add(620100);
        list.add(130000);
        list.add(130202);

        List<Device> list1 = deviceMapper.findByGroupIds(deviceListParam, list);
        System.out.println(list1.size());

    }

    @Test
    public void countNumByAreaIds(){

    }
}