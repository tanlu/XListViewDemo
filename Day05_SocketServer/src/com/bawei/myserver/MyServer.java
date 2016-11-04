package com.bawei.myserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务器代码
 * 
 * @author Administrator
 * 
 */
public class MyServer {
	public static void main(String[] args) {
		try {
			// 实例化ServerSocket
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("服务器启动");
			// accept，阻塞线程，等待客户端连接
			Socket socket = serverSocket.accept();
			System.out.println("socket 连接成功");
			// 获取客户端的信息
			InputStream inputStream = socket.getInputStream();
			// 打印
			System.out.println("server--收到流" + inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
