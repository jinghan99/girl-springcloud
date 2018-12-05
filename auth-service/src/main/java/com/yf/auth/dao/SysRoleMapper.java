package com.yf.auth.dao;

import com.yf.auth.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 系统角色
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

	/**
	 * 查询用户角色集合
	 * @param userId
	 * @return
	 */
	List<String> listUserRoles(Long userId);


	SysRoleEntity getBySign(String roleSign);
}
