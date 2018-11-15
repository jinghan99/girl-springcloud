package com.yf.sysuser.service;


import com.yf.common.entiy.Page;
import com.yf.common.entiy.R;
import com.yf.sysuser.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 * @author zcl<yczclcn@163.com>
 */
public interface SysRoleService {

	/**
	 * 分页查询角色列表
	 * @param params
	 * @return
	 */
	Page<SysRoleEntity> listRole(Map<String, Object> params);

	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	R saveRole(SysRoleEntity role);

	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	R getRoleById(Long id);

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	R updateRole(SysRoleEntity role);

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	R batchRemove(Long[] id);

	/**
	 * 所有角色集合：用户角色选择数据源
	 * @return
	 */
	List<SysRoleEntity> listRole();

	/**
	 * 操作权限
	 * @param role
	 * @return
	 */
	R updateRoleOptAuthorization(SysRoleEntity role);

	/**
	 * 数据权限
	 * @param role
	 * @return
	 */
	R updateRoleDataAuthorization(SysRoleEntity role);
	
}
