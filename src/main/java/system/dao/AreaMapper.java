package system.dao;

import system.model.Area;

public interface AreaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    int insert(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    int insertSelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    Area selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Area record);
}