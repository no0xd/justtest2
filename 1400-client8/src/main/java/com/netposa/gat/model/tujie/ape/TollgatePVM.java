package com.netposa.gat.model.tujie.ape;
import lombok.Data;

/**
* @author hjc
* @Description  视频卡口实体  对应PVM
* @Date 18:06 2018/4/23
**/
@Data
public class TollgatePVM {

    /**
     * 卡口ID
     **/
    private String id;

    /**
     * 名称
     **/
    private String name;

    /**
     * 国标ID
     **/
    private String code;

    /**
     * 卡口类型
     **/
    private String type;

    /**
     *
     **/
    private String orgunitid;

    /**
     *
     **/
    private String grade;

    /**
     *
     **/
    private String place;

    /**
     *
     **/
    private String unitcode;

    /**
     * 行政区域代码
     **/
    private String placecode;

    /**
     * 经度
     **/
    private String x;

    /**
     * 纬度
     **/
    private String y;

    /**
     *
     **/
    private String mappingid;

    /**
     *
     **/
    private String relationId;



}
