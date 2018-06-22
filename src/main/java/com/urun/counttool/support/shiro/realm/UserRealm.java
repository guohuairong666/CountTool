package com.urun.counttool.support.shiro.realm;

import com.urun.counttool.model.entity.User;
import com.urun.counttool.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author
 * @用户realm
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findPermissionsByUserName(userName));
		authorizationInfo.setStringPermissions(userService.findPermissionsByUserName(userName));
		
		return authorizationInfo;
	}

	//认证
	@Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String userName = (String)token.getPrincipal();
    User user = userService.getUserByUserName(userName);
    if (user == null) {
      throw new UnknownAccountException();
    }
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        user.getUserName(),
        user.getPassword(),
        getName());
    return authenticationInfo;
  }

}
