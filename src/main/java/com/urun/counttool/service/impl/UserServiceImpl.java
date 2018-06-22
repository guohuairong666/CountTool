package com.urun.counttool.service.impl;
import com.urun.counttool.dao.PermissionMapper;
import com.urun.counttool.dao.RoleMapper;
import com.urun.counttool.dao.UserMapper;
import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.User;
import com.urun.counttool.model.entity.UserRole;
import com.urun.counttool.service.PasswordService;
import com.urun.counttool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private PasswordService passwordService;

	//添加用户，并添加角色
	@Override
	public void addUser(User user, Long... roleIds) {
	//	passwordService.encryptPassword(user);
		userMapper.addUser(user);
		if(roleIds!=null&&roleIds.length>0){
			for(Long roleId:roleIds){
				userMapper.addUserRole(new UserRole(user.getUserId(),roleId));
			}
		}
	}

	//根据用户id,单个删除用户
	@Override
	public boolean deleteUser(Long userId) {
		int isSuccess1 = 0;
		int isSuccess2 = 0;
		try {
			 isSuccess1 = userMapper.deleteUserRole(userId);
			 isSuccess2 = userMapper.deleteUser(userId);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		if (isSuccess1 > 0 && isSuccess2 > 0 ) {
			return true;
		}
		   return false;
	}

	//根据用户id,批量删除用户
	@Override
	public Map<String,String> deleteMoreUsers(Long... userIds) {
		boolean isSuccess = false;
		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		if (userIds != null && userIds.length > 0) {
			for (Long userId : userIds) {
				++i;
				isSuccess = isSuccess = deleteUser(userId);
				if(isSuccess) {
					 map.put("msg"+i,"用户id"+userId+"删除成功");
				}else{
					map.put("msg"+i,"用户id"+userId+"删除失败");
				}
			}
		}
		   return map;
	}

  //根据用户名获得用户
	@Override
	public User getUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}
  //查询所有用户，并包含角色
	@Override
	public List<User> getAllUsers(){
		  return userMapper.findAllUsers();
	}

	//更新用户拥有的角色 并删除旧的角色
	@Override
	public void updateUserRoles(Long userId, Long... roleIds) {
		userMapper.deleteUserRole(userId);
		if (roleIds != null && roleIds.length > 0 ) {
			for (Long roleId:roleIds){
				userMapper.addUserRole(new UserRole(userId,roleId));
			}
		}
	}

	//根据用户名查找所拥有的【角色】
	@Override
	public List<Role> findRolesByUserName(String userName) {
		return userMapper.findRolesByUserName(userName);
	}

	//根据用户名 查找所拥有的角色权限
	@Override
	public Set<String> findPermissionsByUserName(String userName) {
		return new HashSet<String>(userMapper.findPermissionsByUserName(userName));
	}

  @Override
	public List<User>findUsersByRoleName(String roleName){
		return userMapper.findUsersByRoleName(roleName);
	}

}

