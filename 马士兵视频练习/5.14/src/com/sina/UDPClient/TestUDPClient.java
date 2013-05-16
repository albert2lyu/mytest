package com.sina.UDPClient;
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
		byte buf[] = (new String("hellogirl!")).getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, 
				new InetSocketAddress("127.0.0.1", 6678));
		DatagramSocket ds = new DatagramSocket(8899);
		ds.send(dp);
		ds.close();
	}
}