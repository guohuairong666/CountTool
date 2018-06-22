package com.urun.counttool.service;

import com.urun.counttool.model.entity.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;



/**
 *
 * @GuoHuaiRong
 * @角色服务类
 */
public interface RoleService {
	//添加角色  permissionIds:权限的id
	void addRole(Role role, Long... permissionIds) throws DataAccessException;
	//根据角色id删除角色
	void deleteRole(Long roleId)throws DataAccessException;;
	//批量多个角色id批量删除角色
	void deleteMoreRoles(Long... roleIds)throws DataAccessException;;
	//根据角色id 获取角色
	Role getRoleById(Long roleId)throws DataAccessException;;
	//根据用户名名获得 旗下所有角色
	List<Role> getRolesByUserName(String userName)throws DataAccessException;;
	//查询所有角色
	List<Role> getAllRoles();
	//更新角色
	void updateRole(Role role, Long... permIds)throws DataAccessException;;
	//添加 角色——权限的关联 permissionIds：多个权限的id
	int addRolePermissions(Long roleId, Long... permissionIds)throws DataAccessException;;
}
