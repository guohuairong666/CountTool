package com.urun.counttool.service.impl;


import com.urun.counttool.dao.RoleMapper;
import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.RolePermission;
import com.urun.counttool.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public void addRole(Role role, Long... permissionIds)throws DataAccessException {

		 roleMapper.addRole(role);
		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
			   roleMapper.addRolePermission(new RolePermission(role.getRoleId(), permissionId));
			}
		}
	}
  //删除角色 并取消用户_角色 角色_权限 关联
	@Override
	public void deleteRole(Long roleId)throws DataAccessException {
		   roleMapper.deleteUserRole(roleId);
			 roleMapper.deleteRolePermission(roleId);
			 roleMapper.deleteRole(roleId);

	}

	@Override
	public void deleteMoreRoles(Long... roleIds)throws DataAccessException {
		if(roleIds!=null&&roleIds.length>0){
			for(Long roleId:roleIds){
				deleteRole(roleId);
			}
		}
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleMapper.findById(roleId);
	}


	//根据用户名查找用户所拥有的角色
	@Override
	public List<Role> getRolesByUserName(String userName)throws DataAccessException {
		return roleMapper.findRolesByUserName(userName);
	}

	@Override
	public List<Role> getAllRoles()throws DataAccessException {
		return roleMapper.findAllRoles();
	}

	@Override
	public void updateRole(Role role,Long...permIds)throws DataAccessException {
		roleMapper.updateRole(role);
		roleMapper.deleteRolePermission(role.getRoleId());
		addRolePermissions(role.getRoleId(),permIds);
	}

	@Override
	public int addRolePermissions(Long roleId, Long... permissionIds) {
		if(permissionIds!=null&&permissionIds.length>0){
			for(Long permissionId:permissionIds){
				roleMapper.addRolePermission(new RolePermission(roleId,permissionId));
			}
		}
		  return 0;
	}

}

