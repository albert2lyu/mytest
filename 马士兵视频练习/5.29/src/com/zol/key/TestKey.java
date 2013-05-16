package com.zol.key;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestKey {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MyFrame().MyFrameLaunch();

	}
	
}
class MyFrame extends Frame{
	private TextField tf;
	public void MyFrameLaunch(){
		tf = new TextField();
		setSize(300, 300);
		setLocation(200, 200);
		add(tf, BorderLayout.SOUTH);
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
//				int keyCode = e.getKeyCode();
//				if(keyCode == KeyEvent.VK_A){
//					tf.setText("anxiale:" + keyCode);
					System.out.println("up");
//				}
			}
		});
		this.pack();
		this.setVisible(true);
	}
}
