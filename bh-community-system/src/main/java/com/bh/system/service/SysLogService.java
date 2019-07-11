package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.SysLog;
import com.bh.system.params.AdminRequest;
import com.bh.system.params.AdminUpdateRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
public interface SysLogService {

    /**
     * 添加操作日志
     * @param sysLog
     * @return
     */
    RestResult save(SysLog sysLog);
}
