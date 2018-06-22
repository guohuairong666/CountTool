package com.urun.counttool.service;

import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.User;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;
import java.util.Set;
public interface UserService {
	void addUser(User user,Long...roleIds)throws DataAccessException;;//添加用户
	boolean deleteUser(Long userId)throws DataAccessException;;//删除用户
	Map<String,String> deleteMoreUsers(Long...userIds)throws DataAccessException;;//批量删除用户
	User getUserByUserName(String userName)throws DataAccessException;;//根据用户名获取用户
	List<User> getAllUsers();//获取所有用户

	void updateUserRoles(Long userId,Long...roleIds)throws DataAccessException;;//添加用户角色关联
  List<User> findUsersByRoleName(String roleName)throws DataAccessException;;
	List<Role> findRolesByUserName(String userName)throws DataAccessException;;//根据用户名获取用户所有角色
	Set<String> findPermissionsByUserName(String userName)throws DataAccessException;;//根据用户名获取用户所有权限

}
