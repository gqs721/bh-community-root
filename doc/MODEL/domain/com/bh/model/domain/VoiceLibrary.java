package com.bh.model.domain;

import java.util.Date;

public class VoiceLibrary {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 小区id
     */
    @ApiModelProperty(value="小区id")
    private Integer communityId;

    /**
     * 语音类型，1：住户，2：访客，3：危险区域，4：人群聚集
     */
    @ApiModelProperty(value="语音类型，1：住户，2：访客，3：危险区域，4：人群聚集")
    private Integer voiceType;

    /**
     * 提示文本
     */
    @ApiModelProperty(value="提示文本")
    private String alertingText;

    /**
     * 语音内容
     */
    @ApiModelProperty(value="语音内容")
    private String voiceContent;

    /**
     * 状态，0：启用，1：禁用
     */
    @ApiModelProperty(value="状态，0：启用，1：禁用")
    private Integer status;

    /**
     * 音频
     */
    @ApiModelProperty(value="音频")
    private String voiceFrequency;

    /**
     * 删除状态，0：未删除，1：已删除
     */
    @ApiModelProperty(value="删除状态，0：未删除，1：已删除")
    private Integer delStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(Integer voiceType) {
        this.voiceType = voiceType;
    }

    public String getAlertingText() {
        return alertingText;
    }

    public void setAlertingText(String alertingText) {
        this.alertingText = alertingText == null ? null : alertingText.trim();
    }

    public String getVoiceContent() {
        return voiceContent;
    }

    public void setVoiceContent(String voiceContent) {
        this.voiceContent = voiceContent == null ? null : voiceContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVoiceFrequency() {
        return voiceFrequency;
    }

    public void setVoiceFrequency(String voiceFrequency) {
        this.voiceFrequency = voiceFrequency == null ? null : voiceFrequency.trim();
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", communityId=").append(communityId);
        sb.append(", voiceType=").append(voiceType);
        sb.append(", alertingText=").append(alertingText);
        sb.append(", voiceContent=").append(voiceContent);
        sb.append(", status=").append(status);
        sb.append(", voiceFrequency=").append(voiceFrequency);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}