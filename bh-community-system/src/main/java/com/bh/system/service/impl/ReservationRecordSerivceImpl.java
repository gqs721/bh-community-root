package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.common.utils.StringUtil;
import com.bh.model.dao.ReservationRecordMapper;
import com.bh.model.domain.ReservationRecord;
import com.bh.model.domain.TenementPassRecord;
import com.bh.system.service.ReservationRecordService;
import org.apache.ibatis.annotations.Param;
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
public class ReservationRecordSerivceImpl implements ReservationRecordService {

    @Autowired
    private ReservationRecordMapper reservationRecordMapper;

    @Override
    public RestResult auditReservation(Integer id) {
        ReservationRecord reservationRecord = reservationRecordMapper.selectByPrimaryKey(id);
        reservationRecord.setAuditStatus(1);
        reservationRecord.setReservationStatus(1);

        reservationRecordMapper.updateByPrimaryKeySelective(reservationRecord);

        return ResultUtils.success("接受预约成功");
    }

    @Override
    public RestResult editReservationEvolve(Integer id, Integer reservationEvolve) {
        ReservationRecord reservationRecord = reservationRecordMapper.selectByPrimaryKey(id);
        reservationRecord.setReservationEvolve(reservationEvolve);

        reservationRecordMapper.updateByPrimaryKeySelective(reservationRecord);
        return ResultUtils.success("修改预约进展成功");
    }

    @Override
    public RestResult save(ReservationRecord reservationRecord) {
        Date date = DateUtil.getCurrentDate();

        reservationRecord.setStatus(0);
        reservationRecord.setDelStatus(0);
        reservationRecord.setAuditStatus(0);
        reservationRecord.setReservationStatus(0);
        reservationRecord.setReservationEvolve(0);
        reservationRecord.setCreateTime(date);
        // 设置默认语音
        if(StringUtil.isEmpty(reservationRecord.getVisitorVoice())){
            reservationRecord.setVisitorVoice("欢迎到访");
        }

        reservationRecordMapper.insertSelective(reservationRecord);
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
        reservationRecordMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult list(String keyword, String beginDate, String endDate, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD, keyword);
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

        List<Map> maps = reservationRecordMapper.listPage(domainMap);
        int count = reservationRecordMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

}
