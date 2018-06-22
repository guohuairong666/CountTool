//package com.urun.counttool.support.shiro.realm;
//
//import com.urun.counttool.dao.UserMapper;
//import com.urun.counttool.model.entity.User;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import javax.annotation.Resource;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
///**
// * @author Lei
// * @version 1.0
// */
//public class CustomRealm extends AuthorizingRealm {
//
//    @Resource
//    private UserMapper userMapper;
//
//    public UserMapper getUserMapper() {
//        return userMapper;
//    }
//
//    public void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    // 授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        User user1 = (User) principalCollection.getPrimaryPrincipal();
//        User user = userMapper.selectByUsername(user1.getUsername());
//
//        if (user == null) {
//            return null;
//        }
//        //角色List 获取角色名字并add
//        Set<String> roleSet = new HashSet<>(1);
//        roleSet.add(user.getRole().getName());
//        //权限List
//        List<Permission> permissionList = user.getRole().getPermissionList();
//        //权限的名字Set
//        final Set<String> permissionStringSet = new HashSet<>(4);
//        //循环遍历权限列表  把权限的名字add到权限列表中
//        permissionList.forEach(v -> permissionStringSet.add(v.getName()));
//
//        //授权信息
//        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //添加角色 【setRoles(roleSet)】
//        ((SimpleAuthorizationInfo) authorizationInfo).setRoles(roleSet);
//        //添加权限 【setStringPermissions(permissionStringSet)】
//        ((SimpleAuthorizationInfo) authorizationInfo).setStringPermissions(permissionStringSet);
//        return authorizationInfo;
//    }
//
//    // 认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        String username = (String) authenticationToken.getPrincipal();
//
//        if (username == null) {
//            return null;
//        }
//
//        User user = userMapper.selectByUsername(username);
//        // System.out.println("===>"+user.getRole());
//        // System.out.println("===>"+user.getRole().getPermissionList());
//        if (user == null) {
//            return null;
//        }
//
//        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "customRealm");
//
//        return authenticationInfo;
//    }
//
//}
