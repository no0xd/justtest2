package com.netposa.gat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * * @author netposa
 * * 特征对象
 **/
@Data
public class FeatureInfo {

    /**
     * 厂家
     */
    @JsonProperty("Vendor")
    private String Vendor;

    /**
     *算法版本
     */
    @JsonProperty("AlgorithmVersion")
    private String AlgorithmVersion;

    /**
     * 特征值数据
     */
    @JsonProperty("FeatureData")
    private String FeatureData;
}
