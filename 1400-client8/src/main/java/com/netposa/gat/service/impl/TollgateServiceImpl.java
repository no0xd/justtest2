package com.netposa.gat.service.impl;

import com.google.gson.*;
import com.netposa.gat.mapper.TollgateMapper;
import com.netposa.gat.model.tujie.ape.TollgateObj;
import com.netposa.gat.model.tujie.ape.TollgatePVM;
import com.netposa.gat.service.ITollgateService;
import liquibase.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * netposa
 */
@Service
@Slf4j
public class TollgateServiceImpl implements ITollgateService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    @Autowired
    private TollgateMapper tollgateMapper;
    
    /**
    * @Description 获取所有视频卡口资源
    **/
    @Override
    public List<TollgateObj> getAll() {
        List<TollgateObj> tollgateObjList = null;
        return tollgateObjList;
    }

    /**
    * @Description 根据卡口ID获取卡口资源
    **/
    @Override
    public TollgateObj getTollgateObjById(String id) {
        return null;
    }

    /**
    * @Description  从PVM获取卡口资源并写入缓存
    * @Param [reqUrl]
    * @return java.lang.String
    **/
    @Override
    public String syncClientTollgate(String reqUrl) {
        TollgatePVM tollgatePVM =null;
        try{
            String tollgateStrucString = restTemplate.getForObject(reqUrl,String.class);
            if (StringUtils.isNotEmpty(tollgateStrucString)){
//                Set<Object> keys = stringRedisTemplate.opsForHash().keys(StaticVarConfig.KEY_PVM_TOLLGATE_MAP);
                JsonObject listObject = gson.fromJson(tollgateStrucString,JsonObject.class);
                JsonObject jsonObject = gson.fromJson(listObject.get("data"),JsonObject.class);
                JsonArray tollgateArray = gson.fromJson(jsonObject.get("tollgate"),JsonArray.class);
                if(tollgateArray == null || tollgateArray.size() <= 0){
                    return null;
                }
                Map<String,String> tollgateMap = new HashMap<>();
                for (JsonElement jsonElement: tollgateArray){
                    tollgatePVM = gson.fromJson(jsonElement,TollgatePVM.class);
                    if (tollgatePVM != null && StringUtils.isNotEmpty(tollgatePVM.getId())
                            && StringUtils.isNotEmpty(tollgatePVM.getCode())
                            && (!tollgatePVM.getId().equals(tollgatePVM.getCode()))) {
                        tollgateMap.put(tollgatePVM.getId(),gson.toJson(tollgatePVM));
//                        keys.remove(tollgatePVM.getId());
                    }
                }
//                stringRedisTemplate.opsForHash().putAll(StaticVarConfig.KEY_PVM_TOLLGATE_MAP,tollgateMap);
//                clearDeprecatedCache(StaticVarConfig.KEY_PVM_TOLLGATE_MAP, keys);
            }
        }catch (RestClientException ex){
            log.error("rest请求异常,{}",ex);
        }catch (JsonSyntaxException ex){
            log.error("json转换异常,{}",ex);
        }catch (Exception ex){
            log.error("其他异常,{}",ex);
        }
        return null;
    }

}
