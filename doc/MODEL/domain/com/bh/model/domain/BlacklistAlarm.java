package com.bh.model.domain;

import java.util.Date;

public class BlacklistAlarm {
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
     * 黑名单id
     */
    @ApiModelProperty(value="黑名单id")
    private Integer blacklistId;

    /**
     * 监控id
     */
    @ApiModelProperty(value="监控id")
    private Integer monitorId;

    /**
     * 截图
     */
    @ApiModelProperty(value="截图")
    private String printscreen;

    /**
     * 监控名
     */
    @ApiModelProperty(value="监控名")
    private String monitorName;

    /**
     * 安装位置
     */
    @ApiModelProperty(value="安装位置")
    private String installationSite;

    /**
     * 告警类型，1：黑名单，2：白名单
     */
    @ApiModelProperty(value="告警类型，1：黑名单，2：白名单")
    private Integer alarmType;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String blacklistName;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String blacklistPhone;

    /**
     * 证件号
     */
    @ApiModelProperty(value="证件号")
    private String idNumber;

    /**
     * 告警日期
     */
    @ApiModelProperty(value="告警日期")
    private Date alarmDate;

    /**
     * 告警时间
     */
    @ApiModelProperty(value="告警时间")
    private String alarmTime;

    /**
     * 相似度
     */
    @ApiModelProperty(value="相似度")
    private Integer similarity;

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

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(Integer blacklistId) {
        this.blacklistId = blacklistId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getPrintscreen() {
        return printscreen;
    }

    public void setPrintscreen(String printscreen) {
        this.printscreen = printscreen == null ? null : printscreen.trim();
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName == null ? null : monitorName.trim();
    }

    public String getInstallationSite() {
        return installationSite;
    }

    public void setInstallationSite(String installationSite) {
        this.installationSite = installationSite == null ? null : installationSite.trim();
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getBlacklistName() {
        return blacklistName;
    }

    public void setBlacklistName(String blacklistName) {
        this.blacklistName = blacklistName == null ? null : blacklistName.trim();
    }

    public String getBlacklistPhone() {
        return blacklistPhone;
    }

    public void setBlacklistPhone(String blacklistPhone) {
        this.blacklistPhone = blacklistPhone == null ? null : blacklistPhone.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime == null ? null : alarmTime.trim();
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
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
        sb.append(", communityId=").append(communityId);
        sb.append(", blacklistId=").append(blacklistId);
        sb.append(", monitorId=").append(monitorId);
        sb.append(", printscreen=").append(printscreen);
        sb.append(", monitorName=").append(monitorName);
        sb.append(", installationSite=").append(installationSite);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", blacklistName=").append(blacklistName);
        sb.append(", blacklistPhone=").append(blacklistPhone);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", alarmDate=").append(alarmDate);
        sb.append(", alarmTime=").append(alarmTime);
        sb.append(", similarity=").append(similarity);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}