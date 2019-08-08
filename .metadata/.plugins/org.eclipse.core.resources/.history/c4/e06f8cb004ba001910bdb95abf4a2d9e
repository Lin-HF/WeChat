package com.imooc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author linhaifeng1
 * @Description: 实现客户端发送一个请求，服务器返回hello netty
 *
 */

public class HelloServer {

	public static void main(String[] args) throws Exception {
		
		//定义一对线程组，也就是两个线程池
		//主线程组，用于接受客户端链接，但不做任何处理
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//从线程组，老板线程组会把任务丢给它，让手下线程组去做任务
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//Netty服务器的创建，ServerBootstrap是一个启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup) //设置主从线程组
							.channel(NioServerSocketChannel.class) //设置nio双向通道
							.childHandler(null); //子处理器，用于处理workerGroup
			
			//启动Server，并且设置8088为启动端口号，同时启动方式为同步
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
			
			//监听关闭的channel，设置为同步方式
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
