package com.yf.auth.service;


import com.yf.auth.entity.SysAreaEntity;
import com.yf.utils.entiy.R;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 * @author zcl<yczclcn@163.com>
 */
public interface SysAreaService {

	/**
	 * 根据父级id查询区域：ztree异步数据源
	 * @param areaCode
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(String areaCode);

	/**
	 * 根据父级id查询区域：表格数据源
	 * @param params
	 * @return
	 */
	R listAreaByParentCode(Map<String, Object> params);

	/**
	 * 新增区域
	 * @param area
	 * @return
	 */
	R saveArea(SysAreaEntity area);

	/**
	 * 根据id查询区域
	 * @param areaId
	 * @return
	 */
	R getAreaById(Long areaId);

	/**
	 * 修改区域
	 * @param area
	 * @return
	 */
	R updateArea(SysAreaEntity area);

	/**
	 * 批量删除区域
	 * @param id
	 * @return
	 */
	R batchRemoveArea(Long[] id);
	
}
