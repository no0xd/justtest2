package com.netposa.gat.model.face;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.common.BaseMan;

import lombok.Data;

/***************************************************************************************************
 ** @author netposa
 ** @Des
 ****************************************************************************************************/
@Data
public class Face extends BaseMan {
    /**
     * 人脸标识
     */
    @JsonProperty("FaceID")
    private String FaceID;
    /**
     * 人脸出现时间
     */
    @JsonProperty("FaceAppearTime")
    private String FaceAppearTime;
    /**
     * 人脸消失时间
     */
    @JsonProperty("FaceDisAppearTime")
    private String FaceDisAppearTime;
    /**
     * 姿态分布
     */
    @JsonProperty("Attitude")
    private String Attitude;
    /**
     * 相似度
     */
    @JsonProperty("Similaritydegree")
    private double Similaritydegree;
    /**
     * 眉形
     */
    @JsonProperty("EyebrowStyle")
    private String EyebrowStyle;
    /**
     * 鼻型
     */
    @JsonProperty("NoseStyle")
    private String NoseStyle;
    /**
     * 胡型
     */
    @JsonProperty("MustacheStyle")
    private String MustacheStyle;
    /**
     * 嘴唇
     */
    @JsonProperty("LipStyle")
    private String LipStyle;
    /**
     * 皱纹眼袋
     */
    @JsonProperty("WrinklePouch")
    private String WrinklePouch;
    /**
     * 痤疮色斑
     */
    @JsonProperty("AcneStain")
    private String AcneStain;
    /**
     * 黑痣标记
     */
    @JsonProperty("FreckleBirthMark")
    private String FreckleBirthMark;
    /**
     * 疤痕酒窝
     */
    @JsonProperty("ScarDimple")
    private String ScarDimple;
    /**
     * 其他特征
     */
    @JsonProperty("OtherFeature")
    private String OtherFeature;
//    /**
//     * 人员标识
//     */
//    @JsonProperty("PersonID")
//    private String PersonID;
//    /**
//     * 机动车标识
//     */
//    @JsonProperty("MotorVehicleID")
//    private String MotorVehicleID;
//    /**
//     * 非机动车标识
//     */
//    @JsonProperty("NonMotorVehicleID")
//    private String NonMotorVehicleID;
//

    
    
    
    
}
