package com.netposa.gat.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * Description: 公共controller
 * User: hjc
 * Date: 2018-05-02
 * Time: 11:26
 */
@Slf4j
@Component
public class BaseController {

    @Autowired
    Gson gson;
    
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
    
    /**
     * 
     * @param request
     * @return
     */
    public String parseRequest(HttpServletRequest request){
        StringBuffer sb = new StringBuffer();
        String line = null;
        try{
            BufferedReader reader = request.getReader();
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            log.error("订阅请求流解析出错");
        }
        log.info("sb data is"+sb.toString());
        String subsObjectStr = sb.toString();
        return subsObjectStr;
    }
}
