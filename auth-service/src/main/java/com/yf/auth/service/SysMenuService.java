package com.yf.auth.service;


import com.yf.auth.entiy.SysMenuEntity;
import com.yf.utils.entiy.R;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @author zcl<yczclcn@163.com>
 */
public interface SysMenuService {

	/**
	 * 查询用户菜单
	 * @param userId
	 * @return
	 */
	R listUserMenu(Long userId);

	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 */
	List<SysMenuEntity> listMenu(Map<String, Object> params);

	/**
	 * 查询目录和菜单
	 * @return
	 */
	R listNotButton();

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	R saveMenu(SysMenuEntity menu);

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	R getMenuById(Long id);

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	R updateMenu(SysMenuEntity menu);

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	R batchRemove(Long[] id);

}
