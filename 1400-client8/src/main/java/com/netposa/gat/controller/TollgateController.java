package com.netposa.gat.controller;

import com.netposa.gat.model.tujie.ape.Tollgate;
import com.netposa.gat.model.tujie.ape.TollgatePVM;
import com.netposa.gat.model.tujie.ape.TollgateStruc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author netposa
 *
 */
@RestController
@Slf4j
@RequestMapping("/VIID")
public class TollgateController extends BaseController{

    /**
     * @desc  获取视频卡口资源
     * @param paramTollgate
     * @return
     */
    @GetMapping("/Tollgates")
    public TollgateStruc getTollgatesList(@RequestBody(required = false) Tollgate paramTollgate, HttpServletRequest request) {
        TollgateStruc tollgateStruc = new TollgateStruc();
        TollgateStruc.TollgateObject tollgateObject = new TollgateStruc.TollgateObject();
        //log.info("====================getTollgatesList====================="+request.getRemoteAddr());
        List<Tollgate> tollgateList = new ArrayList<Tollgate>();
        //List<TollgateObj> tollgateObjList = iTollgateService.getAll();
        List<TollgatePVM> tollgateObjList = new ArrayList<>();
//        Set<Object> keys = stringRedisTemplate.opsForHash().keys(StaticVarConfig.KEY_PVM_TOLLGATE_MAP);
//        if (keys.size() > 0) {
//            for (Object obj : keys) {
//                Object tollgateObj = stringRedisTemplate.opsForHash().get(StaticVarConfig.KEY_PVM_TOLLGATE_MAP, obj.toString());
//                if (tollgateObj != null) {
//                    try {
//                        TollgatePVM tollgatePVM = gson.fromJson(tollgateObj.toString(), TollgatePVM.class);
//                        tollgateObjList.add(tollgatePVM);
//                    } catch (JsonSyntaxException ex) {
//                        log.error("tollgate pvm trans error");
//                    }
//
//                }
//            }
//
//        }
        //log.info("====================getTollgatesList====================="+request.getRemoteAddr());
        //处理请求
        if (tollgateObjList == null || CollectionUtils.isEmpty(tollgateObjList)) {
            String tips = "tollgate list is empty";
            log.info(tips);
            return tollgateStruc;
        }
        for (TollgatePVM tollgatePVM : tollgateObjList) {
            Tollgate tollgate = new Tollgate();
            //和PVM确定，国标ID取code字段
            tollgate.setTollgateID(tollgatePVM.getCode());
            tollgate.setName(tollgatePVM.getName());
            tollgate.setLongitude(tollgatePVM.getX());
            tollgate.setLatitude(tollgatePVM.getY());
            tollgate.setPlaceCode(tollgatePVM.getPlacecode());
            tollgate.setPlace(tollgatePVM.getPlace());
            tollgate.setStatus("1");
            tollgate.setTollgateCat(tollgatePVM.getType());
            tollgate.setTollgateUsage(tollgatePVM.getType());
            tollgateList.add(tollgate);
            log.info("Tollgate list {}", tollgateStruc.toString());
        }
        tollgateObject.setTollgateObject(tollgateList);
        tollgateStruc.setTollgateListObject(tollgateObject);
        return tollgateStruc;
    }
}
