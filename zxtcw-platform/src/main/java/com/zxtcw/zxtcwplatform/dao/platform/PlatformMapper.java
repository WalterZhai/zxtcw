package com.zxtcw.zxtcwplatform.dao.platform;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/6
 */
@Mapper
public interface PlatformMapper {

    @Select(" SELECT name FROM sm_user where login_name=#{username} ")
    String getNameByUsername(@Param("username") String username);
}
