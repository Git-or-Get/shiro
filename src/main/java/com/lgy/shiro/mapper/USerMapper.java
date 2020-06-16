package com.lgy.shiro.mapper;

import com.lgy.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface USerMapper {
    @Select("select * from user where name = #{name}")
    public User queryUserByName(String name);
}
