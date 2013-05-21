package com.zol.mouse;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TestMouse {
	public static void main(String[] args) {
		new MyFrame("MyFrame");

	}
}
class MyFrame extends Frame {
	private Button bt;
	private TextField tf;
	public MyFrame(String s){
		super(s);
		bt = new Button("Start");
		tf = new TextField();
		add(bt, BorderLayout.NORTH);
		add(tf, BorderLayout.SOUTH);
		bt.addActionListener(new ActionListener(){
			private int i;
			public void actionPerformed(ActionEvent e){
				tf.setText(e.getActionCommand() + ++i);
			}
		});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		this.pack();
		this.setVisible(true);
	}
}