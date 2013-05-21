package com.qq.chat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatClient extends Frame{
	
	/**
	 * @param args
	 */
	
	private TextField tfTxt = new TextField();
	private TextArea taTxt = new TextArea();
	private Socket s = null; 
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private boolean bConnected = false;
	private Thread thread = new Thread(new ClientThread());
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();

	}
	
	public void launchFrame(){
		this.setLocation(200,200);
		this.setSize(300, 200);
		this.add(tfTxt, BorderLayout.SOUTH);
		this.add(taTxt, BorderLayout.NORTH);
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				disConnect();
				System.exit(0);
			}
		});
		tfTxt.addActionListener(new TFListener());
		this.setVisible(true);
		connect();
		thread.start();
	}
	
	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String str = tfTxt.getText();
//			taTxt.setText(str);
			tfTxt.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
	}
	
	public void connect(){
		try {
			s = new Socket("127.0.0.1", 7777);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disConnect(){
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ClientThread implements Runnable {

		public void run() {
			try{
				while(bConnected){
					String str = dis.readUTF();
					taTxt.setText(taTxt.getText() + str + '\n');
				}
			}catch (SocketException e) {
				System.out.println("bye!");
			} catch (EOFException e) {
				System.out.println("bye - bye!");
			} catch(IOException e){
				e.printStackTrace();
			}
		}	
	}
}
