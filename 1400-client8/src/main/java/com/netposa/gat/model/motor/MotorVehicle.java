package com.netposa.gat.model.motor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.common.BaseVehicle;
import com.netposa.gat.model.common.FeatureInfoStruc;

import lombok.Data;

/***************************************************************************************************
 ** @author netposa
 ** @Des 1400机动车实体
 ****************************************************************************************************/
@Data
public class MotorVehicle extends BaseVehicle {
    /**
     * 车辆标识 -备注：辆全局唯一标识
     * R -必选
     */
    @JsonProperty("MotorVehicleID")
    private String MotorVehicleID;
    /**
     * 关联卡口编码
     * O -可选
     * */
    @JsonProperty("TollgateID")
    private  String TollgateID;

    /**
     * 近景照片 -卡口相机所拍照片，自动采集必选，图像访问路径采用URI命名规范
     * R -必选
     */
    @JsonProperty("StorageUrl1")
    private String StorageUrl1;
    /**
     * 车辆照片
     * O -可选
     */
    @JsonProperty("StorageUrl2")
    private String StorageUrl2;

    /**
     * 远景照片 全景相机所拍照片
     * O 可选
     */
    @JsonProperty("StorageUrl3")
    private String StorageUrl3;

    /**
     *合成图
     * O 可选
     */
    @JsonProperty("StorageUrl4")
    private String StorageUrl4;
    /**
     * 缩略图
     * O 可选
     */
    @JsonProperty("StorageUrl5")
    private String StorageUrl5;

    /**
     * 车道号 O 可选
     */
    @JsonProperty("LaneNo")
    private String LaneNo;
    /**
     *行驶方向
     */
    @JsonProperty("Direction")
    private String Direction;
    /**
     *车辆类型
     */
    @JsonProperty("VehicleClass")
    private String VehicleClass;
    /**
     *颜色深浅
     */
    @JsonProperty("VehicleColorDepth")
    private String VehicleColorDepth;
    /**
     *撞痕信息
     */
    @JsonProperty("HitMarkInfo")
    private String HitMarkInfo;
    /**
     *车身描述
     */
    @JsonProperty("VehicleBodyDesc")
    private String VehicleBodyDesc;
    /**
     *车前部物品
     *  */
    @JsonProperty("VehicleFrontItem")
    private String VehicleFrontItem;
    /**
     *车前部物品描述
     */
    @JsonProperty("DescOfFrontItem")
    private String DescOfFrontItem;
    /**
     *车后部物品
     */
    @JsonProperty("VehicleRearItem")
    private String VehicleRearItem;
    /**
     *车后部物品描述
     */
    @JsonProperty("DescOfRearItem")
    private String DescOfRearItem;
    /**
     *车内人数
     */
    @JsonProperty("NumOfPassenger")
    private String NumOfPassenger;
    /**
     *经过时刻
     */
    @JsonProperty("PassTime")
    private String PassTime;
    /**
     *经过道路名称
     */
    @JsonProperty("NameOfPassedRoad")
    private String NameOfPassedRoad;
    /**
     *是否可疑车
     *  */
    @JsonProperty("IsSuspicious")
    private Boolean IsSuspicious;
    /**
     *遮阳板状态
     */
    @JsonProperty("Sunvisor")
    private int Sunvisor;
    /**
     *安全带状态
     */
    @JsonProperty("SafetyBelt")
    private int SafetyBelt;
    /**
     *打电话状态
     */
    @JsonProperty("Calling")
    private int Calling;
    /**
     *号牌识别可信度
     *  */
    @JsonProperty("PlateReliability")
    private String PlateReliability;
    /**
     *每位号牌号码可信度
     */
    @JsonProperty("PlateCharReliability")
    private String PlateCharReliability;
    /**
     *品牌标识识别可信度
     */
    @JsonProperty("BrandReliability")
    private String BrandReliability;
    /**
     * 主驾人脸标识
     */
    @JsonProperty("DriverFaceID")
    private String DriverFaceID;
    /**
     * 副驾人脸标识
     */
    @JsonProperty("CopilotFaceID")
    private String CopilotFaceID;

    /**
     * 特征值列表
     */
    @JsonProperty("FeatureList")
    private FeatureInfoStruc FeatureList;

    /**
     * 归属分类标签标识
     */
    @JsonProperty("TabID")
    private String TabID;

    /**
     * 国籍代码
     */
    @JsonProperty("NationalityCode")
    private String NationalityCode;

}
