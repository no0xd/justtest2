package com.netposa.gat.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: GAT 1400.3-数据库技术 表A.20 通知对象 页数：50  用于推送数据
 * @author: hjc
 * Date: 2018-04-25
 * Time: 14:39
 */
@Data
public class SubscribeNotificationStruc {

    @JsonProperty("SubscribeNotificationListObject")
    private SubscribeNotificationObject SubscribeNotificationListObject;

    @Data
    public static class SubscribeNotificationObject{
        @JsonProperty("SubscribeNotificationObject")
        private List<SubscribeNotification> SubscribeNotificationObject;
    }

}
