package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PropertyManagement {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 物业logo
     */
    @ApiModelProperty(value="物业logo")
    private String pmLogo;

    /**
     * 物业名称
     */
    @ApiModelProperty(value="物业名称")
    private String pmName;

    /**
     * 审核状态，0:待审核，1：审核通过，2：审核不通过
     */
    @ApiModelProperty(value="审核状态，0:待审核，1：审核通过，2：审核不通过")
    private Integer auditStatus;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private Integer status;

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

    public String getPmLogo() {
        return pmLogo;
    }

    public void setPmLogo(String pmLogo) {
        this.pmLogo = pmLogo == null ? null : pmLogo.trim();
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName == null ? null : pmName.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", pmLogo=").append(pmLogo);
        sb.append(", pmName=").append(pmName);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}