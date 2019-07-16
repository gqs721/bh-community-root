package com.bh.system.service.impl;

import com.bh.common.constants.AdminConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.CollectionUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.*;
import com.bh.model.domain.Admin;
import com.bh.model.domain.PropertyManagement;
import com.bh.model.domain.RoleResource;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.PropertyManagementService;
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
public class PropertyManagementSerivceImpl implements PropertyManagementService {

    @Autowired
    private PropertyManagementMapper propertyManagementMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;


    @Override
    public RestResult add(PropertyManagement propertyManagement) {

        List<PropertyManagement> pms = propertyManagementMapper.getAll();
        if(!pms.isEmpty()){
            return ResultUtils.error(1,"已有一个物业信息，请不要重复添加");
        }
        
        Date currentDate = new Date();

        propertyManagement.setAuditStatus(1);
        propertyManagement.setCreateTime(currentDate);

        propertyManagementMapper.insertSelective(propertyManagement);

        return ResultUtils.success("保存成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult delete(List<Integer> ids) {
        PropertyManagement pm = null;
        for (Integer id : ids) {
            pm = propertyManagementMapper.selectByPrimaryKey(id);
            if (null != pm){
//                pm.se(1);
                propertyManagementMapper.deleteByPrimaryKey(id);
            }
        }
        return ResultUtils.success("删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(PropertyManagement propertyManagement) {

        propertyManagementMapper.updateByPrimaryKeySelective(propertyManagement);

        return ResultUtils.success("更新成功");
    }

    @Override
    public RestResult getAll() {

        return ResultUtils.success(propertyManagementMapper.getAll());
    }

}
