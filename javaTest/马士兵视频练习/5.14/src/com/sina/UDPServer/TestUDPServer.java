package com.sina.UDPServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class TestUDPServer {
	/**
	 * @param args
	 * @throws IOException 
	 * 也许因为防火墙问题，一直接收不到client端发送的包
	 * 但运行正确，只是每次运行，注意更改端口，因为之前运行时已被占用
	 */
	public static void main(String[] args) throws IOException {
		byte buf[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		DatagramSocket ds = new DatagramSocket(6678);
		while(true){
			ds.receive(dp);
			System.out.println(new String(buf, 0 , dp.getLength()));
		}
	}
}