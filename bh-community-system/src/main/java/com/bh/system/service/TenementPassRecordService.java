package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.SysLog;
import com.bh.model.domain.TenementPassRecord;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface TenementPassRecordService {

    /**
     * 添加住户进出记录
     * @param tpr
     * @return
     */
    RestResult save(TenementPassRecord tpr);

    /**
     * 删除住户进出记录
     * @param ids
     * @return
     */
    RestResult delete( List<Integer> ids);


    /**
     * 住户进出记录列表
     * @param tenementName
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String tenementName, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
