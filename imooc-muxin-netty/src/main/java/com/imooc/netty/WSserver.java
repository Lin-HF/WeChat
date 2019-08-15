package com.imooc.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class WSserver {
	
	private static class SingletionWSServer {
		static final WSserver instance = new WSserver();
	}
	
	public static WSserver getInstance() {
		return SingletionWSServer.instance;
	}

	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	
	public WSserver() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new WSServerInitializer());
	}
	
	public void start() {
		this.future = server.bind(8088);
		System.err.println("netty websocket server 启动完毕");
	}
}


