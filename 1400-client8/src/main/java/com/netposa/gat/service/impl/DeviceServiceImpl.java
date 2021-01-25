package com.netposa.gat.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netposa.gat.mapper.DeviceMapper;
import com.netposa.gat.model.tujie.ape.DeviceObj;
import com.netposa.gat.service.IDeviceService;

import java.util.List;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    /**
     * 获取需要推送的全部数据
     * @return
     */
    @Override
    public List<DeviceObj> getAll(){
        return null;
    }

    @Override
    public DeviceObj getDeviceObjById(String deviceId) {
        return null;
    }

}
