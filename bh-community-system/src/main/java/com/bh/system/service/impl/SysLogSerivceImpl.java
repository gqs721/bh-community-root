package com.bh.system.service.impl;

import com.bh.common.constants.AdminConstant;
import com.bh.common.enums.ResultCodeEnum;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.CollectionUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.*;
import com.bh.model.domain.Admin;
import com.bh.model.domain.Role;
import com.bh.model.domain.RoleResource;
import com.bh.model.domain.SysLog;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.RoleService;
import com.bh.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class SysLogSerivceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public RestResult save(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
        return ResultUtils.success(null);
    }

}
