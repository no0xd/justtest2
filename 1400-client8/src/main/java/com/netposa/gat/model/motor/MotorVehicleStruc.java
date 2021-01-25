package com.netposa.gat.model.motor;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***************************************************************************************************
 ** @author netposa
 ** @Des 1400标准机动车结构化数据
 ****************************************************************************************************/
@Data
public class MotorVehicleStruc implements Serializable {

    private static final long serialVersionUID = 2041952122150943861L;

    @JsonProperty("MotorVehicleListObject")
    private MotorVehicleObject MotorVehicleListObject;

    @Data
    public static class MotorVehicleObject {
        @JsonProperty("MotorVehicleObject")
        private List<MotorVehicle> MotorVehicleObject;
    }
}
