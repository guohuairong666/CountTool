package com.urun.counttool.service.impl;

import java.util.List;

import com.urun.counttool.dao.PermissionMapper;
import com.urun.counttool.model.entity.Permission;
import com.urun.counttool.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 *
 * @author
 * 权限服务接口实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;
	
	@Override
	public Long addPermission(Permission permission)throws DataAccessException {
		permissionMapper.addPermission(permission);
		return permission.getPermissionId();
	}

	@Override
	public void deletePermission(Long permissionId)throws DataAccessException {
		permissionMapper.deleteRolePermission(permissionId);
		permissionMapper.deletePermission(permissionId);
	}

	@Override
	public void deleteMorePermissions(Long... permIds)throws DataAccessException {
		if (permIds != null && permIds.length > 0) {
			for (Long permId:permIds){
				deletePermission(permId);
			}
		}
	}

	@Override
	public Permission findById(Long permId)throws DataAccessException {
		return permissionMapper.findById(permId);
	}

	@Override
	public List<Permission> getPermissionsByRoleId(Long roleId)throws DataAccessException {
		return permissionMapper.findPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> getAllPermissions()throws DataAccessException {
		return permissionMapper.findAllPermissions();
	}

	@Override
	public void updatePermission(Permission permission)throws DataAccessException {
		permissionMapper.updatePermission(permission);
	}

}
