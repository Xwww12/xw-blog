package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author xw
 * @since 2023-03-14 23:06:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void saveBatchUserRole(@Param("id") Long id, @Param("roleIds") List<String> roleIds);

    List<String> getUserRoles(Long id);

    void deleteUserRoleById(Long id);
}

