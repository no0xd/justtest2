package com.netposa.gat.model.tujie.ape;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by LQ on 2018/4/13.
 * @Desc  视频卡口实体  GAT 1400.3-数据库技术 表A.1 视频卡口对象特征属性 页数：18
 */
@Data
public class APE {

    /**
     * 设备ID 必填
     **/
    @JsonProperty("ApeID")
    @JsonIgnoreProperties("apeID")
    private String ApeID;

    /**
     * 名称 必填
     **/
    @JsonProperty("Name")
    @JsonIgnoreProperties("name")
    private String Name;

    /**
     * 型号 必填
     **/
    @JsonProperty("Model")
    @JsonIgnoreProperties("model")
    private String Model;

    /**
     * IP地址 必填
     **/
    @JsonProperty("IPAddr")
    @JsonIgnoreProperties("iPAddr")
    private String IPAddr;

    /**
     * IPv6地址 可选
     **/
    @JsonProperty("IPV6Addr")
    @JsonIgnoreProperties("iPV6Addr")
    private String IPV6Addr;

    /**
     * 端口号 必填
     **/
    @JsonProperty("Port")
    @JsonIgnoreProperties("port")
    private String Port;

    /**
     * 经度 必填
     **/
    @JsonProperty("Longitude")
    @JsonIgnoreProperties("longitude")
    private String Longitude;

    /**
     * 纬度 必填
     **/
    @JsonProperty("Latitude")
    @JsonIgnoreProperties("latitude")
    private String Latitude;

    /**
     * 安装地点行政区划代码 必填
     **/
    @JsonProperty("PlaceCode")
    @JsonIgnoreProperties("placeCode")
    private String PlaceCode;

    /**
     * 位置名 可选
     **/
    @JsonProperty("Place")
    @JsonIgnoreProperties("place")
    private String Place;

    /**
     * 管辖单位代码 可选
     **/
    @JsonProperty("OrgCode")
    @JsonIgnoreProperties("orgCode")
    private String OrgCode;

    /**
     * 车辆抓拍方向 可选 0：拍车头；1：拍车尾，兼容无视频卡口信息设备
     **/
    @JsonProperty("CapDirection")
    @JsonIgnoreProperties("capDirection")
    private String CapDirection;

    /**
     * 监视方向 可选
     **/
    @JsonProperty("MonitorDirection")
    @JsonIgnoreProperties("monitorDirection")
    private String MonitorDirection;

    /**
     * 监视区域说明 可选
     **/
    @JsonProperty("MonitorAreaDesc")
    @JsonIgnoreProperties("monitorAreaDesc")
    private String MonitorAreaDesc;

    /**
     * 是否在线 必填
     **/
    @JsonProperty("IsOnLine")
    @JsonIgnoreProperties("isOnLine")
    private String IsOnLine;

    /**
     * 所属采集系统 可选
     **/
    @JsonProperty("OwnerApsID")
    @JsonIgnoreProperties("ownerApsID")
    private String OwnerApsID;

    /**
     * 用户账号 可选（文档为可选，一所目前是必填）
     **/
    @JsonProperty("UserId")
    @JsonIgnoreProperties("userId")
    private String UserId;

    /**
     * 口令 可选（文档为可选，一所目前是必填）
     **/
    @JsonProperty("Password")
    @JsonIgnoreProperties("password")
    private String Password;
}
