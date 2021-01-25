package com.netposa.gat.service;

import java.util.List;

import com.netposa.gat.model.regist.RegisterContext;

public interface IRegistService {
	
	public void batchRegist(List<RegisterContext> registContexts);
	
	public boolean regist(RegisterContext registerContext );
	
	public boolean isRegist(RegisterContext registerContext);
	
	public void keepAlive(RegisterContext registerContext);
	
	public void batchKeepAlive(List<RegisterContext> registContexts);
}
