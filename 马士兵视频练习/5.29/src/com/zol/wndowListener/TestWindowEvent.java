package com.zol.wndowListener;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TestWindowEvent {
	/**
	 * @param args
	 * WindowAdapter and ƒ‰√˚¿‡
	 */
	public static void main(String[] args) {
		new MyFrame("MyFrame");
	}
}
class MyFrame extends Frame{
	public MyFrame(String s){
		super(s);
		setLayout(null);
		this.setBounds(200, 200, 400, 400);
		this.setBackground(new Color(200, 200, 255));
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				System.exit(0);
			}
		});
	}
}