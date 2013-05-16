package com.allshu.client;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
public class TCPClient {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 6666);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF("hello server");
		dos.flush();
		dos.close();
		s.close();
	}
}
