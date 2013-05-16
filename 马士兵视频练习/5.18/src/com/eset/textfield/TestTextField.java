package com.eset.textfield;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TestTextField {
	public static void main(String[] args) {
		new TFFrame();
	}
}
class TFFrame extends Frame{
	TFFrame(){
		TextField tf = new TextField();
		add(tf);
		tf.addActionListener(new TfActionListener());
		pack();
		setVisible(true);
	}
}
class TfActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		TextField tf = (TextField)e.getSource();
		System.out.println(tf.getText());
		tf.setText(null);
	}
}