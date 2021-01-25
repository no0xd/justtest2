package com.netposa.gat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/***************************************************************************************************
 ** @author netposa
 ** @Des 1400标准车辆基类
 ****************************************************************************************************/
@Data
public  abstract class BaseVehicle {
    /**
     * 信息分类 -备注：人工采集还是自动采集
     * R -
     */
    @JsonProperty("InfoKind")
    private String InfoKind;

    /**
     * 来源标识 -备注：来源图像标识
     * R -必选
     */
    @JsonProperty("SourceID")
    private String SourceID;

    /**
     * 设备编码
     * R/O -自动采集时必选
     */
    @JsonProperty("DeviceID")
    private String DeviceID;

    /**
     * 左上角X坐标 车的轮廓外界矩形在画面中的位置，记录坐标
     * R/O 自动采集时必选
     * */
    @JsonProperty("LeftTopX")
    private int LeftTopX;

    /**
     * 左上角Y坐标 车的轮廓外界矩形在画面中的位置，记录坐标
     * R/O 自动采集时必选
     * */
    @JsonProperty("LeftTopY")
    private int LeftTopY;

    /**
     * 右上角X坐标 车的轮廓外界矩形在画面中的位置，记录坐标
     * R/O 自动采集时必选
     * */
    @JsonProperty("RightBtmX")
    private int RightBtmX;

    /**
     * 右上角Y坐标 车的轮廓外界矩形在画面中的位置，记录坐标
     * R/O 自动采集时必选
     * */
    @JsonProperty("RightBtmY")
    private int RightBtmY;

    /**
     * 位置标记时间 O 人工采集时有效
     */
    @JsonProperty("MarkTime")
    private String MarkTime;
    /**
     * 车辆出现时间 O 人工采集时有效
     */
    @JsonProperty("AppearTime")
    private String AppearTime;
    /**
     * 车辆消失时间 O 人工采集时有效
     */
    @JsonProperty("DisappearTime")
    private String DisappearTime;

    /**
     * 有无车牌号
     */
    @JsonProperty("HasPlate")
    private int HasPlate;

    /**
     * 号牌种类
     */
    @JsonProperty("PlateClass")
    private String PlateClass;

    /**
     * 车牌颜色
     */
    @JsonProperty("PlateColor")
    private String PlateColor;
    /**
     * 车牌号
     */
    @JsonProperty("PlateNo")
    private String PlateNo;
    /**
     *挂车牌号
     */
    @JsonProperty("PlateNoAttach")
    private String PlateNoAttach;
    /**
     *车牌描述
     */
    @JsonProperty("PlateDescribe")
    private String PlateDescribe;
    /**
     *是否套牌
     */
    @JsonProperty("IsDecked")
    private Boolean IsDecked;
    /**
     *是否涂改
     */
    @JsonProperty("IsAltered")
    private Boolean IsAltered;
    /**
     *是否遮挡
     */
    @JsonProperty("IsCovered")
    private Boolean IsCovered;
    /**
     *行驶速度
     */
    @JsonProperty("Speed")
    private String Speed;

    /**
     *行驶状态代码
     */
    @JsonProperty("DrivingStatusCode")
    private String DrivingStatusCode;
    /**
     *车辆使用性质代码
     */
    @JsonProperty("UsingPropertiesCode")
    private String UsingPropertiesCode;

    /**
     *车辆品牌
     */
    @JsonProperty("VehicleBrand")
    private String VehicleBrand;
    /**
     *车辆型号
     */
    @JsonProperty("VehicleModel")
    private String VehicleModel;
    /**
     *车辆年款
     */
    @JsonProperty("VehicleStyles")
    private String VehicleStyles;
    /**
     *车辆长度
     */
    @JsonProperty("VehicleLength")
    private String VehicleLength;
    /**
     *车辆宽度
     */
    @JsonProperty("VehicleWidth")
    private String VehicleWidth;
    /**
     *车辆高度
     */
    @JsonProperty("VehicleHeight")
    private String VehicleHeight;
    /**
     *车身颜色 R 必选
     */
    @JsonProperty("VehicleColor")
    private String VehicleColor;

    /**
     *车前盖
     */
    @JsonProperty("VehicleHood")
    private String VehicleHood;
    /**
     *车后盖
     */
    @JsonProperty("VehicleTrunk")
    private String VehicleTrunk;
    /**
     *车轮
     */
    @JsonProperty("VehicleWheel")
    private String VehicleWheel;
    /**
     *车轮印花纹
     */
    @JsonProperty("WheelPrintedPattern")
    private String WheelPrintedPattern;
    /**
     *车窗
     *  */
    @JsonProperty("VehicleWindow")
    private String VehicleWindow;
    /**
     *车顶
     */
    @JsonProperty("VehicleRoof")
    private String VehicleRoof;
    /**
     *车门
     */
    @JsonProperty("VehicleDoor")
    private String VehicleDoor;
    /**
     *车侧
     */
    @JsonProperty("SideOfVehicle")
    private String SideOfVehicle;
    /**
     *车厢
     */
    @JsonProperty("CarOfVehicle")
    private String CarOfVehicle;
    /**
     *后视镜
     */
    @JsonProperty("RearviewMirror")
    private String RearviewMirror;
    /**
     *底盘
     */
    @JsonProperty("VehicleChassis")
    private String VehicleChassis;
    /**
     *遮挡
     */
    @JsonProperty("VehicleShielding")
    private String VehicleShielding;
    /**
     *贴膜颜色
     */
    @JsonProperty("FilmColor")
    private String FilmColor;
    /**
     *改装标志
     */
    @JsonProperty("IsModified")
    private Boolean IsModified;
    /**
     * 图像列表
     */
    @JsonProperty("SubImageList")
    private SubImageInfoStruc SubImageList;
    /**
     * 大图（场景图）路径
     */
    @JsonProperty("StorageURL")
    private String StorageURL;
}
