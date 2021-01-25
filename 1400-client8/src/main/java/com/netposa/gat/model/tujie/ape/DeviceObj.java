package com.netposa.gat.model.tujie.ape;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjc
 * Date: 2018-04-23
 * Time: 14:16
 */
@Data
public class DeviceObj {
    private String id		     ;
    private String deviceId      ;
    private String groupId      ;
    private String name    ;
    private String pinyin        ;
    private String acronym       ;
    private Double longitude     ;
    private Double latitude      ;
    private int    cameraType    ;
    private int    installType   ;
    private int    hdsdType      ;
    private int    isOnline      ;
    private int    score         ;
    private int    cameraStatus  ;
    private String parentId      ;
    private String chnalNo;
    private String serverId;

    private String ability ;
    private String deviceType;

    private String direction;

    private String gbId;
}
