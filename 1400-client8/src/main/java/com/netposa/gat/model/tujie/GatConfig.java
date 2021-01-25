package com.netposa.gat.model.tujie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LvLiuWei
 * @date 2018/2/5
 */
@Data
@Component
@ConfigurationProperties(prefix = "netposa.gat")
public class GatConfig {
    private String serverAddress = "";

    private String serverContextPath = "";

    private String serverDeviceId = "";

    private String serverUserName = "";

    private String serverUserPassword = "";
    
    private String faceTopic;
    
    private String faceGroupId;
    
    private String humanTopic;
    
    private String humanGroupId;
    
    private String nonmotorTopic;
    
    private String nonmotorGroupId;
    
    private String vehicleTopic;
    
    private String vehicleGroupId;
    
    private Algorithm algorithm = Algorithm.DEEPLINT;

    private String subscribeModel;

    public enum Algorithm {
        
        DEEPLINT,
        
        PCC
    }
    
}
