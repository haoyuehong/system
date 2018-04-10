package system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.AreaMapper;
import system.dao.DeviceFunctionMapper;
import system.dao.DeviceMapper;
import system.dao.SchoolMapper;
import system.dto.DeviceFunctionBase;
import system.dto.DeviceFunctionVO;
import system.dto.DevicePositionVO;
import system.dto.SiteVO;
import system.enums.BuildAreaEnum;
import system.model.Area;
import system.model.Device;
import system.model.DeviceFunction;
import system.model.School;

import java.util.ArrayList;
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
    private AreaService areaService;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    public DeviceFunctionVO findByDeviceCode(String deviceCode){
        return deviceFunctionMapper.findByDeviceCode(deviceCode);
    }

    public List<SiteVO> site(Integer groupId){
        List<SiteVO> siteVOS = new ArrayList<>();
        List<Integer> sonAreaIds = areaService.getSonAreaIds(groupId);
        for(Integer devGroupId : sonAreaIds){
            List<Device> list = deviceMapper.findByGroupId(devGroupId);
            if(list.size() > 0){
                SiteVO siteVO = new SiteVO();
                siteVO.setGroupId(devGroupId);
                School school = schoolMapper.findBySchoolCode(devGroupId);
                if(school != null){
                    siteVO.setSiteName(school.getSchoolName());
                }else{
                    Area area = areaService.findByAreaId(devGroupId);
                    siteVO.setSiteName(area.getArea());
                }
            }
        }
        return siteVOS;
    }

    public void updateDevRun(DeviceFunction function){
        deviceFunctionMapper.updateByPrimaryKeySelective(function);
    }

    public void save(DeviceFunction function){
        deviceFunctionMapper.insertSelective(function);
    }
}
