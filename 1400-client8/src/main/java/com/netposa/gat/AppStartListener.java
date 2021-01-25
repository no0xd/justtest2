package com.netposa.gat;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.netposa.gat.init.InitAppService;
/**
 * 初始化业务类
 * @author netposa
 *
 */
public class AppStartListener implements ApplicationListener<ContextRefreshedEvent>{

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		InitAppService service = contextRefreshedEvent.getApplicationContext().getBean(InitAppService.class);
	    
		service.init();
	}
}
