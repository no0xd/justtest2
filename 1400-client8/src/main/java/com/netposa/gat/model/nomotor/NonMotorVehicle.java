package com.netposa.gat.model.nomotor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.common.BaseVehicle;

import lombok.Data;

/***************************************************************************************************
 ** @author netposa
 ** @Des
 ****************************************************************************************************/
@Data
public class NonMotorVehicle extends BaseVehicle {
    /**
     * 车辆标识 -备注：辆全局唯一标识
     * R -必选
     */
    @JsonProperty("NonMotorVehicleID")
    private String NonMotorVehicleID;
    /**
     *车辆款型
     */
    @JsonProperty("VehicleType")
    private String VehicleType;
    /**
     * 人脸标识
     */
    @JsonProperty("FaceID")
    private String FaceID;
    /**
     * 人员标识
     */
    @JsonProperty("PersonID")
    private String PersonID;

}
