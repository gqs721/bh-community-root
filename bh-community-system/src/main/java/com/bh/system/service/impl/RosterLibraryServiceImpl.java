package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.RosterLibraryMapper;
import com.bh.model.domain.Notice;
import com.bh.model.domain.RosterLibrary;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.RosterLibraryService;
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
public class RosterLibraryServiceImpl implements RosterLibraryService {

    @Autowired
    private RosterLibraryMapper rosterLibraryMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult save(RosterLibrary rosterLibrary) {

        Date date = DateUtil.getCurrentDate();

        rosterLibrary.setStatus(0);
        rosterLibrary.setDelStatus(0);
        rosterLibrary.setCreateTime(date);

        rosterLibraryMapper.insertSelective(rosterLibrary);

        if (null == rosterLibrary.getId()){
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
        rosterLibraryMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult update(RosterLibrary rosterLibrary) {

        rosterLibraryMapper.updateByPrimaryKeySelective(rosterLibrary);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult get(Integer id) {
        return ResultUtils.success(rosterLibraryMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult list(String keyword, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = rosterLibraryMapper.listPage(domainMap);
        int count = rosterLibraryMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }
}
