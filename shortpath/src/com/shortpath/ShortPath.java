/*
  主要实现查找任意两地点间最短路径并获得其长度，添加地点，删除地点，添加路线，删除路线操作
  该系统带有模拟地图的加权无向图，直观的表现各地点间的关系及所做操作的响应结果
  可通过鼠标操作或文本输入的形式输入相关信息
  该系统还可获取当前时间，帮助信息,菜单栏选项设有热键
  
  获得最短路径的主要思想:贪婪算法
  数据结构: 二维数组存储加权无向图
          ArrayList存储地点，路径的相关信息
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

//类ShortPath
//GUI的设置,及button的响应
public class ShortPath extends JFrame implements ActionListener {

	private static final long serialVersionUID = 351859015672182830L;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	Graph g = new Graph();
	List l = new List();
	Path p = new Path();
	ArrayList<Place> a = new ArrayList<Place>();

	// 文本区
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
		setTitle("GPS系统");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width / 2 - (WIDTH / 2), screenSize.height / 2
				- (HEIGHT / 2), WIDTH, HEIGHT);// 设置窗口于屏幕中央显示
		Container content = getContentPane();
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		content.setLayout(new BorderLayout());

		JPanel f = new JPanel();
		content.add(f, BorderLayout.CENTER);
		f.setLayout(new BorderLayout());

		JMenuBar menubar = new JMenuBar();// 菜单栏
		f.add(menubar, BorderLayout.NORTH);
		JMenu operate = new JMenu("地图(O)");
		JMenu help = new JMenu("帮助(H)");
		JMenu system = new JMenu("系统(S)");
		menubar.add(operate);
		menubar.add(help);
		menubar.add(system);
		operate.setMnemonic(KeyEvent.VK_O);// 添加热键
		help.setMnemonic(KeyEvent.VK_H);// 添加热键
		system.setMnemonic(KeyEvent.VK_S);

		JMenuItem o1 = new JMenuItem("导入地图数据");
		JMenuItem o2 = new JMenuItem("导出地图数据");
		operate.add(o1);
		operate.add(o2);

		JMenuItem time = new JMenuItem("系统时间");
		system.add(time);

		JMenuItem h1 = new JMenuItem("最优路线查询？");
		JMenuItem h2 = new JMenuItem("删除地点？");
		JMenuItem h3 = new JMenuItem("添加地点？");
		JMenuItem h4 = new JMenuItem("删除路线？");
		JMenuItem h5 = new JMenuItem("添加路线?");
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

		// 设置边框
		dPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.BLUE), "地图", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("宋体", Font.BOLD, 18),
				Color.BLUE));

		tpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.BLUE), "最优路线", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("宋体", Font.BOLD, 15),
				Color.BLACK));
		tpanel.setLayout(new GridLayout(2, 1));
		JPanel panelt1 = new JPanel();
		JPanel panelt2 = new JPanel();
		tpanel.add(panelt1);
		tpanel.add(panelt2);
		text = new JTextField(30);
		dis = new JTextField(30);
		JLabel z = new JLabel("最优路线");
		JLabel l = new JLabel("路线长度(km)");
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
				BorderFactory.createLineBorder(Color.BLUE), "最优路线查询"));
		panel1.setLayout(new GridLayout(4, 1));
		JPanel panel11 = new JPanel();
		JPanel panel12 = new JPanel();
		JPanel panel13 = new JPanel();
		JPanel panel14 = new JPanel();
		panel1.add(panel11);
		panel1.add(panel12);
		panel1.add(panel13);
		panel1.add(panel14);
		JLabel s = new JLabel("起始");
		JLabel e = new JLabel("终止");
		JLabel j = new JLabel("        ↓");
		JLabel empty = new JLabel("        ");
		sf = new JTextField(10);
		ef = new JTextField(10);
		JButton q = new JButton("确定");
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
				BorderFactory.createLineBorder(Color.BLUE), "删除地点"));
		panel5.setLayout(new GridLayout(2, 1));
		JPanel panel51 = new JPanel();
		JPanel panel52 = new JPanel();
		panel5.add(panel51);
		panel5.add(panel52);
		JLabel d = new JLabel("地名");
		delete = new JTextField(10);
		JButton dc = new JButton("删除");
		dc.setPreferredSize(new Dimension(60, 20));
		JLabel empty2 = new JLabel("     ");
		panel51.add(d);
		panel51.add(delete);
		panel52.add(empty2);
		panel52.add(dc);

		JPanel panel6 = new JPanel();
		panel4.add(panel6);
		panel6.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "添加地点"));
		panel6.setLayout(new GridLayout(2, 1));
		JPanel panel61 = new JPanel();
		JPanel panel62 = new JPanel();
		panel6.add(panel61);
		panel6.add(panel62);
		JLabel a = new JLabel("地名");
		add = new JTextField(10);
		JButton ac = new JButton("添加");
		JButton nac = new JButton("取消");
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
				BorderFactory.createLineBorder(Color.BLUE), "删除路线"));
		panel3.setLayout(new GridLayout(4, 1));
		JPanel panel31 = new JPanel();
		JPanel panel32 = new JPanel();
		JPanel panel33 = new JPanel();
		JPanel panel34 = new JPanel();
		panel3.add(panel31);
		panel3.add(panel32);
		panel3.add(panel33);
		panel3.add(panel34);
		JLabel ds = new JLabel("起始");
		JLabel de = new JLabel("终止");
		JLabel dj = new JLabel("        ↓");
		JLabel empty1 = new JLabel("       ");
		dls = new JTextField(10);
		dle = new JTextField(10);
		JButton dl = new JButton("删除路线");
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
				BorderFactory.createLineBorder(Color.BLUE), "添加路线"));
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
		JLabel as = new JLabel("起始");
		JLabel ae = new JLabel("终止");
		JLabel aj = new JLabel("        ↓");
		JLabel aw = new JLabel("路长(km)");
		als = new JTextField(10);
		ale = new JTextField(10);
		weight = new JTextField(7);
		JButton al = new JButton("添加路线");
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

		// 注册监听
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

	// 响应处理
	public void actionPerformed(ActionEvent e) {
		// 最短路线查询响应
		if (e.getActionCommand().equals("确定")) {

			// 初始化
			String path = null;
			a.clear();
			text.setText("");
			dis.setText("");
			dPanel.noChange();
			// 鼠标点击添加,设置文本区
			if (dPanel.f.size() >= 1) {
				String str1 = dPanel.getName(0);
				sf.setText(str1);
				if (dPanel.f.size() == 2) {
					String str2 = dPanel.getName(1);
					ef.setText(str2);
				}
			}

			double line = p.getPath(sf.getText(), ef.getText(), a, g, l);// 获得最短路径

			// 最短路径的文本输出
			if (a.size() != 0) {
				path = a.get(0).getName();
				dPanel.paintLine(a.get(0).getPoint());
			}
			for (int i = 1; i < a.size(); i++) {
				path = path + "→" + a.get(i).getName();
				dPanel.paintLine(a.get(i).getPoint());
			}
			text.setText(path);

			dis.setText(String.valueOf(line));// 最短路径长度文本输出

			// 若起始终止顶点不同但路径长度为0,则不存在这样的路径
			if (sf.getText().equalsIgnoreCase(ef.getText()) == false
					&& line == 0)
				text.setText(sf.getText() + "→" + ef.getText() + "的路线不存在！");

			dPanel.f.clear();// 标识点清除

		}

		// 添加地点操作
		else if (e.getActionCommand().equals("添加")) {
			addPoint();
		}

		// 取消添加
		else if (e.getActionCommand().equals("取消")) {
			dPanel.noChange();
			if (l.contains(dPanel.str))
				p.delete(dPanel.str, g, l);// 在存储结构中删除之前添加的点
			dPanel.deletePoint(dPanel.p, dPanel.str);// 在地图中删除之前添加的点
			add.setText("");// 清空文本区
			dPanel.p = null;
			dPanel.str = "";
		}

		// 添加路线操作
		else if (e.getActionCommand().equals("添加路线")) {
			addPath(null, null, null);
		}

		// 删除地点操作
		else if (e.getActionCommand().equals("删除")) {
			String temp = delete.getText();
			try {
				if (l.contains(temp) == false)// 若图中不存在要删除地点,抛出异常
					throw new Exception();
				ArrayList<Point> a = new ArrayList<Point>();
				dPanel.noChange();
				p.getLine(temp, a, g, l);// 获得邻接顶点
				for (int i = 0; i < a.size(); i++)
					dPanel.deleteLine(l.getPoint(l.indexOf(temp)), a.get(i));// 在地图中删除所有相关路线
				dPanel.deletePoint(l.getPoint(l.indexOf(temp)), temp);// 在地图中删除该地点
				p.delete(temp, g, l);// 实际的删除操作
				delete.setText("");// 清空文本区
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "您所要删除的地点不存在，请重新输入",
						"error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 删除路线操作
		else if (e.getActionCommand().equals("删除路线")) {
			try {
				if (l.contains(dls.getText()) == false
						|| l.contains(dle.getText()) == false)
					throw new Exception();// 若要删除路线的端点有一个不存在,抛出异常
				dPanel.noChange();
				dPanel.deleteLine(l.get(l.indexOf(dls.getText())).getPoint(), l
						.get(l.indexOf(dle.getText())).getPoint());// 在地图中删除该路线
				p.deletePath(dls.getText(), dle.getText(), g, l);// 实际的删除操作
				dls.setText("");// 清空文本区
				dle.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "您所要删除路线不存在，请重新输入",
						"error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 以下为菜单栏选项响应
		// 获取系统时间响应
		else if (e.getActionCommand().equals("系统时间")) {
			JOptionPane.showMessageDialog(null, "时间" + new Date(), "当前时间",
					JOptionPane.INFORMATION_MESSAGE);// new Date()获取系统时间

		} else if (e.getActionCommand().equals("最优路线查询？")) {
			JOptionPane.showMessageDialog(null,
					"分别输入起始/终止地点名\n或用鼠标在地图中点击相应起始/终止地点后\n按确定键查询!", "最优路线查询",
					JOptionPane.INFORMATION_MESSAGE);// new Date()获取系统时间

		} else if (e.getActionCommand().equals("删除地点？")) {
			JOptionPane.showMessageDialog(null, "输入要删除地点并按删除键确认!", "删除地点",
					JOptionPane.INFORMATION_MESSAGE);// new Date()获取系统时间

		} else if (e.getActionCommand().equals("添加地点？")) {
			JOptionPane
					.showMessageDialog(
							null,
							"在地图中用鼠标单击确认要添加地点的具体位置,\n并在文本框中输入该地点名称\n按添加确认\n若添加有误,可按取消键取消本次添加!",
							"添加地点", JOptionPane.INFORMATION_MESSAGE);// new
																		// Date()获取系统时间
		} else if (e.getActionCommand().equals("删除路线？")) {
			JOptionPane.showMessageDialog(null,
					"在文本框中输入要删除路线的起始和终止地点,按删除路线键确认删除!", "删除路线",
					JOptionPane.INFORMATION_MESSAGE);// new Date()获取系统时间

		} else if (e.getActionCommand().equals("添加路线？")) {
			JOptionPane.showMessageDialog(null,
					"在文本框中输入要添加路线的起始和终止地点以及路线长度(km),按添加路线键确认添加!", "添加路线?",
					JOptionPane.INFORMATION_MESSAGE);// new Date()获取系统时间

		} else if (e.getActionCommand().equals("导入地图数据")) {
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
					JOptionPane.showMessageDialog(null, "导入地图数据成功!", "导出地图数据",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "导入地图数据错误，请重新导入!", "导出地图数据",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getActionCommand().equals("导出地图数据")) {
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
					JOptionPane.showMessageDialog(null, "导出地图数据成功!", "导出地图数据",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "导出地图数据错误，请重新导出!", "导出地图数据",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		}
	}

	private void addPath(Point start, Point end, String w) {
		try {
			if(start != null && end != null && w != null){
				if(false == l.contains(start) || false == l.contains(end)){
					throw new IOException();// 若起始或终止地点不存在,抛出异常
				} 
				if(g.exsit(l.indexOf(start), l.indexOf(end))){
					g.delete(l.indexOf(start), l.indexOf(end));// 若添加路线已经存在,则删除
				}
				if(true == isNumeric(w)){
					p.addPath(start, end, Double.parseDouble(w), g, l);
					dPanel.noChange();
					dPanel.addLine(start, end, w); // 地图中的添加操作
				} else {
					throw new IOException();
				}
			} else {
				if (l.contains(als.getText()) == false
						|| l.contains(ale.getText()) == false)
					throw new IOException();// 若起始或终止地点不存在,抛出异常
				if (g.exsit(l.indexOf(als.getText()), l.indexOf(ale.getText())))
					throw new Exception();// 若添加路线已经存在,抛出异常
				String num = weight.getText();
				if(true == isNumeric(num)){
					p.addPath(als.getText(), ale.getText(),
							Double.parseDouble(num), g, l);// 实际的添加操作		
					dPanel.noChange();
					dPanel.addLine(l.getPoint(l.indexOf(als.getText())),
							l.getPoint(l.indexOf(ale.getText())), num); // 地图中的添加操作			
					weight.setText("");// 清空文本区
					als.setText("");
					ale.setText("");
				} else {
					throw new IOException();
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "您所输入的路径有误，请重新输入", "error",
					JOptionPane.ERROR_MESSAGE);
			weight.setText("");
			als.setText("");
			ale.setText("");
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "该路线已经存在，请重新输入", "error",
					JOptionPane.ERROR_MESSAGE);
			weight.setText("");
			als.setText("");
			ale.setText("");
		}
	}

	private void addPoint() {
		dPanel.noChange();
		try {
			if (dPanel.p == null || add.getText().equals(""))// 若为在图中添加新点,抛出异常
				throw new IOException();
			if (l.contains(add.getText()))// 若新添加地点已存在,抛出异常
				throw new Exception();
			p.add(add.getText(), g, l, dPanel.p);// 添加该点
			dPanel.addPoint(add.getText());// 在地图中添加新点名称
			add.setText("");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "请您地图中添加地点位置并输入该地点名称！",
					"error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "该地点已经存在，请您重新输入！", "error",
					JOptionPane.ERROR_MESSAGE);
			dPanel.noChange();
			dPanel.deletePointOnly(dPanel.p);// 只删除之前添加的点
			add.setText("");
			dPanel.p = null;
			dPanel.str = null;
		}
	}

	// main方法
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
