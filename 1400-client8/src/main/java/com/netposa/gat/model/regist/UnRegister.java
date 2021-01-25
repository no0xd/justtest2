package com.netposa.gat.model.regist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 注册请求对象
 */
@Data
public class UnRegister {
	@JsonProperty("UnRegisterObject")
	private UnRegisterObject UnRegisterObject;
	@Data
	public static class UnRegisterObject{
		@JsonProperty("DeviceID")
		private String DeviceID;
	}
}