package com.xzq.commubity.mapper;

import com.xzq.commubity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gtmModified},#{avatarUrl})")
    void insert(User user);

//    类的话会自定匹配参数，不是类的话要加@param
    User findByToken(@Param("token") String token);


    User findById(@Param("id") Integer id);
}
