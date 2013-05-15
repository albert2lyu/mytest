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

//��DrawPanel
//��ͼ����ʾ,����ز������ͼ���޸�,�������ӵص��λ�ü���ò�ѯ���·�ߵĶ˵��λ��
public class DrawPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = -2215724210017542936L;

	Graphics2D g2d;
	private ArrayList<Point> a;// �洢ͼ�����ж�������
	public ArrayList<Point> getA() {
		return a;
	}

	private ArrayList<Point> b;// �洢ͼ������·�ߵ���ʼ������
	public ArrayList<Point> getB() {
		return b;
	}

	private ArrayList<Point> c;// �洢ͼ������·�ߵ���ֹ������
	public ArrayList<Point> getC() {
		return c;
	}
	private ArrayList<Point> d = new ArrayList<Point>();// ���δ洢���·��;��������
	private ArrayList<Point> e = new ArrayList<Point>();// ���δ洢�ϴβ�ѯ���·��;��������,���ڻ�ԭ��ͼ
	ArrayList<Point> f = new ArrayList<Point>();// ���ڴ洢�������õĲ�ѯ���·������������
	private ArrayList<String> name = new ArrayList<String>();// ���ڴ洢���ص�����
	public ArrayList<String> getN() {
		return name;
	}
	int n;
	static int xPos, yPos;
	Point p;
	String str = "", str1, str2;

	// ��ͼ����
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);// ���ñ�����ɫ
		g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);// ���û�����ɫ
		for (int i = 0; i < b.size(); i++) {
			g2d.drawLine(b.get(i).x, b.get(i).y, c.get(i).x, c.get(i).y);// ��·��
			g2d.drawString(w.get(i), (b.get(i).x + c.get(i).x) / 2,
					(b.get(i).y + c.get(i).y) / 2);// д��·������
		}

		g2d.setColor(Color.blue);
		for (int i = 0; i < a.size(); i++)
			g2d.fillOval(a.get(i).x, a.get(i).y, n, n);// ���ص�

		for (int i = 0; i < name.size(); i++)
			g2d.drawString(name.get(i), a.get(i).x - 3, a.get(i).y - 3);// д�ص�����

		for (int i = 0; i < e.size(); i++) {
			g2d.setColor(Color.WHITE);
			g2d.fillOval(e.get(i).x, e.get(i).y, n, n);// ȡ��ǰһ�β�ѯ����ĺ�ɫ·��
		}

		// ��ʶ���·��
		for (int i = 0; i < d.size() - 1; i++) {
			g2d.setColor(Color.RED);
			g2d.drawLine(d.get(i).x + 3, d.get(i).y + 3, d.get(i + 1).x + 3,
					d.get(i + 1).y + 3);
			g2d.fillOval(d.get(i + 1).x, d.get(i + 1).y, 7, 7);
			g2d.fillOval(d.get(0).x, d.get(0).y, 7, 7);
		}

		// ��ʶѡ�еص�
		for (int i = 0; i < f.size(); i++) {
			g2d.setColor(Color.RED);
			g2d.fillOval(f.get(i).x, f.get(i).y, 7, 7);
		}

	}

	// �����·��
	public void paintLine(Point s) {
		d.add(s);// �����·���б��м��붥��s
		repaint();
	}

	// ɾ����s��e��·��
	public void deleteLine(Point s, Point e) {
		Point start = new Point(s.x + 3, s.y + 3);
		Point end = new Point(e.x + 3, e.y + 3);

		for (int i = 0; i < b.size(); i++) {
			// ��b��c��Ѱ��ָ����,�����������յ��Ƴ�
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

	// ֻɾ������s
	public void deletePointOnly(Point s) {
		e.add(s);
		a.remove(s);// �ص��б����Ƴ��ص�e
		repaint();
		e.clear();
	}

	// ɾ������Ϊstr�ĵ�s
	public void deletePoint(Point s, String str) {
		e.add(s);
		a.remove(s);// �ص��б����Ƴ��ص�e
		name.remove(str);// �ص������б����Ƴ��ص�str
		repaint();
		e.clear();
	}

	// �������Ϊs�Ķ���,�ò������ڲ�ѯ����ʱѡȡ�ص�
	public void addPoint(String s) {
		if (s.equals("") == false) {
			name.add(s);// �ص������б�����ӵص�str
			str = s;
			p = null;
		}
		repaint();
	}

	// ��Ӵ�s��e����Ϊweight��·��
	public void addLine(Point s, Point e, String weight) {
		if (s != null && e != null) {
			b.add(new Point(s.x + 3, s.y + 3));
			c.add(new Point(e.x + 3, e.y + 3));
			w.add(weight);
			repaint();
		}
	}

	// �����ϴα�ʶ�����·��
	public void noChange() {
		d.clear();// ����б�d
		repaint();
	}

	ArrayList<String> w = new ArrayList<String>();
	public ArrayList<String> getW() {
		return w;
	}

	// ���캯��
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

		addMouseListener(this);// ���������
		addMouseMotionListener(this);// �����궯������
	}

	// ������name�б�������Ϊi��Ԫ��
	public String getName(int i) {
		if (i >= 0 && i < f.size()) {
			str = name.get(a.indexOf(f.get(i)));
			return str;
		}
		return "";

	}

	// �����Ӧ

	// ��굥��
	// ����µص����Ӳ�ѯ���·���Ķ˵�
	public void mouseClicked(MouseEvent e) {
		noChange();
		if (f.size() == 2) {
			f.clear();
		}
		xPos = e.getX();// ��øõ�x������
		yPos = e.getY();// ��øõ�y������
		int i;

		// �����;��ԭ�еص�,��׼����ѯ����
		for (i = 0; i < a.size(); i++) {
			if (Point.distance(xPos, yPos, a.get(i).x, a.get(i).y) <= n) {
				f.add(a.get(i));// ��ʶ�õ�
				break;
			}
		}

		// �������λ��,��Ӹ��µص�
		if (i == a.size()) {
			p = new Point(xPos, yPos);
			a.add(p);// �ڵص��б�����Ӹõ�
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

	// ��궯����Ӧ
	// ����ƶ���Ӧ
	public void mouseMoved(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ����Ϊ����
	}

	public void mouseDragged(MouseEvent e) {
	}
}
