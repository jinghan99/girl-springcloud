package com.yf.sysuser.dao;

import java.util.List;

import com.yf.sysuser.entity.SysRoleOrgEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与机构的关系
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	/**
	 * 查询角色所有机构id集合
	 * @param roleId
	 * @return
	 */
	List<Long> listOrgId(Long roleId);

	/**
	 * 根据机构id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByOrgId(Long[] id);

	/**
	 * 根据角色id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);
	
}
