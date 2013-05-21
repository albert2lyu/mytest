package com.intel.memory;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2023633216849206487L;
	
	private JTextField tf1 = new JTextField();
	private JTextField tf2 = new JTextField();
	private JTextField tf3 = new JTextField();
	public static JLabel lb8 = new JLabel();
	public static JLabel lb10 = new JLabel();
	public static List l1 = new List();
	public static List l2 = new List();
	public static MyPanel panel3 = new MyPanel();
	public static JLabel lumps[] = new JLabel[15];
	private InternalMemory interMem = null;
	private ExecuteThread et = null;
	private JFileChooser chooser = new JFileChooser();
	private PrintWriter pw = null;
	private BufferedReader br = null;
	
	public static void main(String[] args) {
		new MyFrame().launchFrame();
	}
	
	public void launchFrame(){
		this.setBounds(200, 80, 820, 590);
		this.setLayout(null);
		this.setBackground(new Color(240,240,240));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setBounds(10, 0, 800, 60);
		
		
		panel1.setLayout(new GridLayout(2,3));
		JLabel lb1 = new JLabel("��ҵ����(1-15)��");
		JLabel lb2 = new JLabel("��ҵ��С(10-20)��");
		JLabel lb3 = new JLabel("ҳ�洮����(50-100)��");
		JLabel lb4 = new JLabel("���������ݣ�");
		JLabel lb5 = new JLabel("������ó�ʼ����");
		JLabel lb6 = new JLabel("������ã�");
		JLabel lb7 = new JLabel("   ȱҳ����");
		JLabel lb9 = new JLabel("   ȱҳ�ʣ�");
		JLabel lb11 = new JLabel("");
		JButton bt1 = new JButton("����");
		JButton bt2 = new JButton("���");
		JButton bt3 = new JButton("ȫ������");
		
		panel1.add(lb1);
		panel1.add(tf1);
		panel1.add(lb2);
		panel1.add(tf2);
		panel1.add(lb3);
		panel1.add(tf3);
		panel1.add(lb4);
		panel1.add(bt2);
		panel1.add(lb5);
		panel1.add(bt1);
		panel1.add(lb6);
		panel1.add(bt3);

		l1.setBounds(5, 70, 400, 200);
		this.add(l1);
				
		JButton bt41 = new JButton("OPT");
		JButton bt42 = new JButton("FIFO");
		JButton bt43 = new JButton("LRU");
		JButton bt44 = new JButton("Clock");
		JButton bt45 = new JButton("LFU");
		JButton bt5 = new JButton("��ͣ");
		JButton bt6 = new JButton("����");
		JButton bt7 = new JButton("��");
		JButton bt8 = new JButton("����");

		panel2.setBounds(10, 480, 800, 80);
		panel2.setLayout(new GridLayout(3,5,0,1));
		panel2.add(bt41);
		panel2.add(bt42);
		panel2.add(bt43);
		panel2.add(bt44);
		panel2.add(bt45);
		panel2.add(bt5);
		panel2.add(bt6);
		panel2.add(bt7);
		panel2.add(bt8);
		panel2.add(lb11);
		panel2.add(lb7);
		panel2.add(lb8);
		panel2.add(lb9);
		panel2.add(lb10);
		
		panel3.setBounds(10, 280, 800, 200);
		panel3.setLayout(null);
		this.add(panel3);
		
		l2.setBounds(410, 70, 400, 200);
		l2.add("��ҵ����ҳ�洮�������");
		this.add(l2);
		
		this.add(panel1);
		this.add(panel2);
		
		bt1.addActionListener(new Bt1Monitor());
		bt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Random random = new Random();
				tf1.setText(random.nextInt(15)+1+"");
				tf2.setText(random.nextInt(11)+10+"");
				tf3.setText(random.nextInt(51)+50+"");
			}
		});
		bt3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				l1.removeAll();
				l2.removeAll();
				l2.add("��ҵ����ҳ�洮�������");
				lb8.setText("");
				lb10.setText("");
				panel3.repaint();
				interMem.removeAll();
				interMem = null;
				et = null;
			}
		});
		bt41.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setChoose(1);
				new Thread(et).start();
			}
		});
		bt42.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setChoose(2);
				new Thread(et).start();
			}
		});
		bt43.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setChoose(3);
				new Thread(et).start();
			}
		});
		bt44.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setChoose(4);
				new Thread(et).start();
			}
		});
		bt45.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setChoose(5);
				new Thread(et).start();
			}
		});
		bt5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setIsExecute(false);
			}
		});
		bt6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				interMem.setIsExecute(true);
			}
		});
		bt7.addActionListener(new Bt7Monitor());
		bt8.addActionListener(new Bt8Monitor());
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				System.exit(0);
			}
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	class Bt1Monitor implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(!tf1.getText().equals("") && !tf2.getText().equals("") 
					&& !tf3.getText().equals("")){
				int n1 = Integer.parseInt(tf1.getText());
				int n2 = Integer.parseInt(tf2.getText());
				int n3 = Integer.parseInt(tf3.getText());
				if((n1<1 || n1>15) || (n2<10 || n2>20) || (n3<50 || n3>100)){
					JOptionPane.showMessageDialog(null, "������������������");
				} else {
					interMem = new InternalMemory();
					interMem.allocateJob(n1,n2,n3);
					et = new ExecuteThread(interMem);
					JOptionPane.showMessageDialog(null, "��ʼ���ɹ���ѡ���㷨��ʼ���У�");
				}
			} else {
				JOptionPane.showMessageDialog(null, "�����пգ����������룡");
			}
		}
	}
	class Bt7Monitor implements ActionListener{
		public void actionPerformed(ActionEvent e){		
			chooser.setFileFilter(new FileNameExtensionFilter(null,"txt"));
			Pattern p = null;
			Matcher m = null;
			int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       try {
					br = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsoluteFile()));
					int yesOrNo = JOptionPane.showConfirmDialog(
							null, "�Ƿ��ȡҳ�洮", "s", JOptionPane.YES_NO_OPTION);
					if(yesOrNo == 1){
						p = Pattern.compile("[0-9]{1,2}");
						m = p.matcher(br.readLine());
						if(m.find()){
							tf1.setText(m.group());
						}
						m = p.matcher(br.readLine());
						if(m.find()){
							tf2.setText(m.group());
						}
						m = p.matcher(br.readLine());
						if(m.find()){
							tf3.setText(m.group());
						}
					} else if(yesOrNo == 0){
						p = Pattern.compile("[0-9]{1,2}");
						m = p.matcher(br.readLine());
						if(m.find()){
							tf1.setText(m.group());
						}
						m = p.matcher(br.readLine());
						if(m.find()){
							tf2.setText(m.group());
						}
						m = p.matcher(br.readLine());
						if(m.find()){
							tf3.setText(m.group());
						}
						String line = br.readLine();
						int i = 1;
						while(!line.equals(l2.getItem(0))){		
							p = Pattern.compile("[\\d(1,2),]+$");
							m = p.matcher(line);
							while(m.find()) {
								l1.add("��ҵ"+i+"��ҳ�洮��"+m.group());
								i++;
							}	
							line = br.readLine();
						}
					}
					br.close();
					JOptionPane.showMessageDialog(null, "�Ѿ��ɹ��������ݣ��������ý��г�ʼ����");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		}
	}
	class Bt8Monitor implements ActionListener{
		public void actionPerformed(ActionEvent e){
			chooser.setFileFilter(new FileNameExtensionFilter(null,"txt"));
			int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       try {
					pw = new PrintWriter(chooser.getSelectedFile().getAbsoluteFile());
					pw.println("��ҵ������"+tf1.getText());
					pw.println("��ҵ��С��"+tf2.getText());
					pw.println("ҳ�洮���ȣ�"+tf3.getText());
					for(int i=0; i<l1.getItemCount(); i++){
						pw.println(l1.getItems()[i]);
					}
					for(int i=0; i<l2.getItemCount(); i++){
						pw.println(l2.getItems()[i]);
					}
					pw.flush();
					pw.close();
					JOptionPane.showMessageDialog(null, "�Ѿ��ɹ����棡");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		}
	}
}
