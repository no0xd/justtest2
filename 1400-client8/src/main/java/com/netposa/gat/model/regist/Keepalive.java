package com.netposa.gat.model.regist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 保活请求对象
 */
@Data
public class Keepalive {

    @JsonProperty("KeepaliveObject")
    private KeepaliveObject KeepaliveObject;
    
    @Data
    public static class KeepaliveObject {
    	  @JsonProperty("DeviceID")
          private String DeviceID;
    }
}
