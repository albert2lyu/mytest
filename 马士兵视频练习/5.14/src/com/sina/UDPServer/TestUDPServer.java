package com.sina.UDPServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class TestUDPServer {
	/**
	 * @param args
	 * @throws IOException 
	 * Ҳ����Ϊ����ǽ���⣬һֱ���ղ���client�˷��͵İ�
	 * ��������ȷ��ֻ��ÿ�����У�ע����Ķ˿ڣ���Ϊ֮ǰ����ʱ�ѱ�ռ��
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