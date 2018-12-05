package com.yf.auth.dao;

import com.yf.auth.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 系统角色与菜单关系
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

	/**
	 * 根据菜单id批量删除
	 * @param id
	 * @return
	 */
	int batchRemoveByMenuId(Long[] id);

	/**
	 * 根据角色id批量删除
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);

	/**
	 * 查询角色所有菜单id集合
	 * @param id
	 * @return
	 */
	List<Long> listMenuId(Long id);

	/**
	 * 查询角色 所有 URL 集合
	 * @param id
	 * @return
	 */
	List<String> listMenuUrl(Long id);
	
}
