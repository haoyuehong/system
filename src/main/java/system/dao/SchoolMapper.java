package system.dao;

import system.model.School;

public interface SchoolMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    int insert(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    int insertSelective(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    School selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(School record);

    School findBySchoolName(String schoolName);
}