package system.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import system.model.Device;
import system.param.DeviceParam;

import java.util.ArrayList;
import java.util.List;

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
        DeviceParam deviceParam = new DeviceParam();
        deviceParam.setGroupId(620100);
        deviceParam.setDeviceAddress("第一中学");
        deviceParam.setDeviceName("123");
        List<Device> list = deviceMapper.findList(deviceParam);
        System.out.println(list.size());
    }

    @Test
    public void findByGroupIds(){
        DeviceParam deviceParam = new DeviceParam();
        //deviceParam.setDeviceAddress("第一中学");
        deviceParam.setDeviceName("620100");
        List<Integer> list = new ArrayList<>();
        list.add(620100);
        list.add(130000);
        list.add(130202);

        List<Device> list1 = deviceMapper.findByGroupIds(deviceParam, list);
        System.out.println(list1.size());

    }

    @Test
    public void countNumByAreaIds(){

    }
}