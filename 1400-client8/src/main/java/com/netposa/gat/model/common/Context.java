package com.netposa.gat.model.common;

import lombok.Data;

/**
 * @author netposa
 */
@Data
public class Context {
    
    /**
     * 服务地址
     */
    private String serverBaseUrl;
    
    /**
     * 服务用户名
     */
    private String serverUserName;
    
    /**
     * 服务用户密码
     */
    private String serverUserPassword;
    
    
    /**
     * 服务设备编号
     */
    private String serverDeviceId;
    
    /**
     * 用户认证标识
     */
    private String userIdentity = "1";

    /**
     * 服务器时间，用于校时
     */
    private String localTime;
    
}
