package com.netposa.gat.service;


import java.util.List;

import com.netposa.gat.model.tujie.ape.TollgateObj;

/**
 * Created with IntelliJ IDEA.
 * Description: 视频卡口相关接口
 * User: hjc
 * Date: 2018-04-24
 * Time: 19:00
 */
public interface ITollgateService {
    /**
    * @author hjc
    * @Description 获取所有视频卡口资源
    * @Date 19:01 2018/4/23
    * @Param []
    * @return java.util.List<com.netposa.gat.model.TollgateObj>
    **/
    public List<TollgateObj> getAll();

    /**
    * @author hjc
    * @Description 根据卡口ID获取卡口实体
    * @Date 19:02 2018/4/23
    * @Param [id]
    * @return com.netposa.gat.model.TollgateObj
    **/
    public TollgateObj getTollgateObjById(String id);

    /**
     * @author hjc
     * @Description 同步1400卡口数据
     * @Date 17:17 2018/5/18
     * @Param [orgId]
     * @return java.lang.String
     **/
    public String syncClientTollgate(String url);

}
