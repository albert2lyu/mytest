package com.sohu.UDPServer;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class TestUDPServer {
	/**
	 * @param args
	 * @throws IOException 
	 * ͬ����eset����ǽ���⣬����ǰ�رգ��Լ����Ķ˿ں�
	 */
	public static void main(String[] args) throws IOException {
		byte[] b = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b, b.length);
		DatagramSocket ds = new DatagramSocket(5589);
		while(true){
			ds.receive(dp);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream dis  = new DataInputStream(bais);
			System.out.println(dis.readLong());
		}
	}
}
