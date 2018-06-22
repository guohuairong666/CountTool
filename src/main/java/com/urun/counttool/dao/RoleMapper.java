package com.urun.counttool.dao;

import java.util.List;

import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.RolePermission;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author
 * 角色映射类
 */
public interface RoleMapper {
	//添加角色
	int addRole(Role role)throws DataAccessException;
		//删除角色
		int deleteRole(Long roleId)throws DataAccessException;
		//根据id查找角色
		Role findById(Long roleId)throws DataAccessException;
		//根据用户名查询相应角色
		List<Role> findRolesByUserName(String userName)throws DataAccessException;
		//查找所有角色
		List<Role> findAllRoles()throws DataAccessException;
		//更新角色
		void updateRole(Role role)throws DataAccessException;
		//删除用户角色关联
		int deleteUserRole(Long roleId)throws DataAccessException;
		//删除角色权限关联
		int deleteRolePermission(Long roleId)throws DataAccessException;
		//添加角色权限关联
		int addRolePermission(RolePermission rolePermission)throws DataAccessException;
		}
