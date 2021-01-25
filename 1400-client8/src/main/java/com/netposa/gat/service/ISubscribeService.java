package com.netposa.gat.service;


import com.netposa.gat.model.subscribe.Subscribe;
import java.util.List;


public interface ISubscribeService {

	public void insertSubscribeList(List<Subscribe> list);

    public List<Subscribe> getSubscribeByStatus(int subscribeStatus);

    public int cancelSubscribe(Subscribe subscribe);

    public int updateSubscribe(List<Subscribe> subscribeList);

    public int deleteSubscribe(List<String> subscribeIds);

    public List<Subscribe> querySubscribes(List<String> subscribeIds);

    public int updateSubscribeStatus(Subscribe subscribe);

    public Subscribe getSubscribeById(String id);
}
