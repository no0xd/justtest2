package com.netposa.gat.service.impl;


import com.netposa.gat.mapper.SubscribeMapper;
import com.netposa.gat.model.subscribe.Subscribe;
import com.netposa.gat.service.ISubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 
 * @author netposa
 *
 */
@Service(value = "subscribeServiceImpl")
public class SubscribeServiceImpl implements ISubscribeService  {
	
    @Autowired
    private SubscribeMapper subscribeMapper;


   /**
    * 保存订阅消息
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void insertSubscribeList(List<Subscribe> list) {
        subscribeMapper.insertSubscribeList(list);
    }

   /**
    * 获取订阅列表
    */
    @Override
    public List<Subscribe> getSubscribeByStatus(int subscribeStatus) {
        return subscribeMapper.getSubscribeByStatus(subscribeStatus);
    }

    /**
    *  取消订阅，写入订阅取消单位，订阅取消人，取消时间，取消原因
    **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int cancelSubscribe(Subscribe subscribe) {
        return subscribeMapper.cancelSubscribe(subscribe);
    }

    /**
    * 批量更新订阅
    **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updateSubscribe(List<Subscribe> subscribeList) {
        return subscribeMapper.updateSubscribe(subscribeList);
    }

    /**
     * 批量删除订阅
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int deleteSubscribe(List<String> subscribeIds) {
        return subscribeMapper.deleteSubscribe(subscribeIds);
    }

    /**
     * 批量查询订阅
     */
    @Override
    public List<Subscribe> querySubscribes(List<String> subscribeIds) {
        return subscribeMapper.querySubscribes(subscribeIds);
    }
    
    /**
     * 更新订阅状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updateSubscribeStatus(Subscribe subscribe) {
        return subscribeMapper.updateSubscribeStatus(subscribe);
    }
    
    /**
     * 根据id获取订阅
     */
    @Override
    public Subscribe getSubscribeById(String id) {
        return subscribeMapper.getSubscribeById(id);
    }

}
