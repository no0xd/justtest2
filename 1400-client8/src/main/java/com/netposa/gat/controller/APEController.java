package com.netposa.gat.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netposa.gat.model.tujie.ape.APE;
import com.netposa.gat.service.IDeviceService;
import com.netposa.gat.utils.http.APEsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author netposa
 *
 */
@RestController
@Slf4j
@RequestMapping("/VIID")
public class APEController {
	
    @Autowired
    private IDeviceService iDeviceService;
    
    /**
     * @desc  获取设备
     * @param paramApe
     * @return
     */
    @GetMapping("/APEs")
    public @ResponseBody String getAPEsList(@RequestBody(required = false) APE paramApe, HttpServletRequest request){
    	
    	
    	return APEsUtil.testRequest();
    	
    }
    
//    public APEStruc getAPEsList(@RequestBody(required = false) APE paramApe, HttpServletRequest request){
//
//        APEStruc apeStruc = new APEStruc();
//        APEStruc.APEObject apeObject = new APEStruc.APEObject();
//        log.info("====================getAPEsList====================="+request.getRemoteAddr());
//        List<APEStruc.APEObject.APE> apeList = new ArrayList<APEStruc.APEObject.APE>();
//        List<DeviceObj> deviceObjList = iDeviceService.getAll();
//        log.info("====================getAPEsList====================="+request.getRemoteAddr());
//        //处理请求
//        if (deviceObjList == null){
//            String tips = "ape list is empty";
//            log.info(tips);
//            return apeStruc;
//        }
//        for (DeviceObj deviceObj : deviceObjList){
//            //System.out.println(obj);
//            APEStruc.APEObject.APE ape = new APEStruc.APEObject.APE();
//            ape.setApeID(deviceObj.getGbId());
//            ape.setName(deviceObj.getName());
//            ape.setModel(deviceObj.getDeviceType());
//            ape.setIPAddr("50.1.64.36");
//            ape.setPort("50000");
//            ape.setPlaceCode("010400");
//            ape.setPlace("");
//            ape.setIsOnLine(deviceObj.getIsOnline()+"");
//            ape.setLatitude(deviceObj.getLatitude()+"");
//            ape.setLongitude(deviceObj.getLongitude()+"");
//            ape.setUserId("admin");
//            ape.setPassword("admin");
//            apeList.add(ape);
//            //log.info("APE list {}",apeStruc.toString());
//        }
//        apeObject.setAPEObject(apeList);
//        apeStruc.setAPEListObject(apeObject);
//        return apeStruc;
//    }

//    /**
//     * @desc  更新设备,主要用于更新采集设备的口令，以实现动态口令管理。
//     * @param apeList
//     * @return
//     */
//    @PutMapping("/APEs")
//    public List<ResponseStatus> putAPEsList(@RequestBody List<APE> apeList){
//        List<ResponseStatus> list = new ArrayList<>();
//        if(apeList == null || apeList.isEmpty()){
//            responseStatus.setStatusCode("7");
//            list.add(responseStatus);
//            return list;
//        }
//        //处理请求
//        try {
//            for (APE ape : apeList){
//                //更新采集设备的口令
//                responseStatus = generateResponse(responseStatus,ape);
//                list.add(responseStatus);
//            }
//            return list;
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//            responseStatus.setStatusCode("1");
//            list.add(responseStatus);
//            return list;
//        }
//    }
//    private ResponseStatus generateResponse(ResponseStatus responseStatus,APE ape){
//        responseStatus.setStatusCode("O");
//        responseStatus.setLocalTime( DateUtils.Convert2String(new Date(),DateUtils.DateFormatNoSpan));
//        responseStatus.setStatusString("更新成功");
//        responseStatus.setId(ape.getApeID());
//        return responseStatus;
//    }
}
