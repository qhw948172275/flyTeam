package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_flight_report")
public class FlightReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @Column(name = "create_id")
    private Integer createId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 输入日期
     */
    @ApiModelProperty(value = "输入日期")
    @Column(name = "input_time")
    private String inputTime;

    /**
     * 飞行型号
     */
    @ApiModelProperty(value = "飞行型号")
    @Column(name = "flight_model")
    private String flightModel;

    /**
     * 航班号
     */
    @ApiModelProperty(value = "航班号")
    @Column(name = "flight_number")
    private String flightNumber;
    /**
     * 提交人
     */
    @ApiModelProperty(value = "提交人")
    private String submitter;

    /**
     * 航线
     */
    @ApiModelProperty(value = "航线")
    private String route;
    /**
     * 出发地
     */
    @ApiModelProperty(value = "出发地")
    @Column(name = "base_place")
    private String basePlace;
    /**
     * 目的地
     */
    @ApiModelProperty(value="目的地")
    private String destination;
    /**
     * 左座人员姓名
     */
    @ApiModelProperty(value = "左座人员姓名")
    @Column(name = "left_seat_member_name")
    private String leftSeatMemberName;

    /**
     * 右座人员姓名
     */
    @ApiModelProperty(value = "右座人员姓名")
    @Column(name = "right_seat_member_name")
    private String rightSeatMemberName;

    /**
     * 天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他
     */
    @ApiModelProperty(value = "天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，5-结冰，6-乱流，7-风切边，8-其他")
    @Column(name = "weather_condition")
    private String weatherCondition;

    /**
     * 天气情况其他说明
     */
    @ApiModelProperty(value = "天气情况其他说明")
    @Column(name = "weather_condition_ramark")
    private String weatherConditionRamark;

    /**
     * 飞行影响:0-轻微，1-轻度，2-中度，3-严重
     */
    @ApiModelProperty(value = "飞行影响:0-轻微，1-轻度，2-中度，3-严重")
    @Column(name = "flight_impact")
    private Byte flightImpact;

    /**
     * 事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他
     */
    @ApiModelProperty(value = "事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他")
    @Column(name = "event_stage")
    private Byte eventStage;

    /**
     * 事件发生阶段备注
     */
    @ApiModelProperty(value = "事件发生阶段备注")
    @Column(name = "event_stage_remarke")
    private String eventStageRemarke;

    /**
     * 飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障
     */
    @ApiModelProperty(value = "飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障")
    @Column(name = "flight_fault")
    private String flightFault;

    /**
     * 飞机故障的其他故障备注
     */
    @ApiModelProperty(value = "飞机故障的其他故障备注")
    @Column(name = "flight_fault_remark")
    private String flightFaultRemark;

    /**
     * 是否填写飞行记录：0-未填写，1-填写
     */
    @ApiModelProperty(value = "是否填写飞行记录：0-未填写，1-填写")
    @Column(name = "is_flight_record")
    private Byte isFlightRecord;

    /**
     * 事件经过及可能原因
     */
    @ApiModelProperty(value = "事件经过及可能原因")
    @Column(name = "event_content")
    private String eventContent;

    /**
     * 0-启用你，1-禁用
     */
    @ApiModelProperty(value = "0-启用，1-禁用")
    private Byte status;

    /**
     * 其他成员姓名
     */
    @ApiModelProperty(value = "其他成员姓名")
    @Column(name = "other_member_names")
    private String otherMemberNames;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建人ID
     *
     * @return create_id - 创建人ID
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置创建人ID
     *
     * @param createId 创建人ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取输入日期
     *
     * @return input_time - 输入日期
     */
    public String getInputTime() {
        return inputTime;
    }

    /**
     * 设置输入日期
     *
     * @param inputTime 输入日期
     */
    public void setInputTime(String inputTime) {
        this.inputTime = inputTime == null ? null : inputTime.trim();
    }

    /**
     * 获取飞行型号
     *
     * @return flight_model - 飞行型号
     */
    public String getFlightModel() {
        return flightModel;
    }

    /**
     * 设置飞行型号
     *
     * @param flightModel 飞行型号
     */
    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel == null ? null : flightModel.trim();
    }

    /**
     * 获取航班号
     *
     * @return flight_number - 航班号
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 设置航班号
     *
     * @param flightNumber 航班号
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber == null ? null : flightNumber.trim();
    }

    /**
     * 获取航线
     *
     * @return route - 航线
     */
    public String getRoute() {
        return route;
    }

    /**
     * 设置航线
     *
     * @param route 航线
     */
    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    /**
     * 获取左座人员姓名
     *
     * @return left_seat_member_name - 左座人员姓名
     */
    public String getLeftSeatMemberName() {
        return leftSeatMemberName;
    }

    /**
     * 设置左座人员姓名
     *
     * @param leftSeatMemberName 左座人员姓名
     */
    public void setLeftSeatMemberName(String leftSeatMemberName) {
        this.leftSeatMemberName = leftSeatMemberName == null ? null : leftSeatMemberName.trim();
    }

    /**
     * 获取右座人员姓名
     *
     * @return right_seat_member_name - 右座人员姓名
     */
    public String getRightSeatMemberName() {
        return rightSeatMemberName;
    }

    /**
     * 设置右座人员姓名
     *
     * @param rightSeatMemberName 右座人员姓名
     */
    public void setRightSeatMemberName(String rightSeatMemberName) {
        this.rightSeatMemberName = rightSeatMemberName == null ? null : rightSeatMemberName.trim();
    }

    /**
     * 获取天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他
     *
     * @return weather_condition - 天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他
     */
    public String getWeatherCondition() {
        return weatherCondition;
    }

    /**
     * 设置天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他
     *
     * @param weatherCondition 天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他
     */
    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    /**
     * 获取天气情况其他说明
     *
     * @return weather_condition_ramark - 天气情况其他说明
     */
    public String getWeatherConditionRamark() {
        return weatherConditionRamark;
    }

    /**
     * 设置天气情况其他说明
     *
     * @param weatherConditionRamark 天气情况其他说明
     */
    public void setWeatherConditionRamark(String weatherConditionRamark) {
        this.weatherConditionRamark = weatherConditionRamark == null ? null : weatherConditionRamark.trim();
    }

    /**
     * 获取飞行影响:0-轻微，1-轻度，2-中度，3-严重
     *
     * @return flight_impact - 飞行影响:0-轻微，1-轻度，2-中度，3-严重
     */
    public Byte getFlightImpact() {
        if(this.flightImpact==null){
            return (byte)0;
        }
        return flightImpact;
    }

    /**
     * 设置飞行影响:0-轻微，1-轻度，2-中度，3-严重
     *
     * @param flightImpact 飞行影响:0-轻微，1-轻度，2-中度，3-严重
     */
    public void setFlightImpact(Byte flightImpact) {
        this.flightImpact = flightImpact;
    }

    /**
     * 获取事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他
     *
     * @return event_stage - 事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他
     */
    public Byte getEventStage() {
        if(this.eventStage==null){
            return (byte)0;
        }
        return eventStage;
    }

    /**
     * 设置事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他
     *
     * @param eventStage 事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他
     */
    public void setEventStage(Byte eventStage) {
        this.eventStage = eventStage;
    }

    /**
     * 获取事件发生阶段备注
     *
     * @return event_stage_remarke - 事件发生阶段备注
     */
    public String getEventStageRemarke() {
        return eventStageRemarke;
    }

    /**
     * 设置事件发生阶段备注
     *
     * @param eventStageRemarke 事件发生阶段备注
     */
    public void setEventStageRemarke(String eventStageRemarke) {
        this.eventStageRemarke = eventStageRemarke == null ? null : eventStageRemarke.trim();
    }

    /**
     * 获取飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障
     *
     * @return flight_fault - 飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障
     */
    public String getFlightFault() {
        return flightFault;
    }

    /**
     * 设置飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障
     *
     * @param flightFault 飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障
     */
    public void setFlightFault(String flightFault) {
        this.flightFault = flightFault == null ? null : flightFault.trim();
    }

    /**
     * 获取飞机故障的其他故障备注
     *
     * @return flight_fault_remark - 飞机故障的其他故障备注
     */
    public String getFlightFaultRemark() {
        return flightFaultRemark;
    }

    /**
     * 设置飞机故障的其他故障备注
     *
     * @param flightFaultRemark 飞机故障的其他故障备注
     */
    public void setFlightFaultRemark(String flightFaultRemark) {
        this.flightFaultRemark = flightFaultRemark == null ? null : flightFaultRemark.trim();
    }

    /**
     * 获取是否填写飞行记录：0-未填写，1-填写
     *
     * @return is_flight_record - 是否填写飞行记录：0-未填写，1-填写
     */
    public Byte getIsFlightRecord() {
        if(this.isFlightRecord==null){
            return (byte)0;
        }
        return isFlightRecord;
    }

    /**
     * 设置是否填写飞行记录：0-未填写，1-填写
     *
     * @param isFlightRecord 是否填写飞行记录：0-未填写，1-填写
     */
    public void setIsFlightRecord(Byte isFlightRecord) {
        this.isFlightRecord = isFlightRecord;
    }

    /**
     * 获取事件经过及可能原因
     *
     * @return event_content - 事件经过及可能原因
     */
    public String getEventContent() {
        return eventContent;
    }

    /**
     * 设置事件经过及可能原因
     *
     * @param eventContent 事件经过及可能原因
     */
    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    /**
     * 获取0-启用你，1-禁用
     *
     * @return status - 0-启用你，1-禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-启用你，1-禁用
     *
     * @param status 0-启用你，1-禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取其他成员姓名
     *
     * @return other_member_names - 其他成员姓名
     */
    public String getOtherMemberNames() {
        return otherMemberNames;
    }

    /**
     * 设置其他成员姓名
     *
     * @param otherMemberNames 其他成员姓名
     */
    public void setOtherMemberNames(String otherMemberNames) {
        this.otherMemberNames = otherMemberNames == null ? null : otherMemberNames.trim();
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getBasePlace() {
        return basePlace;
    }

    public void setBasePlace(String basePlace) {
        this.basePlace = basePlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}