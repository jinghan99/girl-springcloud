package com.yf.sysuser.service;


import com.yf.common.entiy.Page;
import com.yf.common.entiy.R;
import com.yf.sysuser.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 * @author zcl<yczclcn@163.com>
 */
public interface SysLogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<SysLogEntity> listLog(Map<String, Object> params);

    /**
     * 批量删除
     * @param id
     * @return
     */
    R batchRemove(Long[] id);

    /**
     * 清空日志
     * @return
     */
    R batchRemoveAll();

}
