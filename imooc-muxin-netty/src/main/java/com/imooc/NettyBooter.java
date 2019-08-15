package com.imooc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.imooc.netty.WSserver;

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (event.getApplicationContext().getParent() == null) {
			try {
				WSserver.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
