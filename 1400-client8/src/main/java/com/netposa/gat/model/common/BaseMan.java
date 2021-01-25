package com.netposa.gat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/***************************************************************************************************
 ** @author netposa
 ** @Des 1400 标准 人脸人体基类
 ****************************************************************************************************/
@Data
public abstract class BaseMan {
    /**
     * 信息分类
     */
    @JsonProperty("InfoKind")
    private String InfoKind;
    /**
     * 来源标识
     */
    @JsonProperty("SourceID")
    private String SourceID;
    /**
     * 设备编码
     */
    @JsonProperty("DeviceID")
    private String DeviceID;
    /**
     * 左上角X坐标
     */
    @JsonProperty("LeftTopX")
    private int LeftTopX;
    /**
     * 左上角Y坐标
     */
    @JsonProperty("LeftTopY")
    private int LeftTopY;
    /**
     * 右下角X坐标
     */
    @JsonProperty("RightBtmX")
    private int RightBtmX;
    /**
     * 右下角Y坐标
     */
    @JsonProperty("RightBtmY")
    private int RightBtmY;
    /**
     * 位置标记时间
     */
    @JsonProperty("LocationMarkTime")
    private String LocationMarkTime;
    /**
     * 证件种类
     */
    @JsonProperty("IDType")
    private String IDType;
    /**
     * 证件号码
     */
    @JsonProperty("IDNumber")
    private String IDNumber;
    /**
     * 姓名
     */
    @JsonProperty("Name")
    private String Name;
    /**
     * 曾用名
     */
    @JsonProperty("UsedName")
    private String UsedName;
    /**
     * 绰号
     */
    @JsonProperty("Alias")
    private String Alias;
    /**
     * 性别代码
     */
    @JsonProperty("GenderCode")
    private String GenderCode;
    /**
     * 年龄上限
     */
    @JsonProperty("AgeUpLimit")
    private int AgeUpLimit;
    /**
     * 年龄下限
     */
    @JsonProperty("AgeLowerLimit")
    private int AgeLowerLimit;
    /**
     * 民族代码
     */
    @JsonProperty("EthicCode")
    private String EthicCode;
    /**
     * 国籍代码
     */
    @JsonProperty("NationalityCode")
    private String NationalityCode;
    /**
     * 籍贯省市县代码
     */
    @JsonProperty("NativeCityCode")
    private String NativeCityCode;
    /**
     * 居住地行政区划
     */
    @JsonProperty("ResidenceAdminDivision")
    private String ResidenceAdminDivision;
    /**
     * 汉语口音代码
     */
    @JsonProperty("ChineseAccentCode")
    private String ChineseAccentCode;
    /**
     * 单位名称
     */
    @JsonProperty("PersonOrg")
    private String PersonOrg;
    /**
     * 职业类别代码
     */
    @JsonProperty("JobCategory")
    private String JobCategory;
    /**
     * 同行人数
     */
    @JsonProperty("AccompanyNumber")
    private int AccompanyNumber;
    /**
     * 肤色
     */
    @JsonProperty("SkinColor")
    private String SkinColor;
    /**
     * 发型
     */
    @JsonProperty("HairStyle")
    private String HairStyle;
    /**
     *
     */
    @JsonProperty("HairType")
    private String HairType;
    /**
     * 发色
     */
    @JsonProperty("HairColor")
    private String HairColor;
    /**
     * 脸型
     */
    @JsonProperty("FaceStyle")
    private String FaceStyle;
    /**
     * 脸部特征
     */
    @JsonProperty("FacialFeature")
    private String FacialFeature;
    /**
     * 体貌特征
     */
    @JsonProperty("PhysicalFeature")
    private String PhysicalFeature;
    /**
     * 口罩颜色
     */
    @JsonProperty("RespiratorColor")
    private String RespiratorColor;
    /**
     * 帽子款式
     */
    @JsonProperty("CapStyle")
    private String CapStyle;
    /**
     * 帽子颜色
     */
    @JsonProperty("CapColor")
    private String CapColor;
    /**
     * 眼镜款式
     */
    @JsonProperty("GlassStyle")
    private String GlassStyle;
    /**
     * 眼镜颜色
     */
    @JsonProperty("GlassColor")
    private String GlassColor;
    /**
     * 是否驾驶员
     */
    @JsonProperty("IsDriver")
    private int IsDriver;
    /**
     * 是否涉外人员
     */
    @JsonProperty("IsForeigner")
    private int IsForeigner;
    /**
     * 护照证件种类
     */
    @JsonProperty("PassportType")
    private String PassportType;
    /**
     * 出入境人员类别编码
     */
    @JsonProperty("ImmigrantTypeCode")
    private String ImmigrantTypeCode;
    /**
     * 是否涉恐人员
     */
    @JsonProperty("IsSuspectedTerrorist")
    private int IsSuspectedTerrorist;
    /**
     * 涉恐人员编号
     */
    @JsonProperty("SuspectedTerroristNumber")
    private String SuspectedTerroristNumber;
    /**
     * 是否涉案人员
     */
    @JsonProperty("IsCriminalInvolved")
    private int IsCriminalInvolved;
    /**
     * 涉案人员专长代码
     */
    @JsonProperty("CriminalInvolvedSpecilisationCodeType")
    private String CriminalInvolvedSpecilisationCodeType;
    /**
     * 体表特殊标记
     */
    @JsonProperty("BodySpecialMark")
    private String BodySpecialMark;
    /**
     * 作案手段
     */
    @JsonProperty("CrimeMethod")
    private String CrimeMethod;
    /**
     * 作案特点代码
     */
    @JsonProperty("CrimeCharacterCode")
    private String CrimeCharacterCode;
    /**
     * 在逃人员编号
     */
    @JsonProperty("EscapedCriminalNumber")
    private String EscapedCriminalNumber;
    /**
     * 是否在押人员
     */
    @JsonProperty("IsDetainees")
    private int IsDetainees;
    /**
     * 看守所编码
     */
    @JsonProperty("DetentionHouseCode")
    private String DetentionHouseCode;
    /**
     * 在押人员身份
     */
    @JsonProperty("DetaineesIdentity")
    private String DetaineesIdentity;
    /**
     * 在押人员特殊身份
     */
    @JsonProperty("DetaineesSpecialIdentity")
    private String DetaineesSpecialIdentity;
    /**
     * 成员类型代码
     */
    @JsonProperty("MemberTypeCode")
    private String MemberTypeCode;
    /**
     * 是否被害人
     */
    @JsonProperty("IsVictim")
    private int IsVictim;
    /**
     * 被害人种类
     */
    @JsonProperty("VictimType")
    private String VictimType;
    /**
     * 受伤害程度
     */
    @JsonProperty("InjuredDegree")
    private String InjuredDegree;
    /**
     * 尸体状况代码
     */
    @JsonProperty("CorpseConditionCode")
    private String CorpseConditionCode;
    /**
     * 是否可疑人
     */
    @JsonProperty("IsSuspiciousPserson")
    private int IsSuspiciousPserson;
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
     * 拍摄时间
     */
    @JsonProperty("ShotTime")
    private String ShotTime;
}
