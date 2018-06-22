//package com.urun.counttool.dao;
//
//import java.util.Date;
//
//import com.urun.counttool.SpringJUnit;
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//
///**
// * @author Lei
// * @version 1.0
// */
//public class RoleMapperTest extends SpringJUnit {
//
//    @Resource
//    private RoleMapper roleMapper;
//
//    @Test
//    public void insert() {
//
//        Role role = new Role();
//        role.setName("超级管理员");
//        role.setDescription("系统超级管理员");
//        role.setCreatedDate(new Date());
//        role.setUpdatedDate(new Date());
//
//        int insert = roleMapper.insert(role);
//
//        Assert.assertEquals("插入失败", 1, insert);
//    }
//
//    @Test
//    public void insertRelativity() {
//        RolePermission rolePermission = new RolePermission();
//        Role role = new Role(1);
//        Permission permission = new Permission(1);
//
//        rolePermission.setRole(role);
//        rolePermission.setPermission(permission);
//        rolePermission.setCreatedDate(new Date());
//        rolePermission.setUpdatedDate(new Date());
//
//        int insert = roleMapper.insertRelativity(rolePermission);
//
//        Assert.assertEquals("插入失败", 1, insert);
//    }
//
//    @Test
//    public void selectById() {
//        Role role = roleMapper.selectById(1);
//        System.out.println("role = " + role);
//    }
//
//    @Test
//    public void selectByName() {
//    }
//}