package com.bawei.myserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket����������
 * 
 * @author Administrator
 * 
 */
public class MyServer {
	public static void main(String[] args) {
		try {
			// ʵ����ServerSocket
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("����������");
			// accept�������̣߳��ȴ��ͻ�������
			Socket socket = serverSocket.accept();
			System.out.println("socket ���ӳɹ�");
			// ��ȡ�ͻ��˵���Ϣ
			InputStream inputStream = socket.getInputStream();
			// ��ӡ
			System.out.println("server--�յ���" + inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
