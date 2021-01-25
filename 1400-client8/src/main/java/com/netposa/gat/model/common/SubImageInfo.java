package com.netposa.gat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author netposa
 * 
 **/
@Data
public class SubImageInfo {
    @JsonProperty("ImageID")
    private String ImageID;

    @JsonProperty("EventSort")
    private String EventSort;

    @JsonProperty("DeviceID")
    private String DeviceID;

    @JsonProperty("StoragePath")
    private String StoragePath;

    @JsonProperty("FileFormat")
    private String FileFormat;

    @JsonProperty("ShotTime")
    private String ShotTime;

    @JsonProperty("Width")
    private String Width;

    @JsonProperty("Height")
    private String Height;

    @JsonProperty("Data")
    private String Data;

    @JsonProperty("Type")
    private String Type;
    /**
     * 特征值
     */
    @JsonProperty("FeatureInfoObject")
    private FeatureInfo FeatureInfoObject;

}
