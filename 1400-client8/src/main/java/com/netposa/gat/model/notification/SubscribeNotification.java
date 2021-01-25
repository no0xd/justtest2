package com.netposa.gat.model.notification;

import lombok.Data;

import java.util.List;

import com.netposa.gat.model.face.FaceStruc.FaceObject;
import com.netposa.gat.model.motor.MotorVehicleStruc.MotorVehicleObject;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc.NonMotorVehicleObject;
import com.netposa.gat.model.person.PersonStruc.PersonObject;
import com.netposa.gat.model.tujie.ape.APEStruc.APEObject;
import com.netposa.gat.model.tujie.ape.TollgateStruc.TollgateObject;

/***************************************************************************************************
 ** @time 2018-04-26 13:47
 ** @author Huzz
 ** @Des 1400标准"通知对象"，用于订阅通知、及其持久化操作。标准文档GAT 1400.3-数据库技术，页数45
 ****************************************************************************************************/
@Data
public class SubscribeNotification {

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
    private List<Object> CaseObjectList;

    /**
     * 视频卡口信息数据集
     */
    private TollgateObject Tollgate;

    /**
     * 车道信息数据集
     */
    private List<Object> Lane;

    /**
     * 设备信息数据集
     */
    private APEObject DeviceList;

    /**
     * 设备状态，该通知针对批量订阅方式
     */
    private List<Object> DeviceStatusList;

    /**
     * 采集系统，设备网管信息数据集
     */
    private List<Object> APSObjectList;

    /**
     * 采集系统状态，该通知针对批量订阅方式
     */
    private List<Object> APSStatusObjectList;

    /**
     * 人员信息数据集
     */
    private PersonObject PersonObjectList;

    /**
     * 人脸信息数据集
     */
    private FaceObject FaceObjectList;

    /**
     * 机动车信息数据集
     */
    private MotorVehicleObject MotorVehicleObjectList;

    /**
     * 非机动车数据集
     */
    private NonMotorVehicleObject NonMotorVehicleObjectList;

    /**
     * 物品列表
     */
    private List<Object> ThingObjectList;

    /**
     * 场景列表
     */
    private List<Object> SceneObjectList;
}
