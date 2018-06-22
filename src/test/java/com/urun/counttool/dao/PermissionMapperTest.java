//package com.urun.counttool.dao;
//
//import com.urun.counttool.SpringJUnit;
//import com.urun.counttool.model.entity.Permission;
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//
//import java.util.Date;
//
///**
// * @author Lei
// * @version 1.0
// */
//public class PermissionMapperTest extends SpringJUnit {
//
//    @Resource
//    private PermissionMapper permissionMapper;
//
//    @Test
//    public void insert() {
//
//        Permission permission = new Permission();
//        permission.setName("INSERT");
//        permission.setDescription("添加");
//        permission.setCreatedDate(new Date());
//        permission.setUpdatedDate(new Date());
//
//        int insert = permissionMapper.insert(permission);
//
//        Assert.assertEquals("插入失败", 1, insert);
//    }
//
//    @Test
//    public void selectById() {
//    }
//
//    @Test
//    public void selectByName() {
//    }
//}