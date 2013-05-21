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
	 * 同意有eset防火墙问题，运行前关闭，以及更改端口号
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
