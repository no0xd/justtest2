package com.netposa.gat.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.netposa.gat.model.subscribe.Subscribe;

import java.util.List;

@Service
public interface SubscribeMapper {

    /*
    * @author hjc
    * @Description 批量插入订阅信息
    * @Date 17:38 2018/4/24
    * @Param [list]
    * @return void
    **/
    public void insertSubscribeList(@Param("list")List<Subscribe> list);

    /*
    * @author hjc
    * @Description  根据状态查询订阅
    * @Date 18:02 2018/4/27
    * @Param [subscribeStatus]
    * @return java.util.List<com.netposa.gat.model.Subscribe>
    **/
    public List<Subscribe> getSubscribeByStatus(@Param("subscribeStatus") int subscribeStatus);

    /*
    * @author hjc
    * @Description  取消订阅，写入订阅取消单位，订阅取消人，取消时间，取消原因
    * @Date 13:57 2018/4/27
    * @Param [subscribe]
    * @return int
    **/
    public int cancelSubscribe(@Param("subscribe") Subscribe subscribe);

    /*
    * @author hjc
    * @Description 批量更新订阅
    * @Date 17:20 2018/4/27
    * @Param [subscribeList]
    * @return int
    **/
    public int updateSubscribe(@Param("list") List<Subscribe> subscribeList);

    /*
    * @author hjc
    * @Description 批量删除订阅
    * @Date 15:35 2018/4/27
    * @Param [subscribeIds]
    * @return int
    **/
    public int deleteSubscribe(List<String> subscribeIds);

    /*
    * @author hjc
    * @Description 批量查询订阅
    * @Date 16:22 2018/4/27
    * @Param [subscribesIds]
    * @return java.util.List<com.netposa.gat.model.Subscribe>
    **/
    public List<Subscribe> querySubscribes(@Param("subscribesIds") List<String> subscribesIds);

    /**
     *  根据主键ID更新SubscribeStatus
     * @param subscribe
     * @return
     */
    public int updateSubscribeStatus(@Param("subscribe") Subscribe subscribe);

    /**
     * 根据ID获取订阅实体记录
     * @param id
     * @return
     */
    public Subscribe getSubscribeById(@Param("ID") String id);
}
