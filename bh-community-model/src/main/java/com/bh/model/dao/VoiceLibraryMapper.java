package com.bh.model.dao;

import com.bh.model.domain.VoiceLibrary;

import java.util.List;
import java.util.Map;

public interface VoiceLibraryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoiceLibrary record);

    int insertSelective(VoiceLibrary record);

    VoiceLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoiceLibrary record);

    int updateByPrimaryKey(VoiceLibrary record);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);


    /**
     * 列表
     * @param queryMap
     * @return
     */
    List<Map> listPage(Map queryMap);

    /**
     * 统计
     * @param queryMap
     * @return
     */
    Integer countPage(Map queryMap);

    /**
     * 通过语音类型获取语音信息
     * @param voiceType
     * @return
     */
    List<VoiceLibrary> findByVoiceType(Integer voiceType);
}