package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.BlacklistAlarm;
import com.bh.model.domain.VideoManage;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface BlacklistAlarmService {

    /**
     * 添加黑名单告警记录
     * @param blacklistAlarm
     * @return
     */
    RestResult save(BlacklistAlarm blacklistAlarm);

    /**
     * 删除黑名单告警记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 黑名单告警记录列表
     * @param keyword
     * @param alarmType
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, Integer alarmType, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
