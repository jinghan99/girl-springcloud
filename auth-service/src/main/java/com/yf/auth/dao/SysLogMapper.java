package com.yf.auth.dao;

import com.yf.auth.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统日志
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	/**
	 * 批量删除
	 * @return
	 */
	int batchRemoveAll();
	
}
