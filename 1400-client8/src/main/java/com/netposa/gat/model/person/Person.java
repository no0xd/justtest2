package com.netposa.gat.model.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.common.BaseMan;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 人员信息结构
 * User: netposa
 */
@Data
public class Person extends BaseMan {
    /**
     * 人员标识
     */
    @JsonProperty("PersonID")
    private String PersonID;
    /**
     * 人员出现时间
     */
    @JsonProperty("PersonAppearTime")
    private String PersonAppearTime;
    /**
     * 人员消失时间
     */
    @JsonProperty("PersonDisAppearTime")
    private String PersonDisAppearTime;
    /**
     * 身高上限
     */
    @JsonProperty("HeightUpLimit")
    private int HeightUpLimit;
    /**
     * 身高下限
     */
    @JsonProperty("HeightLowerLimit")
    private int HeightLowerLimit;
    /**
     * 体型
     */
    @JsonProperty("BodyType")
    private String BodyType;
    /**
     * 姿态
     */
    @JsonProperty("Gesture")
    private String Gesture;
    /**
     * 状态
     */
    @JsonProperty("Status")
    private String Status;
    /**
     * 体表特征
     */
    @JsonProperty("BodyFeature")
    private String BodyFeature;
    /**
     * 习惯动作
     */
    @JsonProperty("HabitualMovement")
    private String HabitualMovement;
    /**
     * 行为
     */
    @JsonProperty("Behavior")
    private String Behavior;
    /**
     * 行为描述
     */
    @JsonProperty("BehaviorDescription")
    private String BehaviorDescription;
    /**
     * 附属物
     */
    @JsonProperty("Appendant")
    private String Appendant;
    /**
     * 附属物描述
     */
    @JsonProperty("AppendantDescription")
    private String AppendantDescription;
    /**
     * 伞颜色
     */
    @JsonProperty("UmbrellaColor")
    private String UmbrellaColor;
    /**
     * 围巾颜色
     */
    @JsonProperty("ScarfColor")
    private String ScarfColor;
    /**
     * 包款式
     */
    @JsonProperty("BagStyle")
    private String BagStyle;
    /**
     * 包颜色
     */
    @JsonProperty("BagColor")
    private String BagColor;
    /**
     * 上衣款式
     */
    @JsonProperty("CoatStyle")
    private String CoatStyle;
    /**
     * 上衣长度
     */
    @JsonProperty("CoatLength")
    private String CoatLength;
    /**
     * 上衣颜色
     */
    @JsonProperty("CoatColor")
    private String CoatColor;
    /**
     * 裤子款式
     */
    @JsonProperty("TrousersStyle")
    private String TrousersStyle;
    /**
     * 裤子颜色
     */
    @JsonProperty("TrousersColor")
    private String TrousersColor;
    /**
     * 裤子长度
     */
    @JsonProperty("TrousersLen")
    private String TrousersLen;
    /**
     * 鞋子款式
     */
    @JsonProperty("ShoesStyle")
    private String ShoesStyle;
    /**
     * 鞋子颜色
     */
    @JsonProperty("ShoesColor")
    private String ShoesColor;
    /**
     * 人脸标识
     */
    @JsonProperty("FaceID")
    private String FaceID;
    /**
     * 非机动车标识
     */
    @JsonProperty("NonMotorVehicleID")
    private String NonMotorVehicleID;




}
