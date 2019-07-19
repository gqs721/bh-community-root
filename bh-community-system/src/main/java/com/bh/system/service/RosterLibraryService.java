package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Notice;
import com.bh.model.domain.RosterLibrary;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface RosterLibraryService {


    /**
     * 添加黑白名单信息
     * @param rosterLibrary
     * @return
     */
    RestResult save(RosterLibrary rosterLibrary);


    /**
     * 根据 pkId  删除黑白名单信息
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 修改 黑白名单信息
     * @param rosterLibrary
     * @return
     */
    RestResult update(RosterLibrary rosterLibrary);


    /**
     * 获取黑白名单信息  根据主键ID
     * @param id
     * @return
     */
    RestResult get(Integer id);


    /**
     * 黑白名单列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, Integer pageNum, Integer pageSize);

}
