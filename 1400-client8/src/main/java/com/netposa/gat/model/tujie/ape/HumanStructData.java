package com.netposa.gat.model.tujie.ape;

import lombok.Data;

/**
 * 该模型来自IoD对接文档，改动需要参考文档
 * Created by PeiTing on 2017/8/3.
 */
@Data
public class HumanStructData {
    /**
     * z
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
     * 卡口/结构化原始数据
     */
    private StructSourceInfo sourceInfo;
    
    
    /**
     * 特征
     */
    private NpFeaturesInfo npFeaturesInfo;
    
    /**
     * 原始人体属性信息（暂无，保留）
     */
    private String featuresInfo;

    @Data
    public static final class StructSourceInfo {
        
        /**
         * 特征图,用于特征提取或二次识别只用
         */
        private String traitImgUrl;
        
        /**
         * 场景图保存在pfs上的位置
         */
        private String sceneImgUrl;
        
        /**
         * 人体位置
         */
        private String location;
        
        /**
         * 人体扩展位置
         */
        private String traitLocation;
        
        /**
         * 目标出现时间戳
         */
        private Long startTime;
        
        /**
         * 目标消失时间戳
         */
        private Long endTime;
        
        /**
         * 置信度(0,1)
         */
        private Double confidence;
    }
    
    
    @Data
    public static final class NpFeaturesInfo {
        /**
         * 记录唯一编号,可以对应原始数据
         */
        private String recordId;
        /**
         * 推送时间戳
         */
        private Long absTime;
        /**
         * 推送时间戳(统计延迟使用)
         */
        private Long pushTime;
        /**
         * 目标高度
         */
        private Integer height = -1;
        
        /**
         * 颜色
         */
        private Integer color = -1;
        
        /**
         * 灰度
         */
        private Integer gray = -1;
        
        /**
         * 头颜色
         */
        private Integer headColor = -1;
        
        /**
         * 脚颜色
         */
        private Integer footColor = -1;
        
        //下面属性都是v3的输出
        /**
         * 性别
         */
        private Integer gender = -1;
        /**
         * 上衣类型
         */
        private Integer upperType = -1;
        /**
         * 上衣类型置信度
         */
        private Double upperTypeConfidence = -1D;
        /**
         * 上衣颜色
         */
        private Integer upperColor = -1;
        /**
         * 上衣颜色置信度
         */
        private Double upperColorConfidence = -1D;
        /**
         * 上衣纹理
         */
        private Integer upperPattern = -1;
        /**
         * 上衣纹理置信度
         */
        private Double upperPatternConfidence = -1D;
        /**
         * 帽子
         */
        private Integer hat = -1;
        /**
         * 帽子置信度
         */
        private Double hatConfidence = -1D;
        /**
         * 下衣类型
         */
        private Integer lowerType = -1;
        /**
         * 下衣类型置信度
         */
        private Double lowerTypeConfidence = -1D;
        /**
         * 下衣颜色
         */
        private Integer lowerColor = -1;
        /**
         * 下衣颜色置信度
         */
        private Double lowerColorConfidence = -1D;
        /**
         * 下衣纹理
         */
        private Integer lowerPattern = -1;
        /**
         * 下衣纹理置信度
         */
        private Double lowerPatternConfidence = -1D;
        /**
         * 包类型
         */
        private Integer bagType = -1;
        /**
         * 包类型置信度
         */
        private Double bagTypeConfidence = -1D;
        /**
         * 包颜色
         */
        private Integer bagColor = -1;
        /**
         * 包颜色置信度
         */
        private Double bagColorConfidence = -1D;
        /**
         * 包纹理
         */
        private Integer bagPattern = -1;
        /**
         * 包纹理置信度
         */
        private Double bagPatternConfidence = -1D;
    }
}

