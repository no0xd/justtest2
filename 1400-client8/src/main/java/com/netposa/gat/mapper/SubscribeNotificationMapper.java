package com.netposa.gat.mapper;


import org.apache.ibatis.annotations.Param;

import com.netposa.gat.model.notification.SubscribeNotification;
import com.netposa.gat.model.notification.SubscribeNotificationText;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjc
 * Date: 2018-04-25
 * Time: 17:40
 */
public interface SubscribeNotificationMapper {
    /**
    * @author hjc
    * @Description 批量插入通知数据
    * @Date 17:41 2018/4/25
    * @Param [list]
    * @return void
    **/
    public void insertNotificationList(@Param("list") List<SubscribeNotificationText> list);

    /**
     * 根据notificationId查询通知记录
     * @param subscribeNotification
     * @return
     */
    public List<SubscribeNotificationText> qryNotificationList(@Param("notification") SubscribeNotification subscribeNotification);

    /**
     * 删除单个通知记录
     * @param NotificationID
     * @return
     */
    public int removeNotification(@Param("NotificationID") String NotificationID);
}
