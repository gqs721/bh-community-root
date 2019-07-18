package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.BuildingMapper;
import com.bh.model.dao.CommunityMapper;
import com.bh.model.dao.TenementMapper;
import com.bh.model.domain.Building;
import com.bh.model.domain.Community;
import com.bh.model.domain.Tenement;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.TenementService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class TenementServiceImpl implements TenementService {

    @Autowired
    private TenementMapper tenementMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult saveTenement(Tenement tenement) {

        Date date = DateUtil.getCurrentDate();

        tenement.setStatus(0);
        tenement.setDelStatus(0);
        tenement.setCreateTime(date);

        tenementMapper.insertSelective(tenement);

        if (null == tenement.getId()){
            throw new IllegalArgumentException();
        }

        return ResultUtils.success("保存成功");
    }

    @Override
    public RestResult deleteTenement(List<Integer> pkIds) {
        if(pkIds == null || pkIds.isEmpty()){
            return ResultUtils.error(1,"没有选择需要删除的数据");
        }

        Integer[] ids = new Integer[pkIds.size()];

        if (pkIds.size() > 0){
            for (int i = 0; i < pkIds.size(); i++) {
                ids[i] = pkIds.get(i);
            }
        }

        // 住户信息 逻辑删除
        tenementMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult updateTenement(Tenement tenement) {

        tenementMapper.updateByPrimaryKeySelective(tenement);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult getTenement(Integer id) {
        return ResultUtils.success(tenementMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult listTenement(String keyword, String tenementType, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put("tenementType",tenementType);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = tenementMapper.listPage(domainMap);
        int count = tenementMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult setStatus(Integer pkId){
        Tenement tenement = tenementMapper.selectByPrimaryKey(pkId);
        String msg = "";
        if(tenement.getStatus() == 0){// 设置禁用
            tenement.setStatus(1);
            msg = "禁用成功";
        }else{ //设置启用
            tenement.setStatus(0);
            msg = "启用成功";
        }
        tenementMapper.updateByPrimaryKeySelective(tenement);

        return ResultUtils.success(msg);
    }
}
