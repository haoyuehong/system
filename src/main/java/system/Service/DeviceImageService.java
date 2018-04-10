package system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.DeviceImageMapper;
import system.model.DeviceImage;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 15:47 2018/4/10
 */
@Service
public class DeviceImageService {

    @Autowired
    private DeviceImageMapper imageMapper;

    public void save(DeviceImage deviceImage){
        imageMapper.insertSelective(deviceImage);
    }

    public List<DeviceImage> findBydeviceCode(Integer deviceCode,Integer imageType){
        return imageMapper.findBydeviceCode(deviceCode,imageType);
    }
}
