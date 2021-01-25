package com.netposa.gat.model.tujie;

import lombok.Data;

/**
 * Author: LvLiuWei
 * Created: 2017/11/9.
 * 重写非机动车kafka接收数据格式
 */
@Data
public class NonmotorStructData {


    /**
     * 记录唯一编号,可以对应原始数据
     */
    private String recordId;

    /**
     * 抓拍时间戳
     */
    private Long absTime;

    /**
     * 推送时间戳(统计延迟使用)
     */
    private Long pushTime;

    /**
     * 0,卡口,1-实时,2-历史
     */
    private Integer sourceType;

    /**
     * 设备id/文件id
     */
    private String sourceId;

    /**
     * 国标ID
     */
    private String gbId;

    /**
     * 非机动车人体属性信息（暂无，保留）
     */
    private String featuresInfo;

    private NpFeaturesInfo npFeaturesInfo;

    @Data
    public static final class NpFeaturesInfo {
        private String recordId;//记录编号
        private Long absTime;//抓拍时间
        private String deviceId;//设备编号
        private Float nonmotorConf;//非机动车整体置信度
        private String nonmotorLocation;//非机动车位置
        private Float nonmotorLocationConf;//非机动车位置置信度
        private String headLocation;//头部位置
        private Float headLocationConf;//头部位置置信度
        private Integer nonmotorColor;//非机动车车身颜色
        private Float nonmotorColorConf;//非机动车车身颜色置信度
        private Integer nonmotorType;//非机动车类型
        private Float nonmotorTypeConf;//非机动车类型置信度
        private Integer nonmotorAngle;//非机动车角度
        private Float nonmotorAngleConf;//非机动车角度置信度
        private Integer gender;//性别
        private Float genderConf;//性别置信度
        private Integer headSymbol;//头部标识物/是否戴帽
        private Float headSymbolConf;//头部标识物/是否戴帽置信度
        private Integer upType;//上衣类型
        private Float upTypeConf;//上衣类型置信度
        private Integer upColor;//上衣颜色
        private Float upColorConf;//上衣颜色置信度
        private Integer upTexture;//上衣颜色样式/上衣纹理
        private Float upTextureConf;//上衣颜色样式/上衣纹理置信度
        private Integer downType;//下衣类型
        private Float downTypeConf;//下衣类型置信度
        private Integer downColor;//下衣颜色
        private Float downColorConf;//下衣颜色置信度
        private Integer downTexture;//下衣纹理
        private Float downTextureConf;//下衣纹理置信度
        private Integer bagType;//包类型
        private Float bagTypeConf;//包类型置信度
        private Integer bagColor;//包颜色
        private Float bagColorConf;//包颜色置信度
        private Integer bagTexture;//包纹理
        private Float bagTextureConf;//包纹理置信度
        private Integer ethnic;//民族
        private Float ethnicConf;//民族置信度
        private String bodyFeature;//人体特征
        private String traceInfo;//非机动车轨迹

        private Long startTime;//非机动车出现时间
        private Long endTime;//非机动车离开时间

        private String sceneImg;//全景图片
        private String traitImg;//特征图片

        private Long pushTime;//推送时间
    }

}
