package com.netposa.gat.model.tujie.ape;

import lombok.Data;

import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */
@Data
public class APEStruc {

    private APEObject APEListObject;

    @Data
    public static class APEObject{

        private List<APE> APEObject;

        @Data
        public static class APE{

            private String ApeID;//设备名称  必选

            private String Name;//名称    必选

            private String Model;//型号   必选

            private String IPAddr;//IP地址  必选

            private String IPV6Addr;//IPv6地址

            private String Port;//端口号  必选

            private String Longitude;//经度  必选

            private String Latitude;//纬度  必选

            private String PlaceCode;//安装地点行政区划代码  必选

            private String Place;//位置名

            private String OrgCode;//管辖单位代码

            private String CapDirection;//车辆抓拍方向

            private String MonitorDirection;//监视方向

            private String MonitorAreaDesc;//监视区域说明

            private String IsOnLine;//是否在线  必选

            private String OwnerApsID;//所属采集系统

            private String UserId;//用户账号  文档非必选，和一所联调，必选

            private String Password;//口令    文档非必选，和一所联调，必选
        }
    }
}
