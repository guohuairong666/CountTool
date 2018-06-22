package com.urun.counttool.controller;

import java.util.List;
import java.util.Map;

import com.urun.counttool.model.entity.Permission;
import com.urun.counttool.model.entity.ResultDto;
import com.urun.counttool.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Api(value = "权限controller",tags = {"权限操作接口"})
@RestController
@RequestMapping("/perm")
public class PermissionController extends BaseController {
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("ListAllPermissions")
	@ApiOperation(value="获取权限列表")
	public ResultDto showRoleList(){
		try{
			List<Permission> list=permissionService.getAllPermissions();
			resultDto.setCode(200);
			resultDto.setSucceed(true);
			resultDto.setData(list);
		}catch (Exception e){
			 e.printStackTrace();
		}
		  return resultDto;
	}

		@ApiOperation(value="提交添加用户接口 返回json消息")
		@PostMapping("/addPermission")
		public Map<String,Object> addPermission(@ApiParam(name = "user",value = "user对象",required = true)
																		        @RequestBody Permission permission){
		           try {
								    permissionService.addPermission(permission);
		           	    map.put("msg","添加成功");
							 }catch (Exception e) {
		           	    map.put("msg","添加失败");
							 }
		             return map;
	}
	
//	@RequiresPermissions("perm:delete")
//	@RequestMapping("/delete")
//	@ResponseBody
//	public void deletePermission(Long permId){
//		permissionService.deletePermission(permId);
//	}
//
//	@RequiresPermissions("perm:delete")
//	@RequestMapping("/deletemore")
//	@ResponseBody
//	public void deleteMorePerms(Long...permIds){
//		permissionService.deleteMorePermissions(permIds);
//	}

	@ApiOperation(value="根据权限id查询权限")
	@ApiImplicitParam(paramType="query", name = "permId", value = "权限id", required = true, dataType = "Long")
	@GetMapping("/getperm")
	public ResultDto getPermById(@RequestParam Long permId){
		try {
			Permission pm = permissionService.findById(permId);
			resultDto.setCode(200);
			resultDto.setSucceed(true);
			resultDto.setData(pm);
		}catch (Exception e){
			e.printStackTrace();
		}
		 return resultDto;
	}

	@PostMapping("/update")
	@ApiOperation(value="更新权限")

	public ResultDto updatePermission(@ApiParam(name = "permission",value = "权限对象",required = true)
																		@RequestBody Permission permission){
		try {
			permissionService.updatePermission(permission);
			resultDto.setCode(200);
			resultDto.setSucceed(true);
      resultDto.setMessage("更新成功");
		}catch (Exception e){
			 e.printStackTrace();
		}
		 return  resultDto;
	}



	@GetMapping("/ListPermissions")
	@ApiOperation(value="根据角色id查询所有权限")
	@ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "Long")
	public ResultDto ListPermissionsByRoleId(Long roleId){
		try {
			List<Permission> list = permissionService.getPermissionsByRoleId(roleId);
			resultDto.setData(list);
			resultDto.setSucceed(true);
			resultDto.setCode(200);
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultDto;
	}
}

