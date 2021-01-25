package com.netposa.gat.utils;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***************************************************************************************************
 ** @time 2018-06-08 19:26
 ** @author hjc
 ** @Des 解析request请求头
 ****************************************************************************************************/
@Slf4j
@Component
public class HeaderUtils extends BaseUtils{
    @Autowired
    public Gson gson;
    /**
     * 解析request请求头Authorization，用于注册校验
     * @return
     */
    public JsonObject parseHeader(String authorization){
        JsonObject jsonObject =  null;
        Iterable<String> authorizationStrIt = Splitter.on("Digest ").split(authorization);
        String authorizationStr = null;
        for(String str: authorizationStrIt){
            if (str.contains("nonce")){
                authorizationStr = str;
            }
        }
        authorizationStr = authorizationStr.replace("=",":");
        authorizationStr = authorizationStr.replace(", ,", ",");
        try{
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("{");
            stringBuffer.append(authorizationStr);
            stringBuffer.append("}");
            String str = stringBuffer.toString();
            jsonObject = gson.fromJson(str, JsonObject.class);
        }catch (JsonSyntaxException ex){
            log.error(ex.getMessage());
        }
        return  jsonObject;
    }

}
