package com.yf.auth.dao;

import com.yf.auth.entity.ColumnEntity;
import com.yf.auth.entity.TableEntity;
import com.yf.utils.entiy.Page;
import com.yf.utils.entiy.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 代码生成器
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysGeneratorMapper {

	/**
	 * 查询所有表格
	 * @param page
	 * @param query
	 * @return
	 */
	List<TableEntity> listTable(Page<TableEntity> page, Query query);

	/**
	 * 根据名称查询
	 * @param tableName
	 * @return
	 */
	TableEntity getTableByName(String tableName);

	/**
	 * 查询所有列
	 * @param tableName
	 * @return
	 */
	List<ColumnEntity> listColumn(String tableName);
	
}
