package com.urun.counttool.service;

import java.util.List;

import com.urun.counttool.model.entity.Permission;
import org.springframework.dao.DataAccessException;

public interface PermissionService {
	Long addPermission(Permission permission) throws DataAccessException;
	void deletePermission(Long permissionId)throws DataAccessException;
	void deleteMorePermissions(Long... permIds)throws DataAccessException;
	Permission findById(Long permId)throws DataAccessException;
	List<Permission> getPermissionsByRoleId(Long roleId)throws DataAccessException;
	List<Permission> getAllPermissions()throws DataAccessException;
	void updatePermission(Permission permission)throws DataAccessException;
}
