package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.VideoManage;
import com.bh.model.domain.VisitorPassRecord;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface VideoManageService {

    /**
     * 添加视频记录
     * @param videoManage
     * @return
     */
    RestResult save(VideoManage videoManage);

    /**
     * 删除视频记录
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 视频管理列表
     * @param keyword
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, String beginDate, String endDate, Integer pageNum, Integer pageSize);
}
