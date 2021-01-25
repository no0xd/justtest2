package com.netposa.gat.model.tujie.ape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
* @author hjc
* @Description  视频卡口实体  GAT 1400.3-数据库技术 表A.5 视频卡口对象特征属性 页数：20
* @Date 18:06 2018/4/23
**/
@Data
public class Tollgate {

    /**
     * 卡口ID，必填
     **/
    @JsonProperty("TollgateID")
    @JsonIgnoreProperties("tollgateID")
    private String TollgateID;

    /**
     * 名称，必填
     **/
    @JsonProperty("Name")
    @JsonIgnoreProperties("name")
    private String Name;

    /**
     * 经度，必填
     **/
    @JsonProperty("Longitude")
    @JsonIgnoreProperties("longitude")
    private String Longitude;

    /**
     * 纬度，必填
     **/
    @JsonProperty("Latitude")
    @JsonIgnoreProperties("latitude")
    private String Latitude;

    /**
     * 安装地点行政区划代码，必填
     **/
    @JsonProperty("PlaceCode")
    @JsonIgnoreProperties("placeCode")
    private String PlaceCode;

    /**
     * 位置名，具体到位置或街道门牌号，可选
     **/
    @JsonProperty("Place")
    @JsonIgnoreProperties("place")
    private String Place;//位置名

    /**
     * 卡口状态  1正常 2停用 9其他，必填
     **/
    @JsonProperty("Status")
    @JsonIgnoreProperties("status")
    private String Status;

    /**
     * 卡口类型，必填 80治安卡口，81交通卡口，82其他
     **/
    @JsonProperty("TollgateCat")
    @JsonIgnoreProperties("tollgateCat")
    private String TollgateCat;

    /**
     * 卡口用途，必填
     **/
    @JsonProperty("TollgateUsage")
    @JsonIgnoreProperties("tollgateUsage")
    private String TollgateUsage;

    /**
     * 卡口车道数，可选
     **/
    @JsonProperty("LaneNum")
    @JsonIgnoreProperties("laneNum")
    private String LaneNum;//

    /**
     * 管辖单位代码，可选
     **/
    @JsonProperty("OrgCode")
    @JsonIgnoreProperties("orgCode")
    private String OrgCode;

    /**
     * 卡口启用时间，可选
     **/
    @JsonProperty("ActiveTime")
    @JsonIgnoreProperties("activeTime")
    private String ActiveTime;
}
