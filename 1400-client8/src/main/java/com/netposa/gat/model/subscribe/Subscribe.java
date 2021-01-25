package com.netposa.gat.model.subscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author netposa
 * @desc 订阅消息
 */
@Data
public class Subscribe implements Serializable {
	
    private static final long serialVersionUID = -7898194272883238670L;

    /**
     * UUID，作为主键使用
     */
    @JsonProperty("ID")
    @JsonIgnoreProperties("ID")
    private String ID;

    @JsonProperty("viewDeviceId")
	private String viewDeviceId;
	
	@JsonProperty("viewDeviceName")
	private String viewDeviceName;
	

    /**
     * 订阅标识符
     */
    @JsonProperty("SubscribeID")
    @JsonIgnoreProperties("subscribeID")
    private String SubscribeID;

    /**
     * 订阅标题
     */
    @JsonProperty("Title")
    @JsonIgnoreProperties("title")
    private String Title;

    /**
     * 订阅类别
     * 1:案事件目录
     * 2:单个案事件内容
     * 3:采集设备目录
     * 4:采集设备状态
     * 5:采集系统目录
     * 6:采集系统状态
     * 7:视频卡口目录
     * 8:单个卡口目录
     * 9:车道目录
     * 10:单个车道目录
     * 11:自动采集的人员信息
     * 12:自动采集的人脸信息
     * 13:自动采集的车辆信息
     * 14:自动采集的非机动车辆信息
     * 15:自动采集的物品信息
     * 16:自动采集的文件信息
     */
    @JsonProperty("SubscribeDetail")
    @JsonIgnoreProperties("subscribeDetail")
    private String SubscribeDetail;

    /**
     * 订阅资源路径
     */
    @JsonProperty("ResourceURI")
    @JsonIgnoreProperties("resourceURI")
    private String ResourceURI;
    

    /**
     * 申请人
     */
    @JsonProperty("ApplicantName")
    @JsonIgnoreProperties("applicantName")
    private String ApplicantName;

    /**
     * 申请单位
     */
    @JsonProperty("ApplicantOrg")
    @JsonIgnoreProperties("applicantOrg")
    private String ApplicantOrg;

    /**
     * 开始时间
     */
    @JsonProperty("BeginTime")
    @JsonIgnoreProperties("beginTime")
    private String BeginTime;

    /**
     * 结束时间
     */
    @JsonProperty("EndTime")
    @JsonIgnoreProperties("endTime")
    private String EndTime;

    /**
     * 信息接收地址
     */
    @JsonProperty("ReceiveAddr")
    @JsonIgnoreProperties("receiveAddr")
    private String ReceiveAddr;

    /**
     * 信息上报时间间隔,<=0表示不限制
     */
    @JsonProperty("ReportInterval")
    @JsonIgnoreProperties("reportInterval")
    private String ReportInterval;

    /**
     * 理由
     */
    @JsonProperty("Reason")
    @JsonIgnoreProperties("reason")
    private String Reason;

    /**
     * 操作类型，0，订阅 1，取消订阅
     */
    @JsonProperty("OperateType")
    @JsonIgnoreProperties("operateType")
    private String OperateType;

    /**
     * 订阅执行状态
     * 0：订阅中 1：已取消订阅 2：订阅到期 9：未订阅 该字段只读
     */
    @JsonProperty("SubscribeStatus")
    @JsonIgnoreProperties("subscribeStatus")
    private String SubscribeStatus;

    /**
     * 订阅取消单位
     */
    @JsonProperty("SubscribeCancelOrg")
    @JsonIgnoreProperties("subscribeCancelOrg")
    private String SubscribeCancelOrg;

    /**
     * 订阅取消人
     */
    @JsonProperty("SubscribeCancelPerson")
    @JsonIgnoreProperties("subscribeCancelPerson")
    private String SubscribeCancelPerson;

    /**
     * 取消时间
     */
    @JsonProperty("CancelTime")
    @JsonIgnoreProperties("cancelTime")
    private String CancelTime;

    /**
     * 取消原因
     */
    @JsonProperty("CancelReason")
    @JsonIgnoreProperties("cancelReason")
    private String CancelReason;

    /**
     * 返回结果图片约定
     * -1 不要图片 01车辆小图 02车牌彩色小图 03车牌二值化图 04驾驶员面部特征图
     * 05 副驾驶面部特征图 06 车标 07 违章合成图 08 过车合成图 09 车辆特写图
     * 10 人员图 11 人脸图 12 非机动车图 13 物品图 14 场景图 100 一般图片
     */
    @JsonProperty("ResultImageDeclare")
    @JsonIgnoreProperties("resultImageDeclare")
    private String ResultImageDeclare;

    /**
     * 返回结果特征约定
     * -1 不要特征值 1 需要返回特征值
     */
    @JsonProperty("ResultFeatureDeclare")
    @JsonIgnoreProperties("resultFeatureDeclare")
    private int ResultFeatureDeclare;

    /**
     * 订阅分类标签标识
     */
    @JsonProperty("TabID")
    @JsonIgnoreProperties("tabID")
    private String TabID="";

}
