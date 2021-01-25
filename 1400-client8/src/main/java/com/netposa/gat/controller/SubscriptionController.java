package com.netposa.gat.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netposa.gat.model.response.ResponseStatus;
import com.netposa.gat.model.subscribe.Subscribe;
import com.netposa.gat.service.ISubscribeService;
import com.netposa.gat.utils.DateUtils;
import com.netposa.gat.utils.SubscribeDetailEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 
 * @author netposa
 *
 */
@RestController
@Slf4j
@RequestMapping("/VIID")
public class SubscriptionController extends BaseController{

    @Autowired
    private Gson gson;
    
    private ResponseStatus responseStatus = new ResponseStatus();

    @Autowired
    private ISubscribeService iSubscribeService;

    /**
     * @desc  批量订阅
     * @return
     */
    @PostMapping("/Subscribes")
    public List<ResponseStatus> subscribeList(HttpServletRequest request){
    	log.info("into subscribe ...");
        String subsObjectStr = this.parseRequest(request);

        List<ResponseStatus> list = new ArrayList<ResponseStatus>();

        //处理请求
        try {
            //解析成对象List
            List<Subscribe> subscribeList = new ArrayList<>();
            log.info("str string data is -->{}"+subsObjectStr);

            JsonObject listObject = gson.fromJson(subsObjectStr,JsonObject.class);
            JsonObject jo2 = gson.fromJson(listObject.get("SubscribeListObject"),JsonObject.class);
            JsonArray joArr = gson.fromJson(jo2.get("SubscribeObject"),JsonArray.class);
            for (JsonElement je: joArr) {
                //持久化LIST
                Subscribe subscribe = gson.fromJson(je,Subscribe.class);
                //生成UUID作为主键
                subscribe.setID(UUID.randomUUID().toString());
                subscribeList.add(subscribe);
            }

            if(subscribeList.isEmpty()){
                responseStatus.setStatusCode("7");
                list.add(responseStatus);
                return list;
            }

            log.info("subscribeList size => "+subscribeList.size());
            for (Subscribe subscribe : subscribeList){
                if("0".equals(subscribe.getOperateType())){
                    subscribe.setSubscribeStatus("0");
                }
                String subscribeDetail = subscribe.getSubscribeDetail();
                String [] subscribeDetailArr = subscribeDetail.split(",");
                if(subscribeDetailArr!=null&&subscribeDetailArr.length>0){
                    for (String str:subscribeDetailArr) {
                        //判断类型
                        if(SubscribeDetailEnum.BodyInfo.getCode().equals(str)){
                            responseStatus = generateResponse(responseStatus,subscribe);
                            list.add(responseStatus);
                        }else if(SubscribeDetailEnum.FaceInfo.getCode().equals(str)){
                            responseStatus =  generateResponse(responseStatus,subscribe);
                            list.add(responseStatus);
                        }else if(SubscribeDetailEnum.VehicleInfo.getCode().equals(str)){
                            responseStatus =  generateResponse(responseStatus,subscribe);
                            list.add(responseStatus);
                        }else if(SubscribeDetailEnum.NonMotorInfo.getCode().equals(str)){
                            responseStatus =  generateResponse(responseStatus,subscribe);
                            list.add(responseStatus);
                        }
                    }
                }
            }
            //持久化订阅信息
            iSubscribeService.insertSubscribeList(subscribeList);
            return list;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            responseStatus.setStatusCode("1");
            list.add(responseStatus);
            return list;
        }
    }

    /**
     * @desc  更新订阅
     * @param
     * @return
     */
    @PutMapping("/Subscribes")
    public List<ResponseStatus> updateSubscribe(HttpServletRequest request){
        String subsObjectStr = this.parseRequest(request);
        List<ResponseStatus> list = new ArrayList<>();
        //解析成对象List
        List<Subscribe> subscribeList = new ArrayList<>();
        log.info("str string data is -->{}"+subsObjectStr);

        JsonObject listObject = gson.fromJson(subsObjectStr,JsonObject.class);
        JsonObject jo2 = gson.fromJson(listObject.get("SubscribeListObject"),JsonObject.class);
        JsonArray joArr = gson.fromJson(jo2.get("SubscribeObject"),JsonArray.class);
        for (JsonElement je: joArr) {
            //持久化LIST
            Subscribe subscribe = gson.fromJson(je,Subscribe.class);
            subscribeList.add(subscribe);
        }
        int result = iSubscribeService.updateSubscribe(subscribeList);
        if(result == subscribeList.size()){
            for (Subscribe subscribe : subscribeList){
                ResponseStatus responseStatus = new ResponseStatus();
                generateResponse(responseStatus,subscribe);
                responseStatus.setStatusString("更新订阅成功");
                list.add(responseStatus);
            }
        }
        return list;
    }

    /**
     * @desc  取消订阅
     * @param
     * @return
     */
    @PutMapping ("/Subscribes/{subscribeIds}")
    public ResponseStatus cancelSubscribes(HttpServletRequest request){
        String subscribeStr = this.parseRequest(request);
        Subscribe subscribe = gson.fromJson(subscribeStr,Subscribe.class);
        ResponseStatus responseStatus = new ResponseStatus();
        if(subscribe == null ){
            responseStatus.setStatusCode("7");
            return responseStatus;
        }
        //处理请求
        try {
            /**
             * OperateType
             * 0，订阅 1，取消订阅
             */
            //判断状态
            if (subscribe.getOperateType() == "1") {
                subscribe.setSubscribeStatus("1");
            }
            int result = iSubscribeService.cancelSubscribe(subscribe);
            if (result > 0){
                generateResponse(responseStatus,subscribe);
                responseStatus.setStatusString("取消订阅成功");
            }
            return responseStatus;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            responseStatus.setStatusCode("1");
            responseStatus.setStatusString("取消订阅失败");
            responseStatus.setId(subscribe.getSubscribeID());
            return responseStatus;
        }
    }

    /**
     * @desc  订阅Get
     * @param
     * @return
     */
    @GetMapping("/Subscribes/{subscribeIds}")
    public List<Subscribe> subscribeGet(@PathVariable("subscribeIds") String subscribeIds){
        //String IDList = this.parseRequest(request);
        Subscribe subscribe = new Subscribe();
        List<Subscribe> subscribeList = new ArrayList<>();
        try {
            String [] IDs = subscribeIds.split(",");
            List<String> subscribeIdList = new ArrayList<>();
            //subscribeIdList.add("20180427114500000001");
            //subscribeIdList.add("20180427114500000002");
            for (String id : IDs){
                subscribeIdList.add(id);
            }
            subscribeList = iSubscribeService.querySubscribes(subscribeIdList);
            return subscribeList;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            subscribe.setSubscribeStatus("9");
            subscribe.setSubscribeStatus("订阅查询失败");
            subscribeList.add(subscribe);
            return subscribeList;
        }
    }

    /**
     * @desc  订阅Del
     * @param
     * @return
     */
    @DeleteMapping("/Subscribes")
    public List<ResponseStatus> subscribeDel(HttpServletRequest request){
        String IDList = this.parseRequest(request);
        List<ResponseStatus> list = new ArrayList<>();
        try {
            String [] IDstr = IDList.split(":");
            String [] IDs = IDstr[1].substring(1,IDstr[1].length()-2).split(",");
            List<String> subscribeIdList = new ArrayList<>();
            for (String id : IDs){
                subscribeIdList.add(id);
            }
            int result = iSubscribeService.deleteSubscribe(subscribeIdList);
            if (result == IDs.length){
                for (String id : IDs){
                    ResponseStatus responseStatus = new ResponseStatus();
                    generateResponse(responseStatus,iSubscribeService.getSubscribeById(id));
                    responseStatus.setId(id);
                    responseStatus.setStatusString("删除订阅成功");
                }

            }
            return list;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            responseStatus.setStatusCode("1");
            list.add(responseStatus);
            return list;
        }
    }

    private ResponseStatus generateResponse(ResponseStatus responseStatus,Subscribe subscribe){
        responseStatus.setStatusCode("O");
        responseStatus.setLocalTime(DateUtils.Convert2String(new Date(),DateUtils.DateFormatNoSpan));
        responseStatus.setStatusString("订阅成功");
        responseStatus.setId(subscribe.getSubscribeID());
        return responseStatus;
    }
}
