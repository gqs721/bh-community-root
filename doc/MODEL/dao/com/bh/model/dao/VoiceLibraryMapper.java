package com.bh.model.dao;

import com.bh.model.domain.VoiceLibrary;

public interface VoiceLibraryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoiceLibrary record);

    int insertSelective(VoiceLibrary record);

    VoiceLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoiceLibrary record);

    int updateByPrimaryKey(VoiceLibrary record);
}