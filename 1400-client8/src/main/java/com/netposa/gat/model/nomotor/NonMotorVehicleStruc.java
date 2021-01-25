package com.netposa.gat.model.nomotor;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
* @author netposa
* @Description 非机动车数据 GAT 1400.3-数据库技术 表A.13 非机动车对象特征属性 页数：35
* @Param 
* @return 
**/
@Data
public class NonMotorVehicleStruc {

    @JsonProperty("NonMotorVehicleListObject")
    private NonMotorVehicleObject NonMotorVehicleListObject;

    @Data
    public static class NonMotorVehicleObject{
        @JsonProperty("NonMotorVehicleObject")
        private List<NonMotorVehicle> NonMotorVehicleObject;
    }
}
