package com.yf.sysuser.dao;

import java.util.List;

import com.yf.sysuser.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;


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
	
}
