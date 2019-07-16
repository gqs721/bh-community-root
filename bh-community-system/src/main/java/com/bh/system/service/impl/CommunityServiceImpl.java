package com.bh.system.service.impl;

import com.bh.common.aop.WebLogAspect;
import com.bh.common.constants.AdminConstant;
import com.bh.common.constants.ParamConstant;
import com.bh.common.enums.ResultCodeEnum;
import com.bh.common.exceptions.APIRunTimeException;
import com.bh.common.exceptions.RoleNotExistException;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.EncryptUtils;
import com.bh.common.utils.JWTUtils;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.*;
import com.bh.model.domain.*;
import com.bh.system.params.AdminRequest;
import com.bh.system.params.AdminUpdateRequest;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.AdminService;
import com.bh.system.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult saveCommunity(Community community) {

        Date date = DateUtil.getCurrentDate();

        community.setStatus(0);
        community.setDelStatus(0);
        community.setCreateTime(date);

        communityMapper.insertSelective(community);

        if (null == community.getId()){
            throw new IllegalArgumentException();
        }

        return ResultUtils.success("保存成功");
    }

    @Override
    public RestResult deleteCommunity(List<Integer> pkIds) {
        if(pkIds == null || pkIds.isEmpty()){
            return ResultUtils.error(1,"没有选择需要删除的数据");
        }

        Integer[] ids = new Integer[pkIds.size()];

        if (pkIds.size() > 0){
            for (int i = 0; i < pkIds.size(); i++) {
                ids[i] = pkIds.get(i);
            }
        }

        // 小区信息 逻辑删除
        communityMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult updateCommunity(Community community) {

        Date currentDate = DateUtil.getCurrentDate();

        communityMapper.updateByPrimaryKeySelective(community);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult getCommunity(Integer id) {
        return ResultUtils.success(communityMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult listCommunity(String keyword, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = communityMapper.listPage(domainMap);
        int count = communityMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult setStatus(Integer pkId){
        Community community = communityMapper.selectByPrimaryKey(pkId);
        if(community.getStatus() == 0){// 设置禁用
            community.setStatus(1);
        }else{ //设置启用
            community.setStatus(0);
        }
        communityMapper.updateByPrimaryKeySelective(community);

        return ResultUtils.success(community);
    }

    @Override
    public RestResult findListByPmId(Integer pmId){
        return ResultUtils.success(communityMapper.findListByPmId(pmId));
    }
}
