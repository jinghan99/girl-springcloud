package com.yf.auth.dao;

import com.yf.auth.entiy.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 用户与角色关系
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

	/**
	 * 查询用户所有角色id
	 * @param userId
	 * @return
	 */
	List<Long> listUserRoleId(Long userId);

	/**
	 * 根据用户id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByUserId(Long[] id);

	/**
	 * 根据角色id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);
	
}
