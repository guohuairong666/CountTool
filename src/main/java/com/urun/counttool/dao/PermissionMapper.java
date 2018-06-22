package com.urun.counttool.dao;

import com.urun.counttool.model.entity.Permission;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 *
 * @author
 * 权限类
 */
public interface PermissionMapper {
	//添加权限
	void addPermission(Permission permission);
	//根据id删除权限
	void deletePermission(Long permissionId);
	//根据权限id查找权限
	Permission findById(Long permId);
	//根据角色id查询所有权限
	List<Permission> findPermissionsByRoleId(Long roleId)throws DataAccessException;
	//查找所有的权限
	List<Permission> findAllPermissions();
  //更新权限
	void updatePermission(Permission permission);
	//删除角色权限关联
	void deleteRolePermission(Long permissionId);
}
