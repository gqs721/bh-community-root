package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.SlideshowMapper;
import com.bh.model.domain.Slideshow;
import com.bh.model.domain.VoiceLibrary;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.SlideshowService;
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
public class SlideshowServiceImpl implements SlideshowService {

    @Autowired
    private SlideshowMapper slideshowMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult save(Slideshow slideshow) {

        Date date = DateUtil.getCurrentDate();

        slideshow.setStatus(0);
        slideshow.setSchedule(0);
        slideshow.setDelStatus(0);
        slideshow.setCreateTime(date);

        slideshowMapper.insertSelective(slideshow);

        if (null == slideshow.getId()){
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
        slideshowMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult update(Slideshow slideshow) {

        slideshowMapper.updateByPrimaryKeySelective(slideshow);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult get(Integer id) {
        return ResultUtils.success(slideshowMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult list(Integer schedule, String beginDate, String endDate, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put("schedule",schedule);
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

        List<Map> maps = slideshowMapper.listPage(domainMap);
        int count = slideshowMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult setStatus(Integer pkId){
        Slideshow slideshow = slideshowMapper.selectByPrimaryKey(pkId);
        if(slideshow.getStatus() == 0){// 设置禁用
            slideshow.setStatus(1);
        }else{ //设置启用
            slideshow.setStatus(0);
        }
        slideshowMapper.updateByPrimaryKeySelective(slideshow);

        return ResultUtils.success(slideshow);
    }
}
