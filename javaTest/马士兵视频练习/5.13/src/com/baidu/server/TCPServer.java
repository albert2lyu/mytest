package com.baidu.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	/**
	 * @param args
	 * 用命令行窗口测试注意去掉package
	 */
	public static void main(String[] args) {
		try{
			ServerSocket ss = new ServerSocket(18888);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			String str = null;
			if((str=dis.readUTF()) != null){
				System.out.println(str);
				System.out.println(s.getInetAddress());
				System.out.println(s.getPort());
			}
			dos.writeUTF("himen");
			dis.close();
			dos.close();
			s.close();
		} catch(IOException e){
			System.out.println("cuowu");
		}
	}
}
