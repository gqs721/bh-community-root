package com.bh.system.service;

import com.bh.common.result.RestResult;
import com.bh.model.domain.Community;
import com.bh.model.domain.VoiceLibrary;

import java.util.List;

/**
 * Created by Mr.BH
 */
public interface VoiceLibraryService {


    /**
     * 添加语音
     * @param voiceLibrary
     * @return
     */
    RestResult save(VoiceLibrary voiceLibrary);


    /**
     * 根据 pkId  删除语音
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);


    /**
     * 修改 语音信息
     * @param voiceLibrary
     * @return
     */
    RestResult update(VoiceLibrary voiceLibrary);


    /**
     * 获取语音信息  根据主键ID
     * @param id
     * @return
     */
    RestResult get(Integer id);


    /**
     * 语音列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    RestResult list(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 修改语音状态
     * @param id
     * @return
     */
    RestResult setStatus(Integer id);


    /**
     * 通过类型获取语音
     * @param voiceType
     * @return
     */
    RestResult findByVoiceType(Integer voiceType);

}
