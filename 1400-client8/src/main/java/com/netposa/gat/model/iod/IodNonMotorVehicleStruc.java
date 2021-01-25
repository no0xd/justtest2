package com.netposa.gat.model.iod;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc.NonMotorVehicleObject;

import lombok.Data;

/***************************************************************************************************
 ** @author netposa
 ** @Des
 ****************************************************************************************************/
@Data
public class IodNonMotorVehicleStruc {
	
	@JsonProperty("NonMotorVehicleListObject")
    private NonMotorVehicleObject NonMotorVehicleListObject;

	/**
	 * 0:1400 协议 1:私有协议
	 */
	@JsonProperty("proxyType")
	private int proxyType;

	/**
	 * proxyManufacturer:netposa/hik/dahua
	 */
	@JsonProperty("proxyManufacturer")
	private String proxyManufacturer;

}
