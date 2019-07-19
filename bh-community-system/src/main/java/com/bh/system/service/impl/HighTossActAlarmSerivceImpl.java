package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.HighTossActAlarmMapper;
import com.bh.model.domain.BlacklistAlarm;
import com.bh.model.domain.HighTossActAlarm;
import com.bh.system.service.HighTossActAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class HighTossActAlarmSerivceImpl implements HighTossActAlarmService {

    @Autowired
    private HighTossActAlarmMapper highTossActAlarmMapper;


    @Override
    public RestResult save(HighTossActAlarm highTossActAlarm) {
        highTossActAlarm.setStatus(0);
        highTossActAlarm.setDelStatus(0);
        highTossActAlarm.setCreateTime(DateUtil.getCurrentDate());
        highTossActAlarmMapper.insertSelective(highTossActAlarm);
        return ResultUtils.success("添加成功");
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

        // 住户信息 逻辑删除
        highTossActAlarmMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult list(String keyword, String installationSite, String beginDate, String endDate, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD, keyword);
        domainMap.put("installationSite", installationSite);
        if (!StringUtil.isEmpty(beginDate)) {
            domainMap.put("beginDate", DateUtil.parseStringYMD(beginDate));
        }else{
            domainMap.put("beginDate", null);
        }
        if(!StringUtil.isEmpty(endDate)) {
            domainMap.put("endDate", DateUtil.parseStringYMD(endDate));
        }else{
            domainMap.put("endDate", null);
        }
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = highTossActAlarmMapper.listPage(domainMap);
        int count = highTossActAlarmMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

}
