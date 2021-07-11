package com.wms;


import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wms.model.AccMailContent;
import com.wms.utils.mailing.MailingManager;
import com.wms.utils.mailing.exceptions.RegOverwriteNotAllowedException;
import com.wms.utils.security.JwtUtil;

@SpringBootApplication
public class ServerIIApplication {
	
    public static void main(String[] args) throws RegOverwriteNotAllowedException, UnsupportedEncodingException {
        //Config.
    	JwtUtil.register("WMS_RESTful", "ux0p2Jh1rNdX38gyHPUzqbuciRCy9wZ_KwwDwODo-8BqnRNgVXi5Iky2dM4kHT_NC24VKBQQkyrZAfreVkKtTA");
     	
        MailingManager.regProtection(false);
		MailingManager.reg(AccMailContent.CATAGORY, "customerservice@littlestorage.net", "WMS Global 2020");
    	//run spring-boots.
        SpringApplication.run(ServerIIApplication.class, args);	   	
        System.out.println("程序正在运行...");
    }  
}
