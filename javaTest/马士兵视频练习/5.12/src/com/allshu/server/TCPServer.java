package com.allshu.server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	/**
	 * @param args
	 * @throws IOException 
	 * 服务端和客户端一起编写，先运行服务端，再运行客户端
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(6666);//6666为端口号
		while(true){
			Socket s = ss.accept();//阻塞式，没接收到客户端连接，将一直停滞
			System.out.println("A client connect!");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		}
	}
}
