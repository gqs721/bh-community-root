package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.TenementPassRecord;
import com.bh.model.domain.VisitorPassRecord;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface VisitorPassRecordService {

    /**
     * 添加访客进出记录
     * @param tpr
     * @return
     */
    RestResult save(VisitorPassRecord visitorPassRecord);

    /**
     * 删除访客进出记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 访客进出记录列表
     * @param keyword
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
