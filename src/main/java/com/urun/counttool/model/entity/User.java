package com.urun.counttool.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author
 * 用户类
 */
public class User implements Serializable {
  private Long userId;
  private String userName;
  private String password;
  private String phoneNumber;
  private Date createTime;
  private String isDel;
  private String Remarks;
  private String roleId;
  private String roleName;
  //private String passwordSalt;


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getIsDel() {
    return isDel;
  }

  public void setIsDel(String isDel) {
    this.isDel = isDel;
  }

  public String getRemarks() {
    return Remarks;
  }

  public void setRemarks(String remarks) {
    Remarks = remarks;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", createTime=" + createTime +
        ", isDel='" + isDel + '\'' +
        ", Remarks='" + Remarks + '\'' +
        ", roleId='" + roleId + '\'' +
        ", roleName='" + roleName + '\'' +
        '}';
  }
}

//	public String getPasswordSalt() {
//		return passwordSalt;
//	}
//	public void setPasswordSalt(String passwordSalt) {
//		this.passwordSalt = passwordSalt;
//	}
//}
