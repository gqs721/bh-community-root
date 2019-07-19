package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Notice;
import com.bh.model.domain.Slideshow;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface NoticeService {


    /**
     * 添加公告信息
     * @param notice
     * @return
     */
    RestResult save(Notice notice);


    /**
     * 根据 pkId  删除公告信息
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 修改 公告信息
     * @param notice
     * @return
     */
    RestResult update(Notice notice);


    /**
     * 获取公告信息  根据主键ID
     * @param id
     * @return
     */
    RestResult get(Integer id);


    /**
     * 公告列表
     * @param schedule
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(Integer schedule, String beginDate, String endDate, Integer pageNum, Integer pageSize);


    /**
     * 修改公告信息状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);

}
