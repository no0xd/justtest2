package com.netposa.gat;

import com.netposa.gat.utils.http.APEsUtil;

public class TestApe {
	public static void main(String[] args) {
		String testRequest="";
		try {
			testRequest = APEsUtil.testRequest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(testRequest);
	}

}
