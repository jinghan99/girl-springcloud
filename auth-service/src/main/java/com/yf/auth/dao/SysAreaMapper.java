package com.yf.auth.dao;

import com.yf.auth.entity.SysAreaEntity;
import com.yf.utils.entiy.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 行政区域
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {

	/**
	 * 根据父计编码查询
	 * @param query
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(Query query);

	/**
	 * 子节点总数
	 * @param areaId
	 * @return
	 */
	int countAreaChildren(Long areaId);
	
}
