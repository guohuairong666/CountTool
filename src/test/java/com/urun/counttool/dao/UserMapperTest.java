//package com.urun.counttool.dao;
//
//import com.urun.counttool.SpringJUnit;
//import com.urun.counttool.model.entity.Role;
//import com.urun.counttool.model.entity.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//
///**
// * @author Lei
// * @version 1.0
// */
//public class UserMapperTest extends SpringJUnit {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void insert() {
//       User user = new User();
//       user.setUsername("guohuairong666");
//       user.setPassword("123");
//       Role role = new Role();
//       role.setId(2);
//       user.setRole(role);
//       userMapper.addUser(user);
//    }
//
////    @Test
////    public void insertRelativity() {
////        UserRole userRole = new UserRole();
////        User user = new User(1);
////        Role role = new Role(1);
////
////        userRole.setUser(user);
////        userRole.setRole(role);
////        userRole.setCreatedDate(new Date());
////        userRole.setUpdatedDate(new Date());
////
////        int insert = userMapper.insertRelativity(userRole);
////
////        Assert.assertEquals("插入失败", 1, insert);
////    }
//
//    @Test
//    public void selectById() {
//        System.out.println(userMapper);
//        User user = userMapper.selectById(1);
//        System.out.println("user = " + user);
//
//    }
//
//    @Test
//    public void selectByUsername() {
//        User tom = userMapper.selectByUsername("tom");
//        System.out.println("tom = " + tom);
//    }
//}