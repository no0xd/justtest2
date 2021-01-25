package com.netposa.gat.model.iod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.model.person.PersonStruc.PersonObject;

import lombok.Data;
/**
 * netposa
 * @author netposa
 *
 */
@Data
public class IodPersonStruc {
	
	 @JsonProperty("PersonListObject")
	 private PersonObject PersonListObject;
	 
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
