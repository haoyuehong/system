package system.dao;

import system.model.DeviceImage;

public interface DeviceImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    int insert(DeviceImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    int insertSelective(DeviceImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    DeviceImage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DeviceImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_image
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DeviceImage record);
}