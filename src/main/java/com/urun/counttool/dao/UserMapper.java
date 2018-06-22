package com.urun.counttool.dao;

import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.User;
import com.urun.counttool.model.entity.UserRole;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * 用户数据库映射类
 */

public interface UserMapper {
	//添加用户
	void addUser(User user)throws DataAccessException;;
	// 删除用户
	int deleteUser(Long userId)throws DataAccessException;;
  //根据名字查询用户
	User findUserByUserName(String userName)throws DataAccessException;;
	//查询所有用户
	List<User>findAllUsers()throws DataAccessException;;
	//删除用户角色关联
	int deleteUserRole(Long userId)throws DataAccessException;;
	//添加用户角色关联
	void addUserRole(UserRole userRole)throws DataAccessException;;

	//根据【用户名字】查询相应【角色】
	List<Role> findRolesByUserName(String userName)throws DataAccessException;;

	//根据【角色名字】查询所拥有的【用户】
	List<User> findUsersByRoleName(String RoleName)throws DataAccessException;;

	//根据用户名字查询相应权限
	List<String> findPermissionsByUserName(String userName)throws DataAccessException;;
}
