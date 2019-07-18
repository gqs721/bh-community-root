package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ReservationRecord {
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
     * 语音id
     */
    @ApiModelProperty(value="语音id")
    private Integer voiceId;

    /**
     * 访客姓名
     */
    @ApiModelProperty(value="访客姓名")
    private String visitorName;

    /**
     * 访客手机
     */
    @ApiModelProperty(value="访客手机")
    private String visitorPhone;

    /**
     * 预约日期
     */
    @ApiModelProperty(value="预约日期")
    private Date reservationDate;

    /**
     * 预约时间
     */
    @ApiModelProperty(value="预约时间")
    private String reservationTime;

    /**
     * 受访住户
     */
    @ApiModelProperty(value="受访住户")
    private String beVisitedTenement;

    /**
     * 受访住户电话
     */
    @ApiModelProperty(value="受访住户电话")
    private String beVisitedPhone;

    /**
     * 受访地址
     */
    @ApiModelProperty(value="受访地址")
    private String beVisitedAddress;

    /**
     * 语音，默认：欢迎到访
     */
    @ApiModelProperty(value="语音，默认：欢迎到访")
    private String visitorVoice;

    /**
     * 审核状态
     */
    @ApiModelProperty(value="审核状态")
    private Integer auditStatus;

    /**
     * 预约状态，0：未接受，1:已接受
     */
    @ApiModelProperty(value="预约状态，0：未接受，1:已接受")
    private Integer reservationStatus;

    /**
     * 预约进展，0：未完成，1：进行中，2：已完成
     */
    @ApiModelProperty(value="预约进展，0：未完成，1：进行中，2：已完成")
    private Integer reservationEvolve;

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

    public Integer getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(Integer voiceId) {
        this.voiceId = voiceId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone == null ? null : visitorPhone.trim();
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime == null ? null : reservationTime.trim();
    }

    public String getBeVisitedTenement() {
        return beVisitedTenement;
    }

    public void setBeVisitedTenement(String beVisitedTenement) {
        this.beVisitedTenement = beVisitedTenement == null ? null : beVisitedTenement.trim();
    }

    public String getBeVisitedPhone() {
        return beVisitedPhone;
    }

    public void setBeVisitedPhone(String beVisitedPhone) {
        this.beVisitedPhone = beVisitedPhone == null ? null : beVisitedPhone.trim();
    }

    public String getBeVisitedAddress() {
        return beVisitedAddress;
    }

    public void setBeVisitedAddress(String beVisitedAddress) {
        this.beVisitedAddress = beVisitedAddress == null ? null : beVisitedAddress.trim();
    }

    public String getVisitorVoice() {
        return visitorVoice;
    }

    public void setVisitorVoice(String visitorVoice) {
        this.visitorVoice = visitorVoice == null ? null : visitorVoice.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Integer getReservationEvolve() {
        return reservationEvolve;
    }

    public void setReservationEvolve(Integer reservationEvolve) {
        this.reservationEvolve = reservationEvolve;
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
        sb.append(", voiceId=").append(voiceId);
        sb.append(", visitorName=").append(visitorName);
        sb.append(", visitorPhone=").append(visitorPhone);
        sb.append(", reservationDate=").append(reservationDate);
        sb.append(", reservationTime=").append(reservationTime);
        sb.append(", beVisitedTenement=").append(beVisitedTenement);
        sb.append(", beVisitedPhone=").append(beVisitedPhone);
        sb.append(", beVisitedAddress=").append(beVisitedAddress);
        sb.append(", visitorVoice=").append(visitorVoice);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", reservationStatus=").append(reservationStatus);
        sb.append(", reservationEvolve=").append(reservationEvolve);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}