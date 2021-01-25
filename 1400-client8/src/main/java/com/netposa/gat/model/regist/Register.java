package com.netposa.gat.model.regist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 注册请求对象
 */
@Data
public class Register{

    @JsonProperty("RegisterObject")
    private RegisterObject RegisterObject;

    @Data
    public static class RegisterObject{
        @JsonProperty("DeviceID")
        private String DeviceID;
    }
}
