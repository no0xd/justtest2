package com.netposa.gat.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * 1400协议响应列表对象
 * @author netposa
 *
 */
@Data
public class ResponseStatusStruc {

    @JsonProperty("ResponseStatusListObject")
    private ResponseStatusObject ResponseStatusListObject;

    @Data
    public static class ResponseStatusObject{

        @JsonProperty("ResponseStatusObject")
        private List<ResponseStatus> ResponseStatusObject;
    }
}
