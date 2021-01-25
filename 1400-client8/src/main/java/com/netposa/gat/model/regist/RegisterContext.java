package com.netposa.gat.model.regist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

/**
* @author netposa
* @Description 注册上下文实体类
**/
@Data
public class RegisterContext  {
    /**
     * UUID
     */
    @JsonProperty("ID")
    private String ID;

    /**
     * 发起方发送的DeviceId
     */
    @JsonProperty("DeviceID")
    private String DeviceID;

    /**
     * 接收方的ip
     */
    @JsonProperty("IP")
    private String IP;

    /**
     * 接收方发送的PORT
     */
    @JsonProperty("Port")
    private String Port;
    
    /**
     * username
     */
    @JsonProperty("UserName")
    private String UserName;
    
    /**
     * password
     */
    @JsonProperty("Password")
    private String Password;
    
    /**
     * 注册时间
     */
    @JsonProperty("RegisterTime")
    private Date RegisterTime;

    /**
     * 注销时间
     */
    @JsonProperty("CancelTime")
    private Date CancelTime;

    /**
     * 保活时间间隔
     */
    @JsonProperty("KeepAliveTime")
    private int KeepAliveTime;

    /**
     * 状态，R为注册中，C为注销
     */
    @JsonProperty("Status")
    private String Status;
    
    /**
     * 根据厂商code过滤，可以多个厂商，逗号隔开。
     */
    @JsonProperty("proxyManufacturer")
    private String proxyManufacturer;
    
    /**
     * 根据的deviceID过滤，可以多个DeviceID，逗号隔开。
     */
    @JsonProperty("GreenList")
    private String GreenList;
    
    /**
     * 需要获取的数据类型
     */
    @JsonProperty("GreenList")
    private String DataType;
    
    /**
     * kafka配置groupId
     */
    @JsonProperty("GroupID")
    private String GroupID;
}
