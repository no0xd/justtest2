package com.netposa.gat.model.tujie.ape;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjc
 * Date: 2018-04-23
 * Time: 18:48
 */
@Data
public class TollgateObj {
    private String id       ;
    private String name     ;
    private String type     ;
    private String orgid    ;
    private double longitude;
    private double latitude ;
    private String pinyin   ;
    private String acronym  ;
    private String tollgateType;
}
