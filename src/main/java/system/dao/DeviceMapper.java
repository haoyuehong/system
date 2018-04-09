package system.dao;

import org.apache.ibatis.annotations.Param;
import system.dto.DeviceNumCount;
import system.model.Device;
import system.param.DeviceListParam;

import java.util.List;

public interface DeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    int insert(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    int insertSelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    Device selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Device record);

    /**
     * 获取设备列表（学校）
     */
    List<Device> findList(@Param("deviceListParam") DeviceListParam deviceListParam);

    /**
     * 获取设备列表（地区）
     */
    List<Device> findByGroupIds(@Param("deviceListParam")DeviceListParam deviceListParam,@Param("sonAreaIds")List<Integer> sonAreaIds);

    /**
     * 根据地区id统计设备数量
     */
    Integer countNumByAreaIds(@Param("areaIds")List<Integer> areaIds);

    /**
     * 根据学校id查询设备数量
     */
    DeviceNumCount countNumBySchoolCodes(@Param("schoolCode")String schoolCode);
}