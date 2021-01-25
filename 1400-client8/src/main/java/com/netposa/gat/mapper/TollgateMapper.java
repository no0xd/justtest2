package com.netposa.gat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.netposa.gat.model.tujie.ape.TollgateObj;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 视频卡口
 * User: hjc
 * Date: 2018-04-23
 * Time: 18:44
 */
@Service
public interface TollgateMapper {
    /**
     * 获取需要推送的全部数据
     * @return
     */
    public List<TollgateObj> getAll();

    /**
     * 根据卡口ID查询数据
     * @return
     */
    public TollgateObj getTollgateObjById(@Param("id") String tollgateId);

}
