package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.MonitorAidsMapper;
import com.bh.model.domain.MonitorAids;
import com.bh.model.domain.RosterLibrary;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.MonitorAidsService;
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
public class MonitorAidsServiceImpl implements MonitorAidsService {

    @Autowired
    private MonitorAidsMapper monitorAidsMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult save(MonitorAids monitorAids) {

        Date date = DateUtil.getCurrentDate();

        monitorAids.setStatus(0);
        monitorAids.setDelStatus(0);
        monitorAids.setCreateTime(date);

        monitorAidsMapper.insertSelective(monitorAids);

        if (null == monitorAids.getId()){
            throw new IllegalArgumentException();
        }

        return ResultUtils.success("保存成功");
    }

    @Override
    public RestResult delete(List<Integer> pkIds) {
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
        monitorAidsMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult update(MonitorAids monitorAids) {

        monitorAidsMapper.updateByPrimaryKeySelective(monitorAids);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult get(Integer id) {
        return ResultUtils.success(monitorAidsMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult list(String keyword, Integer monitorType, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put("monitorType",monitorType);
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = monitorAidsMapper.listPage(domainMap);
        int count = monitorAidsMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult setStatus(Integer pkId){
        MonitorAids monitorAids = monitorAidsMapper.selectByPrimaryKey(pkId);
        if(monitorAids.getStatus() == 0){// 设置禁用
            monitorAids.setStatus(1);
        }else{ //设置启用
            monitorAids.setStatus(0);
        }
        monitorAidsMapper.updateByPrimaryKeySelective(monitorAids);

        return ResultUtils.success(monitorAids);
    }
}
