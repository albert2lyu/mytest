package com.allshu.server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	/**
	 * @param args
	 * @throws IOException 
	 * ����˺Ϳͻ���һ���д�������з���ˣ������пͻ���
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(6666);//6666Ϊ�˿ں�
		while(true){
			Socket s = ss.accept();//����ʽ��û���յ��ͻ������ӣ���һֱͣ��
			System.out.println("A client connect!");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		}
	}
}
