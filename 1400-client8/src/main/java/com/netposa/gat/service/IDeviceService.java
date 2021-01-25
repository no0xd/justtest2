package com.netposa.gat.service;



import java.util.List;

import com.netposa.gat.model.tujie.ape.DeviceObj;

/**
* @author hjc
* @Description 设备信息相关接口
* @Date 14:14 2018/4/23
* @Param
* @return
**/
public interface IDeviceService {
    /*
    * @author hjc
    * @Description 查询所有设备数据
    * @Date 14:14 2018/4/23
    * @Param []
    * @return java.util.List<com.netposa.gat.model.Device>
    **/
    public List<DeviceObj> getAll();
    /*
    * @author hjc
    * @Description 
    * @Date 16:49 2018/4/23
    * @Param [deviceId]
    * @return com.netposa.gat.model.DeviceObj
    **/
    public DeviceObj getDeviceObjById(String deviceId);

}

