package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.MonitorAids;
import com.bh.model.domain.RosterLibrary;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface MonitorAidsService {


    /**
     * 添加监控设备信息
     * @param monitorAids
     * @return
     */
    RestResult save(MonitorAids monitorAids);


    /**
     * 根据 pkId  删除监控设备信息
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 修改 监控设备信息
     * @param monitorAids
     * @return
     */
    RestResult update(MonitorAids monitorAids);


    /**
     * 获取监控设备信息  根据主键ID
     * @param id
     * @return
     */
    RestResult get(Integer id);


    /**
     * 监控设备列表
     * @param keyword
     * @param monitorType
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, Integer monitorType, Integer pageNum, Integer pageSize);


    /**
     * 修改监控设备信息状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);

}
