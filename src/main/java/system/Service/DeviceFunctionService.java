package system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.AreaMapper;
import system.dao.DeviceFunctionMapper;
import system.dto.DeviceFunctionVO;
import system.enums.BuildAreaEnum;
import system.model.Area;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 14:04 2018/3/27
 */
@Service
public class DeviceFunctionService {
    @Autowired
    private DeviceFunctionMapper deviceFunctionMapper;
    @Autowired
    private AreaMapper areaMapper;

    public DeviceFunctionVO findByDeviceCode(String deviceCode){
        return deviceFunctionMapper.findByDeviceCode(deviceCode);
    }

    public List<DeviceFunctionVO> deviceFunctionCount(String buildType,Integer groupId){
        List<DeviceFunctionVO> deviceFunctionVOS;
        if(buildType.equals(BuildAreaEnum.SCHOOL.getType())){
            deviceFunctionVOS = deviceFunctionMapper.deviceFunctionCountBySchoolCode(groupId.toString());
        }else{
            deviceFunctionVOS = deviceFunctionMapper.deviceFunctionCountByAreaId(groupId);
        }
        return deviceFunctionVOS;
    }


}
