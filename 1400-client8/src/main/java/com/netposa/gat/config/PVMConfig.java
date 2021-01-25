package com.netposa.gat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netposa
 */
@Data
@Component
@ConfigurationProperties(prefix = "netposa.pvm")
public class PVMConfig {
    private String host;
    
    private String port;
    
    private String url;
    
}
