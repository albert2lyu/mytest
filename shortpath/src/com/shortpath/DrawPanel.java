package com.shortpath;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

//类DrawPanel
//地图的显示,及相关操作后地图的修改,获得新添加地点的位置及获得查询最短路线的端点的位置
public class DrawPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = -2215724210017542936L;

	Graphics2D g2d;
	private ArrayList<Point> a;// 存储图中所有顶点坐标
	public ArrayList<Point> getA() {
		return a;
	}

	private ArrayList<Point> b;// 存储图中所有路线的起始点坐标
	public ArrayList<Point> getB() {
		return b;
	}

	private ArrayList<Point> c;// 存储图中所有路线的终止点坐标
	public ArrayList<Point> getC() {
		return c;
	}
	private ArrayList<Point> d = new ArrayList<Point>();// 依次存储最短路径途径点坐标
	private ArrayList<Point> e = new ArrayList<Point>();// 依次存储上次查询最短路径途径点坐标,用于还原地图
	ArrayList<Point> f = new ArrayList<Point>();// 用于存储鼠标点击获得的查询最短路径的两点坐标
	private ArrayList<String> name = new ArrayList<String>();// 用于存储个地点名称
	public ArrayList<String> getN() {
		return name;
	}
	int n;
	static int xPos, yPos;
	Point p;
	String str = "", str1, str2;

	// 画图方法
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);// 设置背景颜色
		g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);// 设置画笔颜色
		for (int i = 0; i < b.size(); i++) {
			g2d.drawLine(b.get(i).x, b.get(i).y, c.get(i).x, c.get(i).y);// 画路径
			g2d.drawString(w.get(i), (b.get(i).x + c.get(i).x) / 2,
					(b.get(i).y + c.get(i).y) / 2);// 写入路径长度
		}

		g2d.setColor(Color.blue);
		for (int i = 0; i < a.size(); i++)
			g2d.fillOval(a.get(i).x, a.get(i).y, n, n);// 画地点

		for (int i = 0; i < name.size(); i++)
			g2d.drawString(name.get(i), a.get(i).x - 3, a.get(i).y - 3);// 写地点名称

		for (int i = 0; i < e.size(); i++) {
			g2d.setColor(Color.WHITE);
			g2d.fillOval(e.get(i).x, e.get(i).y, n, n);// 取消前一次查询后画入的红色路线
		}

		// 标识最短路径
		for (int i = 0; i < d.size() - 1; i++) {
			g2d.setColor(Color.RED);
			g2d.drawLine(d.get(i).x + 3, d.get(i).y + 3, d.get(i + 1).x + 3,
					d.get(i + 1).y + 3);
			g2d.fillOval(d.get(i + 1).x, d.get(i + 1).y, 7, 7);
			g2d.fillOval(d.get(0).x, d.get(0).y, 7, 7);
		}

		// 标识选中地点
		for (int i = 0; i < f.size(); i++) {
			g2d.setColor(Color.RED);
			g2d.fillOval(f.get(i).x, f.get(i).y, 7, 7);
		}

	}

	// 画最短路径
	public void paintLine(Point s) {
		d.add(s);// 在最短路径列表中加入顶点s
		repaint();
	}

	// 删除由s到e的路径
	public void deleteLine(Point s, Point e) {
		Point start = new Point(s.x + 3, s.y + 3);
		Point end = new Point(e.x + 3, e.y + 3);

		for (int i = 0; i < b.size(); i++) {
			// 在b和c中寻找指定边,并将其起点和终点移除
			if (b.get(i).equals(start) && c.get(i).equals(end)
					|| b.get(i).equals(end) && c.get(i).equals(start)) {
				b.remove(i);
				c.remove(i);
				w.remove(i);
				repaint();
				break;
			}
		}
	}

	// 只删除顶点s
	public void deletePointOnly(Point s) {
		e.add(s);
		a.remove(s);// 地点列表中移除地点e
		repaint();
		e.clear();
	}

	// 删除名称为str的点s
	public void deletePoint(Point s, String str) {
		e.add(s);
		a.remove(s);// 地点列表中移除地点e
		name.remove(str);// 地点名称列表中移除地点str
		repaint();
		e.clear();
	}

	// 添加名称为s的顶点,该操作用于查询操作时选取地点
	public void addPoint(String s) {
		if (s.equals("") == false) {
			name.add(s);// 地点名称列表中添加地点str
			str = s;
			p = null;
		}
		repaint();
	}

	// 添加从s到e长度为weight的路径
	public void addLine(Point s, Point e, String weight) {
		if (s != null && e != null) {
			b.add(new Point(s.x + 3, s.y + 3));
			c.add(new Point(e.x + 3, e.y + 3));
			w.add(weight);
			repaint();
		}
	}

	// 撤销上次标识的最短路径
	public void noChange() {
		d.clear();// 清空列表d
		repaint();
	}

	ArrayList<String> w = new ArrayList<String>();
	public ArrayList<String> getW() {
		return w;
	}

	// 构造函数
	public DrawPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

		a = new ArrayList<Point>();
		a.add(new Point(100, 150));
		a.add(new Point(300, 150));
		a.add(new Point(100, 350));
		a.add(new Point(300, 350));
		a.add(new Point(450, 250));

		b = new ArrayList<Point>();
		c = new ArrayList<Point>();

		b.add(new Point(103, 153));
		c.add(new Point(303, 153));
		b.add(new Point(103, 153));
		c.add(new Point(103, 353));
		b.add(new Point(303, 153));
		c.add(new Point(303, 353));
		b.add(new Point(103, 353));
		c.add(new Point(303, 353));
		b.add(new Point(303, 153));
		c.add(new Point(453, 253));
		b.add(new Point(303, 353));
		c.add(new Point(453, 253));
		b.add(new Point(103, 153));
		c.add(new Point(453, 253));

		n = 6;

		w.add("4");
		w.add("2");
		w.add("4");
		w.add("1");
		w.add("5");
		w.add("3");
		w.add("8");

		name.add("a");
		name.add("b");
		name.add("c");
		name.add("d");
		name.add("e");

		addMouseListener(this);// 添加鼠标监听
		addMouseMotionListener(this);// 添加鼠标动作监听
	}

	// 返回在name列表中索引为i的元素
	public String getName(int i) {
		if (i >= 0 && i < f.size()) {
			str = name.get(a.indexOf(f.get(i)));
			return str;
		}
		return "";

	}

	// 鼠标响应

	// 鼠标单击
	// 添加新地点或添加查询最短路径的端点
	public void mouseClicked(MouseEvent e) {
		noChange();
		if (f.size() == 2) {
			f.clear();
		}
		xPos = e.getX();// 获得该点x轴坐标
		yPos = e.getY();// 获得该点y轴坐标
		int i;

		// 若点击途中原有地点,则准备查询操作
		for (i = 0; i < a.size(); i++) {
			if (Point.distance(xPos, yPos, a.get(i).x, a.get(i).y) <= n) {
				f.add(a.get(i));// 标识该点
				break;
			}
		}

		// 若点击新位置,添加该新地点
		if (i == a.size()) {
			p = new Point(xPos, yPos);
			a.add(p);// 在地点列表中添加该点
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// 鼠标动作相应
	// 鼠标移动相应
	public void mouseMoved(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 鼠标变为手型
	}

	public void mouseDragged(MouseEvent e) {
	}
}
