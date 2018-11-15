package com.yf.sysuser.dao;

import java.util.List;

import com.yf.sysuser.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;


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
	
}
