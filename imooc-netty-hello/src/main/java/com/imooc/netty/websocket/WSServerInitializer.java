package com.imooc.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		
		//websocket 基于http 协议，所以要有http编解码器
		pipeline.addLast(new HttpServerCodec());
		
		//对写大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());
		
		//对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
		//几乎在netty中的编程，都会用到此handler
		pipeline.addLast(new HttpObjectAggregator(1024 * 64));
		
		//======================以上是用于支持http协议======================
		
		//websocket服务器处理协议，用于制定给客户端链接访问的路由：/ws
		/**
		 * 本handler会帮你处理复杂的繁重的事
		 * 会帮你处理握手动作：handshaking（close， ping，pong） ping + pong = 心跳
		 * 对于websocket来讲，都是以frames进行传输，不同的数据类型对应的frames也不同
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		pipeline.addLast(null);
		
	}

}
