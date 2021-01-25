package com.netposa.gat.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netposa.gat.utils.DateUtils;
import lombok.Data;
import java.util.Date;

/**
* @author netposa
* @Description 1400协议返回状态类
**/
@Data
public class ResponseStatus {
	
		/**
	     * 资源定位符
	     */
	    @JsonProperty("RequestURL")
	    private String RequestURL;

	    /**
	     * 状态码
	     * O:OK   1:其他未知错误  2：设备忙  3：设备错
	     * 4：无效操作  5：XML格式无效  6：XML内容无效
	     * 7：JSON格式无效  8：JSON内容无效  9：系统重启中
	     */
	    @JsonProperty("StatusCode")
	    private String StatusCode;

	    /**
	     * 状态描述
	     */
	    @JsonProperty("StatusString")
	    private String StatusString;

	    /**
	     * 资源ID
	     * POST方法创建资源时会返回ID，创建成功后必须返回新的ID，创建失败则无此ID
	     */
	    @JsonProperty("Id")
	    private String Id;

	    /**
	     * 当前时间，用于需要校时的场合
	     */
	    @JsonProperty("LocalTime")
	    private String LocalTime =  DateUtils.Convert2String(new Date(),DateUtils.DateFormatNoSpan);
}
