package com.bh.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RosterLibrary {
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
     * 脸图
     */
    @ApiModelProperty(value="脸图")
    private String facePic;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String rosterName;

    /**
     * 名单类型，1：黑名单，2：白名单
     */
    @ApiModelProperty(value="名单类型，1：黑名单，2：白名单")
    private Integer rosterType;

    /**
     * 证件号
     */
    @ApiModelProperty(value="证件号")
    private String idNumber;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String rosterPhone;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

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

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic == null ? null : facePic.trim();
    }

    public String getRosterName() {
        return rosterName;
    }

    public void setRosterName(String rosterName) {
        this.rosterName = rosterName == null ? null : rosterName.trim();
    }

    public Integer getRosterType() {
        return rosterType;
    }

    public void setRosterType(Integer rosterType) {
        this.rosterType = rosterType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getRosterPhone() {
        return rosterPhone;
    }

    public void setRosterPhone(String rosterPhone) {
        this.rosterPhone = rosterPhone == null ? null : rosterPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", facePic=").append(facePic);
        sb.append(", rosterName=").append(rosterName);
        sb.append(", rosterType=").append(rosterType);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", rosterPhone=").append(rosterPhone);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}