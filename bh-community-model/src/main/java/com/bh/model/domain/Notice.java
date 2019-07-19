package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Notice {
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
     * 轮播图
     */
    @ApiModelProperty(value="轮播图")
    private String noticeContent;

    /**
     * 定时播放，0：未定时，1：定时
     */
    @ApiModelProperty(value="定时播放，0：未定时，1：定时")
    private Integer timingPlay;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    private Date endTime;

    /**
     * 状态，0：启用，1：禁用
     */
    @ApiModelProperty(value="状态，0：启用，1：禁用")
    private Integer status;

    /**
     * 进度，0：未开始，1：进行中，2：已结束
     */
    @ApiModelProperty(value="进度，0：未开始，1：进行中，2：已结束")
    private Integer schedule;

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
     * 
     */
    @ApiModelProperty(value="")
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

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public Integer getTimingPlay() {
        return timingPlay;
    }

    public void setTimingPlay(Integer timingPlay) {
        this.timingPlay = timingPlay;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
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
        sb.append(", noticeContent=").append(noticeContent);
        sb.append(", timingPlay=").append(timingPlay);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", schedule=").append(schedule);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}