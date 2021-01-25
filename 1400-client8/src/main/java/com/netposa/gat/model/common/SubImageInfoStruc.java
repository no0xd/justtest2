package com.netposa.gat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 *  @author netposa
 * 
 **/
@Data
public class SubImageInfoStruc {

    @JsonProperty("SubImageInfoObject")
    private List<SubImageInfo> SubImageInfoObject;
}
