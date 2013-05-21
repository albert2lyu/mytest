package com.baidu.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class TCPClient {
	/**
	 * @param args
	 * 用命令行窗口测试注意去掉package
	 */
	public static void main(String[] args) {
		try{
			Socket s = new Socket("127.0.0.1", 18888);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("hellogirl");
			String str = null;
			if((str=dis.readUTF()) != null){
				System.out.println(str);
			}
			dos.close();
			dis.close();
			s.close();
		} catch(IOException e) {
			System.out.println("cuowu");
		}
	}
}
