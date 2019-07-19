package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.NoticeMapper;
import com.bh.model.domain.Notice;
import com.bh.model.domain.Slideshow;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.NoticeService;
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
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult save(Notice notice) {

        Date date = DateUtil.getCurrentDate();

        notice.setStatus(0);
        notice.setSchedule(0);
        notice.setDelStatus(0);
        notice.setCreateTime(date);

        noticeMapper.insertSelective(notice);

        if (null == notice.getId()){
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
        noticeMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult update(Notice notice) {

        notice.setUpdateTime(DateUtil.getCurrentDate());

        noticeMapper.updateByPrimaryKeySelective(notice);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult get(Integer id) {
        return ResultUtils.success(noticeMapper.selectByPrimaryKey(id));
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

        List<Map> maps = noticeMapper.listPage(domainMap);
        int count = noticeMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult setStatus(Integer pkId){
        Notice notice = noticeMapper.selectByPrimaryKey(pkId);
        if(notice.getStatus() == 0){// 设置禁用
            notice.setStatus(1);
        }else{ //设置启用
            notice.setStatus(0);
        }
        notice.setUpdateTime(DateUtil.getCurrentDate());
        noticeMapper.updateByPrimaryKeySelective(notice);

        return ResultUtils.success(notice);
    }
}
