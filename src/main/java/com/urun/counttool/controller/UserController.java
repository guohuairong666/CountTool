package com.urun.counttool.controller;

import com.alibaba.fastjson.JSONObject;
import com.urun.counttool.model.entity.ResultDto;
import com.urun.counttool.model.entity.Role;
import com.urun.counttool.model.entity.User;
import com.urun.counttool.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lei
 * @version 1.0
 */
@Api(value = "用户controller",tags = {"用户操作接口"})
@RestController
@RequestMapping("/user")
public class UserController extends  BaseController {

  @Resource
  UserService userService;

    @ApiOperation(value="提交登录接口")
    @PostMapping("/Login")
    public String login(@ApiParam(name="user",value="user对象，只需要username和password",required=true)
                        @RequestBody User user) {
        System.out.println("====>"+user.getUserName()+user.getPassword());
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken authenticationToken = new UsernamePasswordToken();
        authenticationToken.setUsername(user.getUserName());
        authenticationToken.setPassword(user.getPassword().toCharArray());
        try {
            subject.login(authenticationToken);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg", "登录失败");
        }
        return jsonObject.toString();

    }




    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     */
    @ApiOperation(value="未登录接口，返回未登录消息")
    @GetMapping(value = "/unauth")
    public ResultDto unauth() {
        resultDto.setCode(200);
        resultDto.setMessage("未登录");
        resultDto.setSucceed(true);
        return resultDto;
    }


  @ApiOperation(value="提交添加用户接口 返回json消息")
  @PostMapping("/addUser")
  @ApiImplicitParam(paramType="query", name = "roleIds", value = "角色id", required = false, dataType = "Long")
  public User addUser( @ApiParam(name = "user",value = "user对象",required = true)
                       @RequestBody User user,
                       @RequestParam Long...roleIds){
                      userService.addUser(user, roleIds);
                       return user;
                      }

    @ApiOperation(value = "删除单个用户接口 返回json消息")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = false, dataType = "Long")
    @GetMapping("/deleteUser")
    public  Map<String, String> deleteUser(@RequestParam Long userId){
      Map<String, String> map = new HashMap<String, String>();
      try {
        if (userService.deleteUser(userId)) {
          map.put("msg", "删除成功");
        } else {
          map.put("msg", "删除失败");
        }
        return  map;
      } catch (Exception e){
        map.put("msg", "删除异常");
        e.printStackTrace();
      }
       return map;
    }

  @ApiOperation(value = "批量删除用户接口 返回json消息")
  @ApiImplicitParam(paramType="query", name = "userIds", value = "多个用户id", required = true, dataType = "array")
  @PostMapping("/deleteMoreUser")
  public Map<String,String> deleteMoreUsers(@RequestParam Long...userIds){
   Map<String,String> map = userService.deleteMoreUsers(userIds);
   return map;
  }

  @ApiOperation(value = "获得用户列表接口 返回数组")
  @GetMapping("/listUsers")
  public List<User> showUserList(){
      List<User> list = userService.getAllUsers();
      User user = list.get(0);
      System.out.println(user.toString());
      System.out.println(list);
      return list;
  }
  @ApiOperation(value = "更新用户中的角色,并删除旧的角色")
  @GetMapping("/corelationRole")
  @ApiImplicitParams({
      @ApiImplicitParam(name="userId",value="用户id",dataType="string", paramType = "query",required = true),
      @ApiImplicitParam(name="roleIds",value="角色id",dataType="long", paramType = "query", required = true)})
  public void corelationRole(@RequestParam Long userId, @RequestParam Long...roleIds){
    System.out.println("====>"+userId+roleIds);
    userService.updateUserRoles(userId, roleIds);
  }

  @GetMapping("/ListRolesByUserName")
  @ApiOperation(value = "根据用户名获得所拥有的角色")
  @ApiImplicitParam(name="username",value="用户名",dataType="String", paramType = "query", required = true)
  public List<Role> getRolesByUserName(String username){
      return  userService.findRolesByUserName(username);
  }


  @GetMapping("/ListUsersByRoleName")
  @ApiOperation(value = "根据角色名获得所拥有的用户")
  @ApiImplicitParam(name="roleName",value="角色名",dataType="String", paramType = "query", required = true)
  public List<User> ListUsersByRoleName(String roleName){
    return  userService.findUsersByRoleName(roleName);
  }

  @GetMapping("/ListPermissions")
  @ApiOperation(value = "根据用户名字查询相应权限")
  @ApiImplicitParam(name="username",value="用户名",dataType="String", paramType = "query", required = true)
  public Set<String> ListPermissions(String username){
    return  userService.findPermissionsByUserName(username);
  }
}

