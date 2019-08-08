package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 
 * @author linhaifeng1
 * 
 *
 */

public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		//通过socket channel去获得对应的管道
		ChannelPipeline pipeline = channel.pipeline();
		
		//通过管道添加handler
		//HttpServerCodec是由netty自己提供的助手类
		//当请求到服务端，我们需要做解码，响应到客户端做编码
		pipeline.addLast("", new HttpServerCodec());
		
		//添加自定义的助手类，返回“hello netty”
		pipeline.addLast("customHandler", null);
	}
	
}
