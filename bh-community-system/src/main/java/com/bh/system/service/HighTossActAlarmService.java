package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.BlacklistAlarm;
import com.bh.model.domain.HighTossActAlarm;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface HighTossActAlarmService {

    /**
     * 添加高空抛物告警记录
     * @param highTossActAlarm
     * @return
     */
    RestResult save(HighTossActAlarm highTossActAlarm);

    /**
     * 删除高空抛物告警记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 高空抛物告警记录列表
     * @param keyword
     * @param installationSite
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, String installationSite, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
