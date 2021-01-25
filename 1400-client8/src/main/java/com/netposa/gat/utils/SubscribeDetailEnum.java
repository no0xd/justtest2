package com.netposa.gat.utils;

/**
 *netposa
 */
public enum SubscribeDetailEnum {
    /**
     * 订阅类别
     * 1:案事件目录
     * 2:单个案事件内容
     * 3:采集设备目录
     * 4:采集设备状态
     * 5:采集系统目录
     * 6:采集系统状态
     * 7:视频卡口目录
     * 8:单个卡口目录
     * 9:车道目录
     * 10:单个车道目录
     * 11:自动采集的人员信息
     * 12:自动采集的人脸信息
     * 13:自动采集的车辆信息
     * 14:自动采集的非机动车辆信息
     * 15:自动采集的物品信息
     * 16:自动采集的文件信息
     */
    CaseEventDir("1"), OneCaseEventDir("2"), DeviceDir("3"), DeviceStatus("4"),
    SystemDir("5"), SystemStatus("6"), VideoDarlantitoDir("7"), OneDarlantitoDir("8"),
    LaneDir("9"), OneLaneDir("10"), BodyInfo("11"), FaceInfo("12"),
    VehicleInfo("13"), NonMotorInfo("14"), GoodsInfo("15"), FileInfo("16") ;


    private String code ;

    private SubscribeDetailEnum(String code){
        this.code = code ;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
