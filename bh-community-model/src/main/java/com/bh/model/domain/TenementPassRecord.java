package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TenementPassRecord {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 住户id
     */
    @ApiModelProperty(value="住户id")
    private Integer tenementId;

    /**
     * 监控设备id
     */
    @ApiModelProperty(value="监控设备id")
    private Integer monitorId;

    /**
     * 脸图
     */
    @ApiModelProperty(value="脸图")
    private String tenementPic;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String tenementName;

    /**
     * 住址
     */
    @ApiModelProperty(value="住址")
    private String address;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
     * 住户类型
     */
    @ApiModelProperty(value="住户类型")
    private Integer tenementType;

    /**
     * 日期
     */
    @ApiModelProperty(value="日期")
    private Date passDate;

    /**
     * 进出时间
     */
    @ApiModelProperty(value="进出时间")
    private String passTime;

    /**
     * 设备地址
     */
    @ApiModelProperty(value="设备地址")
    private String passAddress;

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

    public Integer getTenementId() {
        return tenementId;
    }

    public void setTenementId(Integer tenementId) {
        this.tenementId = tenementId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getTenementPic() {
        return tenementPic;
    }

    public void setTenementPic(String tenementPic) {
        this.tenementPic = tenementPic == null ? null : tenementPic.trim();
    }

    public String getTenementName() {
        return tenementName;
    }

    public void setTenementName(String tenementName) {
        this.tenementName = tenementName == null ? null : tenementName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getTenementType() {
        return tenementType;
    }

    public void setTenementType(Integer tenementType) {
        this.tenementType = tenementType;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime == null ? null : passTime.trim();
    }

    public String getPassAddress() {
        return passAddress;
    }

    public void setPassAddress(String passAddress) {
        this.passAddress = passAddress == null ? null : passAddress.trim();
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
        sb.append(", tenementId=").append(tenementId);
        sb.append(", monitorId=").append(monitorId);
        sb.append(", tenementPic=").append(tenementPic);
        sb.append(", tenementName=").append(tenementName);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", tenementType=").append(tenementType);
        sb.append(", passDate=").append(passDate);
        sb.append(", passTime=").append(passTime);
        sb.append(", passAddress=").append(passAddress);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}