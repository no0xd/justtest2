package com.netposa.gat.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.netposa.gat.model.tujie.ape.DeviceObj;

import java.util.List;

@Service
public interface DeviceMapper {
    /**
     * 获取需要推送的全部数据
     * @return
     */
    public List<DeviceObj> getAll();

    /**
     * 根据设备ID查询数据
     * @return
     */
    public DeviceObj getDeviceObjById(@Param("id") String deviceId);

}
