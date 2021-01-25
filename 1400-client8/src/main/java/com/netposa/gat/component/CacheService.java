package com.netposa.gat.component;


import com.google.gson.JsonObject;
import com.netposa.gat.model.regist.RegisterContext;


public class CacheService {
	
	private CacheService(){};
	
	public static CacheService getInstance(){
		return CacheServiceHolder.newInstance;
	}
	
	private static class CacheServiceHolder{
		private static CacheService newInstance = new CacheService();
	}

	/**
	 * 注册保活对象缓存
	 */
	private static RegisterContext registerContext ;

	

	public synchronized RegisterContext getRegisterContext() {
		return registerContext;
	}

	public synchronized void setRegisterContext(RegisterContext registerContext) {
		CacheService.registerContext = registerContext;
	}

	/**
	 * 全局Context缓存
	 */
	private static JsonObject gatContext ;

	public synchronized JsonObject getGatContext() {
		return gatContext;
	}

	public synchronized void setGatContext(JsonObject gatContext) {
		CacheService.gatContext = gatContext;
	}
	
	
	
}
