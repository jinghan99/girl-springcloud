package com.yf.sysuser.controller;

import java.util.List;
import java.util.Map;

import com.yf.common.annotation.SysLog;
import com.yf.common.entiy.Page;
import com.yf.common.entiy.R;
import com.yf.sysuser.entity.SysRoleEntity;
import com.yf.sysuser.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统角色
 * @author zcl<yczclcn@163.com>
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<SysRoleEntity> list(@RequestBody Map<String, Object> params) {

		return sysRoleService.listRole(params);
	}
	
	/**
	 * 用户角色
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysRoleEntity> listRole() {
		return sysRoleService.listRole();
	}
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@SysLog("新增角色")
	@RequestMapping("/save")
	public R saveRole(@RequestBody SysRoleEntity role) {
		return sysRoleService.saveRole(role);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getRoleById(@RequestBody Long id) {
		return sysRoleService.getRoleById(id);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	public R updateRole(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRole(role);
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysRoleService.batchRemove(id);
	}
	
	/**
	 * 分配权限
	 * @param role
	 * @return
	 */
	@SysLog("操作权限")
	@RequestMapping("/authorize/opt")
	public R updateRoleOptAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleOptAuthorization(role);
	}
	
	/**
	 * 数据权限
	 * @param role
	 * @return
	 */
	@SysLog("数据权限")
	@RequestMapping("/authorize/data")
	public R updateRoleDataAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleDataAuthorization(role);
	}
	
}