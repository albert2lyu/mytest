package com.eset.TFMath;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TFMath {
	public static void main(String[] args) {
		new TFFrame().launchFrame();
	}
}
class TFFrame extends Frame {
	TextField num1, num2 ,num3;
	public void launchFrame(){
		num1 = new TextField(10);
		num2 = new TextField(10);
		num3 = new TextField(15);
		Label labelPlus = new Label("+");
		Button btEqual = new Button("=");
		btEqual.addActionListener(new MyMonitor(this));
		setLayout(new FlowLayout());
		add(num1);
		add(labelPlus);
		add(num2);
		add(btEqual);
		add(num3);
		pack();
		setVisible(true);
	}
}
class MyMonitor implements ActionListener {
	private TFFrame tf = null;
	public MyMonitor(TFFrame tf){
		this.tf = tf;
	}
	public void actionPerformed(ActionEvent e){
		int n1 = Integer.parseInt(tf.num1.getText());
		int n2 = Integer.parseInt(tf.num2.getText());
		tf.num3.setText("" + (n1+n2));
	}
}