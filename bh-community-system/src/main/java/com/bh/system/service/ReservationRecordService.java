package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.ReservationRecord;
import com.bh.model.domain.TenementPassRecord;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface ReservationRecordService {

    /**
     * 添加预约记录
     * @param reservationRecord
     * @return
     */
    RestResult save(ReservationRecord reservationRecord);

    /**
     * 删除预约记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 预约记录列表
     * @param keyword
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, String beginDate, String endDate, Integer pageNum, Integer pageSize);

    /**
     * 审核预约
     * @param id
     * @return
     */
    RestResult auditReservation(Integer id);

    /**
     * 修改预约进展
     * @param id
     * @param reservationEvolve
     * @return
     */
    RestResult editReservationEvolve(Integer id, Integer reservationEvolve);
}
