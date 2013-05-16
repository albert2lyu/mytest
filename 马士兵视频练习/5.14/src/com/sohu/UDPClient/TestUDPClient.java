package com.sohu.UDPClient;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
public class TestUDPClient {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long l = 10000L;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(l);
		byte[] b = baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(b, b.length, 
				new InetSocketAddress("127.0.0.1", 5589));
		DatagramSocket ds = new DatagramSocket(5561);
		ds.send(dp);
		System.out.println("sendok!");
		ds.close();
	}
}
