package com.urun.counttool.model.entity;

import java.io.Serializable;

/**
 *
 * @author
 * 角色_	权限类
 */
public class RolePermission implements Serializable{
	private Long roleId;
	private Long permissionId;
	
	public RolePermission(Long roleId,Long permissionId){
		this.roleId=roleId;
		this.permissionId=permissionId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}
