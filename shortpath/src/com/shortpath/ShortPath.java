/*
  ��Ҫʵ�ֲ����������ص�����·��������䳤�ȣ���ӵص㣬ɾ���ص㣬���·�ߣ�ɾ��·�߲���
  ��ϵͳ����ģ���ͼ�ļ�Ȩ����ͼ��ֱ�۵ı��ָ��ص��Ĺ�ϵ��������������Ӧ���
  ��ͨ�����������ı��������ʽ���������Ϣ
  ��ϵͳ���ɻ�ȡ��ǰʱ�䣬������Ϣ,�˵���ѡ�������ȼ�
  
  ������·������Ҫ˼��:̰���㷨
  ���ݽṹ: ��ά����洢��Ȩ����ͼ
          ArrayList�洢�ص㣬·���������Ϣ
 */
package com.shortpath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//��ShortPath
//GUI������,��button����Ӧ
public class ShortPath extends JFrame implements ActionListener {

	private static final long serialVersionUID = 351859015672182830L;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	Graph g = new Graph();
	List l = new List();
	Path p = new Path();
	ArrayList<Place> a = new ArrayList<Place>();

	// �ı���
	private JTextField sf;
	private JTextField ef;
	private JTextField text;
	private JTextField dis;
	private JTextField add;
	private JTextField als;
	private JTextField ale;
	private JTextField weight;
	private JTextField delete;
	private JTextField dls;
	private JTextField dle;
	private DrawPanel dPanel;

	public ShortPath() {
		setTitle("GPSϵͳ");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width / 2 - (WIDTH / 2), screenSize.height / 2
				- (HEIGHT / 2), WIDTH, HEIGHT);// ���ô�������Ļ������ʾ
		Container content = getContentPane();
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		content.setLayout(new BorderLayout());

		JPanel f = new JPanel();
		content.add(f, BorderLayout.CENTER);
		f.setLayout(new BorderLayout());

		JMenuBar menubar = new JMenuBar();// �˵���
		f.add(menubar, BorderLayout.NORTH);
		JMenu operate = new JMenu("��ͼ(O)");
		JMenu help = new JMenu("����(H)");
		JMenu system = new JMenu("ϵͳ(S)");
		menubar.add(operate);
		menubar.add(help);
		menubar.add(system);
		operate.setMnemonic(KeyEvent.VK_O);// ����ȼ�
		help.setMnemonic(KeyEvent.VK_H);// ����ȼ�
		system.setMnemonic(KeyEvent.VK_S);

		JMenuItem o1 = new JMenuItem("�����ͼ����");
		JMenuItem o2 = new JMenuItem("������ͼ����");
		operate.add(o1);
		operate.add(o2);

		JMenuItem time = new JMenuItem("ϵͳʱ��");
		system.add(time);

		JMenuItem h1 = new JMenuItem("����·�߲�ѯ��");
		JMenuItem h2 = new JMenuItem("ɾ���ص㣿");
		JMenuItem h3 = new JMenuItem("��ӵص㣿");
		JMenuItem h4 = new JMenuItem("ɾ��·�ߣ�");
		JMenuItem h5 = new JMenuItem("���·��?");
		help.add(h1);
		help.add(h2);
		help.add(h3);
		help.add(h4);
		help.add(h5);

		JPanel bPanel = new JPanel();
		JPanel panel = new JPanel();
		f.add(panel, BorderLayout.CENTER);
		f.add(bPanel, BorderLayout.EAST);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		panel.setLayout(new BorderLayout());
		dPanel = new DrawPanel();
		JPanel tpanel = new JPanel();
		panel.add(dPanel, BorderLayout.CENTER);
		panel.add(tpanel, BorderLayout.SOUTH);

		// ���ñ߿�
		dPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.BLUE), "��ͼ", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("����", Font.BOLD, 18),
				Color.BLUE));

		tpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.BLUE), "����·��", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("����", Font.BOLD, 15),
				Color.BLACK));
		tpanel.setLayout(new GridLayout(2, 1));
		JPanel panelt1 = new JPanel();
		JPanel panelt2 = new JPanel();
		tpanel.add(panelt1);
		tpanel.add(panelt2);
		text = new JTextField(30);
		dis = new JTextField(30);
		JLabel z = new JLabel("����·��");
		JLabel l = new JLabel("·�߳���(km)");
		JLabel k = new JLabel("       ");
		panelt1.add(z);
		panelt1.add(k);
		panelt1.add(text);
		panelt2.add(l);
		panelt2.add(dis);

		bPanel.setPreferredSize(new Dimension(200, 650));
		bPanel.setLayout(new GridLayout(4, 1));

		JPanel panel1 = new JPanel();
		bPanel.add(panel1);
		panel1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "����·�߲�ѯ"));
		panel1.setLayout(new GridLayout(4, 1));
		JPanel panel11 = new JPanel();
		JPanel panel12 = new JPanel();
		JPanel panel13 = new JPanel();
		JPanel panel14 = new JPanel();
		panel1.add(panel11);
		panel1.add(panel12);
		panel1.add(panel13);
		panel1.add(panel14);
		JLabel s = new JLabel("��ʼ");
		JLabel e = new JLabel("��ֹ");
		JLabel j = new JLabel("        ��");
		JLabel empty = new JLabel("        ");
		sf = new JTextField(10);
		ef = new JTextField(10);
		JButton q = new JButton("ȷ��");
		q.setPreferredSize(new Dimension(60, 23));
		panel11.add(s);
		panel11.add(sf);
		panel12.add(j);
		panel13.add(e);
		panel13.add(ef);
		panel14.add(empty);
		panel14.add(q);

		JPanel panel4 = new JPanel();
		bPanel.add(panel4);
		panel4.setLayout(new GridLayout(2, 1));

		JPanel panel5 = new JPanel();
		panel4.add(panel5);
		panel5.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "ɾ���ص�"));
		panel5.setLayout(new GridLayout(2, 1));
		JPanel panel51 = new JPanel();
		JPanel panel52 = new JPanel();
		panel5.add(panel51);
		panel5.add(panel52);
		JLabel d = new JLabel("����");
		delete = new JTextField(10);
		JButton dc = new JButton("ɾ��");
		dc.setPreferredSize(new Dimension(60, 20));
		JLabel empty2 = new JLabel("     ");
		panel51.add(d);
		panel51.add(delete);
		panel52.add(empty2);
		panel52.add(dc);

		JPanel panel6 = new JPanel();
		panel4.add(panel6);
		panel6.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "��ӵص�"));
		panel6.setLayout(new GridLayout(2, 1));
		JPanel panel61 = new JPanel();
		JPanel panel62 = new JPanel();
		panel6.add(panel61);
		panel6.add(panel62);
		JLabel a = new JLabel("����");
		add = new JTextField(10);
		JButton ac = new JButton("���");
		JButton nac = new JButton("ȡ��");
		ac.setPreferredSize(new Dimension(60, 20));
		nac.setPreferredSize(new Dimension(60, 20));
		JLabel empty3 = new JLabel("    ");
		panel61.add(a);
		panel61.add(add);
		panel62.add(empty3);
		panel62.add(ac);
		panel62.add(nac);

		JPanel panel3 = new JPanel();
		bPanel.add(panel3);
		panel3.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "ɾ��·��"));
		panel3.setLayout(new GridLayout(4, 1));
		JPanel panel31 = new JPanel();
		JPanel panel32 = new JPanel();
		JPanel panel33 = new JPanel();
		JPanel panel34 = new JPanel();
		panel3.add(panel31);
		panel3.add(panel32);
		panel3.add(panel33);
		panel3.add(panel34);
		JLabel ds = new JLabel("��ʼ");
		JLabel de = new JLabel("��ֹ");
		JLabel dj = new JLabel("        ��");
		JLabel empty1 = new JLabel("       ");
		dls = new JTextField(10);
		dle = new JTextField(10);
		JButton dl = new JButton("ɾ��·��");
		dl.setPreferredSize(new Dimension(110, 20));
		panel31.add(ds);
		panel31.add(dls);
		panel32.add(dj);
		panel33.add(de);
		panel33.add(dle);
		panel34.add(empty1);
		panel34.add(dl);

		JPanel panel2 = new JPanel();
		bPanel.add(panel2);
		panel2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "���·��"));
		panel2.setLayout(new GridLayout(5, 1));
		JPanel panel21 = new JPanel();
		JPanel panel22 = new JPanel();
		JPanel panel23 = new JPanel();
		JPanel panel24 = new JPanel();
		JPanel panel25 = new JPanel();
		panel2.add(panel21);
		panel2.add(panel22);
		panel2.add(panel23);
		panel2.add(panel24);
		panel2.add(panel25);
		JLabel as = new JLabel("��ʼ");
		JLabel ae = new JLabel("��ֹ");
		JLabel aj = new JLabel("        ��");
		JLabel aw = new JLabel("·��(km)");
		als = new JTextField(10);
		ale = new JTextField(10);
		weight = new JTextField(7);
		JButton al = new JButton("���·��");
		JLabel temp = new JLabel("     ");
		al.setPreferredSize(new Dimension(120, 20));
		panel21.add(as);
		panel21.add(als);
		panel22.add(aj);
		panel23.add(ae);
		panel23.add(ale);
		panel24.add(aw);
		panel24.add(weight);
		panel25.add(temp);
		panel25.add(al);

		// ע�����
		q.addActionListener(this);
		ac.addActionListener(this);
		nac.addActionListener(this);
		al.addActionListener(this);
		dc.addActionListener(this);
		dl.addActionListener(this);
		time.addActionListener(this);
		h1.addActionListener(this);
		h2.addActionListener(this);
		h3.addActionListener(this);
		h4.addActionListener(this);
		h5.addActionListener(this);
		o1.addActionListener(this);
		o2.addActionListener(this);
	}

	// ��Ӧ����
	public void actionPerformed(ActionEvent e) {
		// ���·�߲�ѯ��Ӧ
		if (e.getActionCommand().equals("ȷ��")) {

			// ��ʼ��
			String path = null;
			a.clear();
			text.setText("");
			dis.setText("");
			dPanel.noChange();
			// ��������,�����ı���
			if (dPanel.f.size() >= 1) {
				String str1 = dPanel.getName(0);
				sf.setText(str1);
				if (dPanel.f.size() == 2) {
					String str2 = dPanel.getName(1);
					ef.setText(str2);
				}
			}

			double line = p.getPath(sf.getText(), ef.getText(), a, g, l);// ������·��

			// ���·�����ı����
			if (a.size() != 0) {
				path = a.get(0).getName();
				dPanel.paintLine(a.get(0).getPoint());
			}
			for (int i = 1; i < a.size(); i++) {
				path = path + "��" + a.get(i).getName();
				dPanel.paintLine(a.get(i).getPoint());
			}
			text.setText(path);

			dis.setText(String.valueOf(line));// ���·�������ı����

			// ����ʼ��ֹ���㲻ͬ��·������Ϊ0,�򲻴���������·��
			if (sf.getText().equalsIgnoreCase(ef.getText()) == false
					&& line == 0)
				text.setText(sf.getText() + "��" + ef.getText() + "��·�߲����ڣ�");

			dPanel.f.clear();// ��ʶ�����

		}

		// ��ӵص����
		else if (e.getActionCommand().equals("���")) {
			addPoint();
		}

		// ȡ�����
		else if (e.getActionCommand().equals("ȡ��")) {
			dPanel.noChange();
			if (l.contains(dPanel.str))
				p.delete(dPanel.str, g, l);// �ڴ洢�ṹ��ɾ��֮ǰ��ӵĵ�
			dPanel.deletePoint(dPanel.p, dPanel.str);// �ڵ�ͼ��ɾ��֮ǰ��ӵĵ�
			add.setText("");// ����ı���
			dPanel.p = null;
			dPanel.str = "";
		}

		// ���·�߲���
		else if (e.getActionCommand().equals("���·��")) {
			addPath(null, null, null);
		}

		// ɾ���ص����
		else if (e.getActionCommand().equals("ɾ��")) {
			String temp = delete.getText();
			try {
				if (l.contains(temp) == false)// ��ͼ�в�����Ҫɾ���ص�,�׳��쳣
					throw new Exception();
				ArrayList<Point> a = new ArrayList<Point>();
				dPanel.noChange();
				p.getLine(temp, a, g, l);// ����ڽӶ���
				for (int i = 0; i < a.size(); i++)
					dPanel.deleteLine(l.getPoint(l.indexOf(temp)), a.get(i));// �ڵ�ͼ��ɾ���������·��
				dPanel.deletePoint(l.getPoint(l.indexOf(temp)), temp);// �ڵ�ͼ��ɾ���õص�
				p.delete(temp, g, l);// ʵ�ʵ�ɾ������
				delete.setText("");// ����ı���
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "����Ҫɾ���ĵص㲻���ڣ�����������",
						"error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// ɾ��·�߲���
		else if (e.getActionCommand().equals("ɾ��·��")) {
			try {
				if (l.contains(dls.getText()) == false
						|| l.contains(dle.getText()) == false)
					throw new Exception();// ��Ҫɾ��·�ߵĶ˵���һ��������,�׳��쳣
				dPanel.noChange();
				dPanel.deleteLine(l.get(l.indexOf(dls.getText())).getPoint(), l
						.get(l.indexOf(dle.getText())).getPoint());// �ڵ�ͼ��ɾ����·��
				p.deletePath(dls.getText(), dle.getText(), g, l);// ʵ�ʵ�ɾ������
				dls.setText("");// ����ı���
				dle.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "����Ҫɾ��·�߲����ڣ�����������",
						"error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// ����Ϊ�˵���ѡ����Ӧ
		// ��ȡϵͳʱ����Ӧ
		else if (e.getActionCommand().equals("ϵͳʱ��")) {
			JOptionPane.showMessageDialog(null, "ʱ��" + new Date(), "��ǰʱ��",
					JOptionPane.INFORMATION_MESSAGE);// new Date()��ȡϵͳʱ��

		} else if (e.getActionCommand().equals("����·�߲�ѯ��")) {
			JOptionPane.showMessageDialog(null,
					"�ֱ�������ʼ/��ֹ�ص���\n��������ڵ�ͼ�е����Ӧ��ʼ/��ֹ�ص��\n��ȷ������ѯ!", "����·�߲�ѯ",
					JOptionPane.INFORMATION_MESSAGE);// new Date()��ȡϵͳʱ��

		} else if (e.getActionCommand().equals("ɾ���ص㣿")) {
			JOptionPane.showMessageDialog(null, "����Ҫɾ���ص㲢��ɾ����ȷ��!", "ɾ���ص�",
					JOptionPane.INFORMATION_MESSAGE);// new Date()��ȡϵͳʱ��

		} else if (e.getActionCommand().equals("��ӵص㣿")) {
			JOptionPane
					.showMessageDialog(
							null,
							"�ڵ�ͼ������굥��ȷ��Ҫ��ӵص�ľ���λ��,\n�����ı���������õص�����\n�����ȷ��\n���������,�ɰ�ȡ����ȡ���������!",
							"��ӵص�", JOptionPane.INFORMATION_MESSAGE);// new
																		// Date()��ȡϵͳʱ��
		} else if (e.getActionCommand().equals("ɾ��·�ߣ�")) {
			JOptionPane.showMessageDialog(null,
					"���ı���������Ҫɾ��·�ߵ���ʼ����ֹ�ص�,��ɾ��·�߼�ȷ��ɾ��!", "ɾ��·��",
					JOptionPane.INFORMATION_MESSAGE);// new Date()��ȡϵͳʱ��

		} else if (e.getActionCommand().equals("���·�ߣ�")) {
			JOptionPane.showMessageDialog(null,
					"���ı���������Ҫ���·�ߵ���ʼ����ֹ�ص��Լ�·�߳���(km),�����·�߼�ȷ�����!", "���·��?",
					JOptionPane.INFORMATION_MESSAGE);// new Date()��ȡϵͳʱ��

		} else if (e.getActionCommand().equals("�����ͼ����")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
				@Override
				public String getDescription() {
					return "*.map";
				}

				@Override
				public boolean accept(File arg0) {
					return arg0.getName().endsWith(".map");
				}
			});
			FileSystemView fsv = FileSystemView.getFileSystemView();
			fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				if(true == loadMap(fileChooser.getSelectedFile().getAbsolutePath())){
					JOptionPane.showMessageDialog(null, "�����ͼ���ݳɹ�!", "������ͼ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�����ͼ���ݴ��������µ���!", "������ͼ����",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getActionCommand().equals("������ͼ����")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
				@Override
				public String getDescription() {
					return "*.map";
				}

				@Override
				public boolean accept(File arg0) {
					return arg0.getName().endsWith(".map");
				}
			});
			FileSystemView fsv = FileSystemView.getFileSystemView();
			fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				if (true == createMap(fileChooser.getSelectedFile()
						.getAbsolutePath())) {
					JOptionPane.showMessageDialog(null, "������ͼ���ݳɹ�!", "������ͼ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "������ͼ���ݴ��������µ���!", "������ͼ����",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		}
	}

	private void addPath(Point start, Point end, String w) {
		try {
			if(start != null && end != null && w != null){
				if(false == l.contains(start) || false == l.contains(end)){
					throw new IOException();// ����ʼ����ֹ�ص㲻����,�׳��쳣
				} 
				if(g.exsit(l.indexOf(start), l.indexOf(end))){
					g.delete(l.indexOf(start), l.indexOf(end));// �����·���Ѿ�����,��ɾ��
				}
				if(true == isNumeric(w)){
					p.addPath(start, end, Double.parseDouble(w), g, l);
					dPanel.noChange();
					dPanel.addLine(start, end, w); // ��ͼ�е���Ӳ���
				} else {
					throw new IOException();
				}
			} else {
				if (l.contains(als.getText()) == false
						|| l.contains(ale.getText()) == false)
					throw new IOException();// ����ʼ����ֹ�ص㲻����,�׳��쳣
				if (g.exsit(l.indexOf(als.getText()), l.indexOf(ale.getText())))
					throw new Exception();// �����·���Ѿ�����,�׳��쳣
				String num = weight.getText();
				if(true == isNumeric(num)){
					p.addPath(als.getText(), ale.getText(),
							Double.parseDouble(num), g, l);// ʵ�ʵ���Ӳ���		
					dPanel.noChange();
					dPanel.addLine(l.getPoint(l.indexOf(als.getText())),
							l.getPoint(l.indexOf(ale.getText())), num); // ��ͼ�е���Ӳ���			
					weight.setText("");// ����ı���
					als.setText("");
					ale.setText("");
				} else {
					throw new IOException();
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "���������·����������������", "error",
					JOptionPane.ERROR_MESSAGE);
			weight.setText("");
			als.setText("");
			ale.setText("");
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "��·���Ѿ����ڣ�����������", "error",
					JOptionPane.ERROR_MESSAGE);
			weight.setText("");
			als.setText("");
			ale.setText("");
		}
	}

	private void addPoint() {
		dPanel.noChange();
		try {
			if (dPanel.p == null || add.getText().equals(""))// ��Ϊ��ͼ������µ�,�׳��쳣
				throw new IOException();
			if (l.contains(add.getText()))// ������ӵص��Ѵ���,�׳��쳣
				throw new Exception();
			p.add(add.getText(), g, l, dPanel.p);// ��Ӹõ�
			dPanel.addPoint(add.getText());// �ڵ�ͼ������µ�����
			add.setText("");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "������ͼ����ӵص�λ�ò�����õص����ƣ�",
					"error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "�õص��Ѿ����ڣ������������룡", "error",
					JOptionPane.ERROR_MESSAGE);
			dPanel.noChange();
			dPanel.deletePointOnly(dPanel.p);// ֻɾ��֮ǰ��ӵĵ�
			add.setText("");
			dPanel.p = null;
			dPanel.str = null;
		}
	}

	// main����
	public static void main(String args[]) {

		ShortPath gps = new ShortPath();

		WindowDestroyer myListener = new WindowDestroyer();
		gps.addWindowListener(myListener);
	}

	private boolean createMap(String fileName) {
		Document doc = DocumentHelper.createDocument();
		Element config = doc.addElement("Config");
		Element points = config.addElement("Points");
		Element point;
		for (int i = 0; i < dPanel.getA().size(); i++) {
			point = points.addElement("Point");
			point.addAttribute("X", String.valueOf(dPanel.getA().get(i).x));
			point.addAttribute("Y", String.valueOf(dPanel.getA().get(i).y));
			point.addAttribute("N", dPanel.getN().get(i));
		}
		Element Paths = config.addElement("Paths");
		Element path;
		for (int i = 0; i < dPanel.getB().size(); i++) {
			path = Paths.addElement("Path");
			path.addAttribute("StartX",
					String.valueOf(dPanel.getB().get(i).x - 3));
			path.addAttribute("StartY",
					String.valueOf(dPanel.getB().get(i).y - 3));
			path.addAttribute("EndX",
					String.valueOf(dPanel.getC().get(i).x - 3));
			path.addAttribute("EndY",
					String.valueOf(dPanel.getC().get(i).y - 3));
			path.addAttribute("Weight", dPanel.getW().get(i));
		}
		try {
			if (false == fileName.endsWith(".map")) {
				fileName = fileName + ".map";
			}
			XMLWriter write = new XMLWriter(new FileOutputStream(new File(fileName)));
			write.write(doc);
			write.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private boolean loadMap(String fileName) {
		SAXReader reader = new SAXReader();
		dPanel.getA().clear();
		dPanel.getB().clear();
		dPanel.getC().clear();
		dPanel.getW().clear();
		dPanel.getN().clear();
		l.getList().clear();
		l.add(0,new Place(new String("empty"),new Point(0,0)));
		try {
			Document doc = reader.read(new File(fileName));
			Element points = doc.getRootElement().element("Points");
			points.accept(new PointVisitor());
			Element paths = doc.getRootElement().element("Paths");
			paths.accept(new PathVisitor());
			return true;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	public class PointVisitor extends VisitorSupport {
		public void visit(Element element) {
			if(element.getName().equals("Point")){
				int x = Integer.parseInt(element.attributeValue("X"));
				int y = Integer.parseInt(element.attributeValue("Y"));
				dPanel.p = new Point(x, y);
				dPanel.getA().add(dPanel.p);
				String n = element.attributeValue("N");
				add.setText(n);
				addPoint();
			}	
		}
	}
	public class PathVisitor extends VisitorSupport {
		public void visit(Element element) {
			if(element.getName().equals("Path")){
				int sx = Integer.parseInt(element.attributeValue("StartX"));
				int sy = Integer.parseInt(element.attributeValue("StartY"));
				int ex = Integer.parseInt(element.attributeValue("EndX"));
				int ey = Integer.parseInt(element.attributeValue("EndY"));
				String weight = element.attributeValue("Weight");
				Point start = new Point(sx, sy);
				Point end = new Point(ex, ey);
				addPath(start, end, weight);
			}	
		}
	}
	
	public boolean isNumeric(String str){ 
		return str.matches("\\d*");
	}
}
