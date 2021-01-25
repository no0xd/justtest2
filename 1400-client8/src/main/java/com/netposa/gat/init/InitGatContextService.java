package com.netposa.gat.init;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netposa.gat.component.CacheService;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class InitGatContextService {
	
	@Autowired
	public Gson bootGson;
	
	@Autowired
    DataSource bootDataSource;
	
	private static Gson gson;
	
    @PostConstruct
    private void initBean(){
    	gson = bootGson;
    }
    
    public void init(){
    	try{
    		//初始化GatContext
    		String json = FileUtil.getServerConfigJson();
    		JsonObject  jsonObject = gson.fromJson(json, JsonObject.class);
    		if(ObjectUtils.isEmpty(jsonObject)){
    			log.error(" ===========check server.json file failed !=========");
    		}
    		CacheService.getInstance().setGatContext(jsonObject);
    		
    		JsonArray jsonArray = jsonObject.getAsJsonArray("registContextList");
    		List<RegisterContext> contexts = new ArrayList<RegisterContext>();
 	        for (JsonElement jsonElement : jsonArray) {
 	        	RegisterContext context = gson.fromJson(jsonElement,RegisterContext.class); // TypeToken
 	        	contexts.add(context);
 	        }
 	        
    		CacheService.getInstance().setRegisterContext(contexts.get(0));
    		
    	}catch(Exception e){
    		log.error("init rester config Excpetion : {}",e);
    	}
    }
}
