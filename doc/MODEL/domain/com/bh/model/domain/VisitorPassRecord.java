package com.bh.model.domain;

import java.util.Date;

public class VisitorPassRecord {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 预约id
     */
    @ApiModelProperty(value="预约id")
    private Integer reservationId;

    /**
     * 监控设备id
     */
    @ApiModelProperty(value="监控设备id")
    private Integer monitorId;

    /**
     * 访客姓名
     */
    @ApiModelProperty(value="访客姓名")
    private String visitorName;

    /**
     * 访客电话
     */
    @ApiModelProperty(value="访客电话")
    private String visitorPhone;

    /**
     * 进出地址
     */
    @ApiModelProperty(value="进出地址")
    private String passAddress;

    /**
     * 进出日期
     */
    @ApiModelProperty(value="进出日期")
    private String passDate;

    /**
     * 进出时间
     */
    @ApiModelProperty(value="进出时间")
    private String passTime;

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

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
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

    public String getPassAddress() {
        return passAddress;
    }

    public void setPassAddress(String passAddress) {
        this.passAddress = passAddress == null ? null : passAddress.trim();
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate == null ? null : passDate.trim();
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime == null ? null : passTime.trim();
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
        sb.append(", reservationId=").append(reservationId);
        sb.append(", monitorId=").append(monitorId);
        sb.append(", visitorName=").append(visitorName);
        sb.append(", visitorPhone=").append(visitorPhone);
        sb.append(", passAddress=").append(passAddress);
        sb.append(", passDate=").append(passDate);
        sb.append(", passTime=").append(passTime);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}