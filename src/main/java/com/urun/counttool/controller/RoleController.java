package com.urun.counttool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.urun.counttool.model.entity.ResultDto;
import com.urun.counttool.model.entity.Role;
import com.urun.counttool.service.PermissionService;
import com.urun.counttool.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Null;

@Api(value = "角色类controller",tags = {"角色类操作接口"})
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@GetMapping("/list")
	public List<Role> ListRole(){

		List list=roleService.getAllRoles();
		return list;
	}

	@RequestMapping("/listperms")
	@ResponseBody
	public List getPerms(){
		return permissionService.getAllPermissions();
	}

  @PostMapping("add")
	@ApiOperation(value="提交添加角色接口 返回json消息")
	@ApiImplicitParam(paramType="query", name = "permIds", value = "权限id",
			               required = true, dataType = "long")
	public ResultDto addRole(@ApiParam(name = "role",value = "role对象",required = true)
													 @RequestBody Role role,
													 @RequestParam Long...permIds){
					try{
				    roleService.addRole(role, permIds);
					  resultDto.setCode(200);
					  resultDto.setSucceed(true);
					  resultDto.setMessage("添加成功");
					}catch (DataAccessException d){
						d.printStackTrace();
					}
					return resultDto;
				}

	@GetMapping("/delete")
	@ApiOperation(value="根据角色id删除角色,并取消用户_角色，角色_权限关联,返回json消息")
	@ApiImplicitParam(paramType="query", name = "roleId", value = "角色id",
			required = true, dataType = "Long")
	public ResultDto deleteRole(Long roleId){
		Map<String,Object>map = new HashMap();
		try {
			roleService.deleteRole(roleId);
		  resultDto.setMessage("删除成功");
		  resultDto.setSucceed(true);
		  resultDto.setCode(200);
		}catch (Exception e){
			e.printStackTrace();
		}
       return resultDto;
	}

	@PostMapping("/deletemore")
	@ApiOperation(value="根据角色id批量删除角色,并取消用户_角色，角色_权限关联,返回json消息")
	@ApiImplicitParam(paramType="query", name = "roleIds", value = "角色id,集合",
			required = true, dataType = "Long")
	public ResultDto deleteMoreRoles(@RequestParam Long...roleIds){
		try {
			roleService.deleteMoreRoles(roleIds);
			resultDto.setMessage("删除成功");
			resultDto.setSucceed(true);
			resultDto.setCode(200);
		}catch (DataAccessException e){
		    e.printStackTrace();
		}
		return resultDto;
	}
	
	@RequiresPermissions("role:showperms")
	@RequestMapping("/ListRolePerms")
	@ResponseBody
	public List showRolePerms(Long roleId){
		return permissionService.getPermissionsByRoleId(roleId);
	}
	

	@GetMapping("/findRoleByRid")
	@ApiOperation(value="根据角色id查找角色，返回json消息")
	@ApiImplicitParam(paramType="query", name = "roleId", value = "角色id",
			required = true, dataType = "Long")
	public ResultDto getRoleById(Long roleId){
		resultDto.setSucceed(true);
		resultDto.setCode(200);
		resultDto.setData(roleService.getRoleById(roleId));
		return resultDto;
	}

	@PostMapping("/updaterole")
	@ApiOperation(value="更新角色信息，通过权限id更新权限，并删除旧的权限，返回json消息")
	@ApiImplicitParam(paramType="query", name = "permIds", value = "权限id,数组",
			required = true, dataType = "Long")
	public ResultDto updateRole(@ApiParam(name = "role",value = "role对象",required = true)
																			 @RequestBody Role role,
																			 @RequestParam Long...permIds){
		try {
			roleService.updateRole(role,permIds);
			resultDto.setSucceed(true);
			resultDto.setCode(200);
		  resultDto.setMessage("更新成功");
	}catch (Exception e){
		  e.printStackTrace();
			resultDto.setSucceed(false);
			resultDto.setCode(400);
			resultDto.setMessage("更新失败"+e);
	}
		return resultDto;
}


	@GetMapping("/ListRoleByUName")
	@ApiOperation(value="根据用户名查找所拥有的角色，返回json消息")
	@ApiImplicitParam(paramType="query", name = "username", value = "用户名字",
			required = true, dataType = "Long")
	public ResultDto getRoleUName(String username){
		try {
			List<Role> list = roleService.getRolesByUserName(username);
			resultDto.setSucceed(true);
			resultDto.setCode(200);
			resultDto.setMessage("查找成功");
			resultDto.setData(list);
		}catch (Exception e){
		  e.printStackTrace();
		}

	  return resultDto;
	}

}

