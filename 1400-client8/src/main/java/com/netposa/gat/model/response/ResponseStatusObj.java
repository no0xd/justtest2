package com.netposa.gat.model.response;

import lombok.Data;

/**
 * 1400响应单个对象
 * @author netposa
 *
 */
@Data
public class ResponseStatusObj {

	private ResponseStatusObject ResponseStatusObject;

	@Data
	public static class ResponseStatusObject{
		/**
		 * 资源定位符
		 */
		private String RequestURL;

		/**
		 * 状态码
		 * O:OK   1:其他未知错误  2：设备忙  3：设备错
		 * 4：无效操作  5：XML格式无效  6：XML内容无效
		 * 7：JSON格式无效  8：JSON内容无效  9：系统重启中
		 */
		private String StatusCode;

		/**
		 * 状态描述
		 */
		private String StatusString;

		/**
		 * 资源ID
		 * POST方法创建资源时会返回ID，创建成功后必须返回新的ID，创建失败则无此ID
		 */
		private String Id;

		/**
		 * 当前时间，用于需要校时的场合
		 */
		private String LocalTime ;
	}
}
