package com.bh.model.domain;

import java.util.Date;

public class MonitorAids {
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
     * 监控名称
     */
    @ApiModelProperty(value="监控名称")
    private String monitorName;

    /**
     * 监控类型，1：公共梯控，2：梯控监控，3:进出监控
     */
    @ApiModelProperty(value="监控类型，1：公共梯控，2：梯控监控，3:进出监控")
    private Integer monitorType;

    /**
     * 监控账号
     */
    @ApiModelProperty(value="监控账号")
    private String monitorAccount;

    /**
     * 监控密码
     */
    @ApiModelProperty(value="监控密码")
    private String monitorPassword;

    /**
     * 厂商
     */
    @ApiModelProperty(value="厂商")
    private String manufacturer;

    /**
     * 分辨率
     */
    @ApiModelProperty(value="分辨率")
    private String resolutionRatio;

    /**
     * 访问地址
     */
    @ApiModelProperty(value="访问地址")
    private String visitAddress;

    /**
     * 识别内容
     */
    @ApiModelProperty(value="识别内容")
    private String distinguishContent;

    /**
     * 安装位置
     */
    @ApiModelProperty(value="安装位置")
    private String installationSite;

    /**
     * 排序，排序靠前的显示在监控播放框
     */
    @ApiModelProperty(value="排序，排序靠前的显示在监控播放框")
    private Integer sort;

    /**
     * 状态，0：启用，1：禁用
     */
    @ApiModelProperty(value="状态，0：启用，1：禁用")
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

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName == null ? null : monitorName.trim();
    }

    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public String getMonitorAccount() {
        return monitorAccount;
    }

    public void setMonitorAccount(String monitorAccount) {
        this.monitorAccount = monitorAccount == null ? null : monitorAccount.trim();
    }

    public String getMonitorPassword() {
        return monitorPassword;
    }

    public void setMonitorPassword(String monitorPassword) {
        this.monitorPassword = monitorPassword == null ? null : monitorPassword.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getResolutionRatio() {
        return resolutionRatio;
    }

    public void setResolutionRatio(String resolutionRatio) {
        this.resolutionRatio = resolutionRatio == null ? null : resolutionRatio.trim();
    }

    public String getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(String visitAddress) {
        this.visitAddress = visitAddress == null ? null : visitAddress.trim();
    }

    public String getDistinguishContent() {
        return distinguishContent;
    }

    public void setDistinguishContent(String distinguishContent) {
        this.distinguishContent = distinguishContent == null ? null : distinguishContent.trim();
    }

    public String getInstallationSite() {
        return installationSite;
    }

    public void setInstallationSite(String installationSite) {
        this.installationSite = installationSite == null ? null : installationSite.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        sb.append(", monitorName=").append(monitorName);
        sb.append(", monitorType=").append(monitorType);
        sb.append(", monitorAccount=").append(monitorAccount);
        sb.append(", monitorPassword=").append(monitorPassword);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", resolutionRatio=").append(resolutionRatio);
        sb.append(", visitAddress=").append(visitAddress);
        sb.append(", distinguishContent=").append(distinguishContent);
        sb.append(", installationSite=").append(installationSite);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}