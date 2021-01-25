package com.netposa.gat.model.tujie;

import lombok.Data;
/**
 * Author: LvLiuWei
 * Created: 2017/10/25.
 */
@Data
public class VehicleStructData {
    /**
     * 记录编号（通行ID）
     */
    private String recordId;
    /**
     * 抓拍时间戳，如2017-10-27 18:30:00，暂无
     */
    private String absTime;
    /**
     * 推送时间戳（统计延迟使用）,暂无
     */
    private String pushTime;
    /**
     * 0表示卡口数据；1表示二次识别数据
     */
    private String sourceType;
    /**
     * 设备ID/文件ID
     */
    private String sourceId;
    //原始车辆属性信息（暂无，保留）
    private String featuresInfo;
    private VehicleRcgInfo vehicleRcgInfo;
    /**
     * 原始数据
     */
    private VehicleSourceInfo vehicleSourceInfo;
    @Data
    public static final class VehicleSourceInfo {
        //记录编号
        private String jlbh;
        //号牌号码
        private String hphm;
        //号牌种类
        private String hpzl;
        //号牌颜色
        private String hpys;
        //经过时间
        private String jgsj;
        //行驶状态
        private String xszt;
        //车辆速度
        private String clsd;
        //车道方向
        private String cdfx;
        //车外轮廓长（车身长度）
        private String cwkc;
        //车辆类型
        private String cllx;
        //设备编号
        private String sbbh;
        //车道编号
        private String cdbh;
        //车身颜色
        private String csys;
        //车辆品牌
        private String clpp;
        //车辆子品牌
        private String clzpp;
        //图片类型
        private String tplx;
        //全景图片
        private String qjtp;
        //特征图片
        private String tztp;
        //TODO kafka推送时间，考虑放在根数据中
        private String sjkfksj;
        private String yzsj;//delete
        private String sjcz;//delete
        //违章类型
        private String wzlx;
        //TODO delete车道编码
        private String cdbm;
        //人脸数
        private String rls;
        //遮阳板数
        private String zybs;
        //安全带数
        private String aqds;
        //挂件数
        private String gjs;
        //TODO 待确认
        private String clnj;
        //TODO 待确认
        private String clnk;
        //纸巾盒数
        private String zjhs;
        //打电话
        private String ddh;
    }
    /**
     * 二次识别数据
     */
    @Data
    public static final class VehicleRcgInfo {
        //TODO 车标类型序号,与车辆品牌的关系
        private String cblx;
        //车标位置
        private String cbwz;
        //TODO 车标位置置信度or 车标置信度，作用是什么？？？
        private String cbzxd;
        //号牌号码
        private String hphm;
        //号牌颜色
        private String hpys;
        //号牌种类
        private String hpzl;
        //号牌位置
        private String hpwz;
        //号牌置信度
        private String hphmzxd;
        //车辆位置
        private String clwz;
        //车辆位置置信度
        private String clwzzxd;
        //年检标位置
        private String njbwz;
        //年检标数
        private String njbs;
        //摆件位置
        private String bjwz;
        //摆件数
        private String bjs;
        //挂饰位置
        private String gswz;
        //挂饰数
        private String gss;
        //纸巾盒位置
        private String zjhwz;
        //纸巾盒数
        private String zjhs;
        //TODO 挂件位置，与摆件和挂饰的关系
        private String gjwz;
        //主驾遮阳板位置
        private String zjzybwz;
        //TODO 主驾遮阳板置信度，作用是什么？？？
        private String zjzybzxd;
        //副驾遮阳板位置
        private String fjzybwz;
        //副驾遮阳板置信度
        private String fjzybzxd;
        //车辆品牌
        private String clpp;
        //车辆品牌置信度
        private String clppzxd;
        //车辆子品牌
        private String clzpp;
        //车辆子品牌置信度
        private String clzppzxd;
        //车辆年款
        private String clnk;
        //主驾安全带位置
        private String zjaqdwz;
        //主驾未系安全带置信度
        private String zjaqdzxd;
        //副驾安全带位置
        private String fjaqdwz;
        //副驾未系安全带置信度
        private String fjaqdzxd;
        //挡风玻璃位置
        private String dfblwz;
        //主驾开车打电话位置
        private String ddhwz;
        //主驾开车打电话置信度
        private String ddhzxd;
        //车身颜色
        private String csys;
        //TODO 车辆整体信息置信度，作用是什么？？？
        private String clztzxd;
        //车辆类型
        private String cllx;
        //TODO kafka推送时间，考虑放在根数据中
        private String sjkfksj;
        //目前未使用的二次识别信息暂时置于json串中，见json工作表
        private String featureJson;
    }
}