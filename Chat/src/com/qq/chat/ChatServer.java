package com.qq.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	/**
	 * @param args
	 */
	
	private boolean bConnected = false;
	private ServerSocket ss  = null;
	private boolean started = false;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(7777);
			started = true;
		} catch (BindException e) {
			System.out.println("�˿�ʹ����....");
			System.out.println("��ص���س����������з�������");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			while(started){
				Socket s = ss.accept();
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
		} catch(IOException e){
			System.out.println("hhh");
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		
		public void run() {
			try{
				while(bConnected){
					String str = dis.readUTF();
					for(int i=0; i<clients.size(); i++){
						Client c = clients.get(i);
						c.send(str);
					}
				}
			} catch (EOFException e) {
				System.out.println("Client closed!");
			} catch(IOException e){
				e.printStackTrace();
			} finally {		
				try {
					if(dis != null){
						dis.close();
					}
					if(dos != null){
						dos.close();
					}
					if(s != null){
						s.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		public Client(Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		public void send(String str){
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("tuichule");
			}
		}
	}
}
