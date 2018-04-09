package system.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 7:50 2018/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SchoolMapperTest {
    @Autowired
    private SchoolMapper schoolMapper;

    @Test
    public void findByAreaIds() {
        List<Integer> list = new ArrayList<>();
        list.add(340000);
        list.add(130000);
        list.add(130202);
        List<Integer> list1 = schoolMapper.findByAreaIds(list);
        System.out.println(list1.size());
    }
}