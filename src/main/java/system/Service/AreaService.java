package system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.AreaMapper;
import system.dao.SchoolMapper;
import system.model.Area;

import java.util.List;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:09 2018/4/8
 */
@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 根据地区id获取其子地区及学校id
     */
    public List<Integer> getSonAreaIds(Integer groupId){
        //查询该地区的子地区id和学校id
        List<Integer> sonAreaIds = areaMapper.findSonArea(groupId);
        sonAreaIds.add(groupId);
        //根据地区id查询管辖学校
        List<Integer> schoolIds = schoolMapper.findByAreaIds(sonAreaIds);
        sonAreaIds.addAll(schoolIds);
        return sonAreaIds;
    }

    public List<Area> findSonAreaByAreaId(Integer areaId){
       return  areaMapper.findByParentId(areaId);
    }

    public Area findByAreaId(Integer areaId){
        return areaMapper.findByAreaId(areaId);
    }
}
