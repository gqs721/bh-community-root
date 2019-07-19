package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Slideshow;
import com.bh.model.domain.VoiceLibrary;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface SlideshowService {


    /**
     * 添加轮播图
     * @param slideshow
     * @return
     */
    RestResult save(Slideshow slideshow);


    /**
     * 根据 pkId  删除轮播图
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 修改 轮播图信息
     * @param slideshow
     * @return
     */
    RestResult update(Slideshow slideshow);


    /**
     * 获取轮播图信息  根据主键ID
     * @param id
     * @return
     */
    RestResult get(Integer id);


    /**
     * 轮播图列表
     * @param schedule
     * @param beginDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(Integer schedule, String beginDate, String endDate, Integer pageNum, Integer pageSize);


    /**
     * 修改轮播图状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);

}
