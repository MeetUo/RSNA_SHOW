package cn.rsna.dao;

import cn.rsna.entity.UserPic;
import cn.rsna.entity.UserPicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int countByExample(UserPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int deleteByExample(UserPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer picid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int insert(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int insertSelective(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    List<UserPic> selectByExample(UserPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    UserPic selectByPrimaryKey(Integer picid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserPic record, @Param("example") UserPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserPic record, @Param("example") UserPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userpic
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserPic record);
}