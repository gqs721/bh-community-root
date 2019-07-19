package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.HazardousAreaAlarm;
import com.bh.model.domain.HighTossActAlarm;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface HazardousAreaAlarmService {

    /**
     * 添加危险区域告警记录
     * @param hazardousAreaAlarm
     * @return
     */
    RestResult save(HazardousAreaAlarm hazardousAreaAlarm);

    /**
     * 删除危险区域告警记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 危险区域告警记录列表
     * @param keyword
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
