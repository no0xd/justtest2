package com.netposa.gat.model.notification;

import lombok.Data;

/**
 * 订阅消息持久化类
 * @author netposa
 *
 */
@Data
public class SubscribeNotificationText {
    /**
     * 唯一标示UUID，数据库主键，非1400标准
     */
    private String ID;
    /**
     * 通知标识
     */
    private String NotificationID;

    /**
     * 订阅标识
     */
    private String SubscribeID;

    /**
     * 订阅标题
     */
    private String Title;

    /**
     * 触发时间
     */
    private String TriggerTime;

    /**
     * 信息标识，订阅通知的详细信息
     */
    private String InfoIDs;

    /**
     * 视频案事件信息数据集
     */
    private String CaseObjectList;

    /**
     * 视频卡口信息数据集
     */
    private String Tollgate;

    /**
     * 车道信息数据集
     */
    private String Lane;

    /**
     * 设备信息数据集
     */
    private String DeviceList;

    /**
     * 设备状态，该通知针对批量订阅方式
     */
    private String DeviceStatusList;

    /**
     * 采集系统，设备网管信息数据集
     */
    private String APSObjectList;

    /**
     * 采集系统状态，该通知针对批量订阅方式
     */
    private String APSStatusObjectList;

    /**
     * 人员信息数据集
     */
    private String PersonObjectList;

    /**
     * 人脸信息数据集
     */
    private String FaceObjectList;

    /**
     * 机动车信息数据集
     */
    private String MotorVehicleObjectList;

    /**
     * 非机动车数据集
     */
    private String NonMotorVehicleObjectList;

    /**
     * 物品列表
     */
    private String ThingObjectList;

    /**
     * 场景列表
     */
    private String SceneObjectList;
}
