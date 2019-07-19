package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class VideoManage {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 监控id
     */
    @ApiModelProperty(value="监控id")
    private Integer monitorId;

    /**
     * 监控名称
     */
    @ApiModelProperty(value="监控名称")
    private String monitorName;

    /**
     * 监控位置
     */
    @ApiModelProperty(value="监控位置")
    private String monitorSite;

    /**
     * 监控日期
     */
    @ApiModelProperty(value="监控日期")
    private Date monitorDate;

    /**
     * 监控时间
     */
    @ApiModelProperty(value="监控时间")
    private String monitorTime;

    /**
     * 监控时长
     */
    @ApiModelProperty(value="监控时长")
    private String monitorDuration;

    /**
     * 视频路径
     */
    @ApiModelProperty(value="视频路径")
    private String videoUrl;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private Integer status;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName == null ? null : monitorName.trim();
    }

    public String getMonitorSite() {
        return monitorSite;
    }

    public void setMonitorSite(String monitorSite) {
        this.monitorSite = monitorSite == null ? null : monitorSite.trim();
    }

    public Date getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime == null ? null : monitorTime.trim();
    }

    public String getMonitorDuration() {
        return monitorDuration;
    }

    public void setMonitorDuration(String monitorDuration) {
        this.monitorDuration = monitorDuration == null ? null : monitorDuration.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", monitorId=").append(monitorId);
        sb.append(", monitorName=").append(monitorName);
        sb.append(", monitorSite=").append(monitorSite);
        sb.append(", monitorDate=").append(monitorDate);
        sb.append(", monitorTime=").append(monitorTime);
        sb.append(", monitorDuration=").append(monitorDuration);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}