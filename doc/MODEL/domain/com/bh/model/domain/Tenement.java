package com.bh.model.domain;

import java.util.Date;

public class Tenement {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 楼栋id
     */
    @ApiModelProperty(value="楼栋id")
    private Integer buildId;

    /**
     * 语音id
     */
    @ApiModelProperty(value="语音id")
    private Integer voiceId;

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
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
     * 所属分期
     */
    @ApiModelProperty(value="所属分期")
    private String byStaging;

    /**
     * 所属楼栋
     */
    @ApiModelProperty(value="所属楼栋")
    private String building;

    /**
     * 所属单元
     */
    @ApiModelProperty(value="所属单元")
    private String unit;

    /**
     * 住户类型，1：业主，2：租户
     */
    @ApiModelProperty(value="住户类型，1：业主，2：租户")
    private Integer tenementType;

    /**
     * 房号
     */
    @ApiModelProperty(value="房号")
    private Integer roomNumber;

    /**
     * 欢迎语
     */
    @ApiModelProperty(value="欢迎语")
    private String welcomeInfo;

    /**
     * 语音
     */
    @ApiModelProperty(value="语音")
    private String voice;

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

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(Integer voiceId) {
        this.voiceId = voiceId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getByStaging() {
        return byStaging;
    }

    public void setByStaging(String byStaging) {
        this.byStaging = byStaging == null ? null : byStaging.trim();
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getTenementType() {
        return tenementType;
    }

    public void setTenementType(Integer tenementType) {
        this.tenementType = tenementType;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getWelcomeInfo() {
        return welcomeInfo;
    }

    public void setWelcomeInfo(String welcomeInfo) {
        this.welcomeInfo = welcomeInfo == null ? null : welcomeInfo.trim();
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice == null ? null : voice.trim();
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
        sb.append(", buildId=").append(buildId);
        sb.append(", voiceId=").append(voiceId);
        sb.append(", tenementPic=").append(tenementPic);
        sb.append(", tenementName=").append(tenementName);
        sb.append(", phone=").append(phone);
        sb.append(", byStaging=").append(byStaging);
        sb.append(", building=").append(building);
        sb.append(", unit=").append(unit);
        sb.append(", tenementType=").append(tenementType);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", welcomeInfo=").append(welcomeInfo);
        sb.append(", voice=").append(voice);
        sb.append(", status=").append(status);
        sb.append(", delStatus=").append(delStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}