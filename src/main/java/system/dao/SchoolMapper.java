package system.dao;

import org.apache.ibatis.annotations.Param;
import system.model.School;

import java.util.List;

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

    School findBySchoolName(@Param("schoolName") String schoolName);

    List<Integer> findByAreaIds(@Param("areaIds") List<Integer> areaIds);

    List<School> findByAreaId(@Param("areaId")Integer areaId);

    School findBySchoolCode(@Param("schoolCode")Integer schoolCode);
}