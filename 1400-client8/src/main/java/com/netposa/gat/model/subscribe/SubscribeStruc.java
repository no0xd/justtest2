package com.netposa.gat.model.subscribe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
* @author netposa
* @Param 批量订阅消息
* @return 
**/
@Data
public class SubscribeStruc {

 @JsonProperty("SubscribeListObject")
    private SubscribeObject SubscribeListObject;

    @Data
    public static class SubscribeObject{
        @JsonProperty("SubscribeObject")
        private List<Subscribe> SubscribeObject;

    }

}
