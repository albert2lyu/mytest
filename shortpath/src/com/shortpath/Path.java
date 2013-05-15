package com.shortpath;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//��Path
//���������ı���Ϣ���ʵ����ز����ķ���
public class Path {

	// ���ͼg,��Ӧ�б�l��,��start��end�����·��,����a�з���ǰ�̵ص�
	// �����������·������
	public double getPath(String start, String end, ArrayList<Place> a,
			Graph g, List l) {

		int s = 0, e = 0;

		try {
			if (l.contains(start))// ��ͼ�в����ڵص�start,�׳��쳣
				s = l.indexOf(start);
			else
				throw new Exception();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "��ʼ�ص㲻����", "error",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		try {
			if (l.contains(end))// ��ͼ�в����ڵص�end,�׳��쳣
				e = l.indexOf(end);
			else
				throw new Exception();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "��ֹ�ص㲻����", "error",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		double[] d = new double[g.Vertices() + 1];
		int[] p = new int[g.Vertices() + 1];

		g.ShortestPaths(s, d, p);// ��ö���s��������������·��

		int i = e;
		if (d[e] == Double.MAX_VALUE)// ��������start��end��·��,����0;
			return 0;

		while (i != s) {
			a.add(0, l.get(i));// ���б�a�����β���ǰ�̶���
			i = p[i];

		}

		a.add(0, l.get(s));
		return d[e];
	}

	// ��ͼg���������Ϊstr,λ��Ϊp�Ķ���
	public void add(String str, Graph g, List l, Point p) {
		l.add(l.size(), new Place(str, p));// �ڵص��б���׷���µص�
		g.addPoint();// ��ͼ������¶���
	}

	// ��ͼg����Ӵ�start��end��·��,·������Ϊw
	public void addPath(String start, String end, double w, Graph g, List l) {
		int s = 0, e = 0;
		if (l.contains(start))
			s = l.indexOf(start);
		else
			// ��start����ͼ�еص�,��������
			return;

		if (l.contains(end))
			e = l.indexOf(end);
		else
			// ��end����ͼ�еص�,��������
			return;

		if (g.exsit(s, e))// ��ͼ���Ѵ��ڸ�·��,�����ʧ��,��������
			return;

		g.add(s, e, w);
	}
	
	// ��ͼg����Ӵ�start��end��·��,·������Ϊw
	public void addPath(Point start, Point end, double w, Graph g, List l) {
		int s = 0, e = 0;
		if (l.contains(start))
			s = l.indexOf(start);
		else
			// ��start����ͼ�еص�,��������
			return;

		if (l.contains(end))
			e = l.indexOf(end);
		else
			// ��end����ͼ�еص�,��������
			return;

		if (g.exsit(s, e))// ��ͼ���Ѵ��ڸ�·��,�����ʧ��,��������
			return;

		g.add(s, e, w);
	}

	// ��ͼg��ɾ������Ϊstr�ĵص�
	public void delete(String str, Graph g, List l) {
		g.deletePoint(l.indexOf(str));
		l.remove(str);
	}

	// ��ͼg��ɾ����start��end��·��
	public void deletePath(String start, String end, Graph g, List l) {
		int s = 0, e = 0;
		try {// ��ͼ�в����ڵص�start��end,ɾ��ʧ��,�׳��쳣
			if (l.contains(start))
				s = l.indexOf(start);
			else
				throw new Exception();

			if (l.contains(end))
				e = l.indexOf(end);
			else
				throw new Exception();
			if (g.exsit(s, e) == false)
				throw new Exception();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "����Ҫɾ����·�߲����ڣ�����������", "error",
					JOptionPane.ERROR_MESSAGE);
		}

		g.delete(s, e);// ɾ��s��e֮���·��
	}

	// ��ͼg��������ص�str����·���Ķ���λ��,���ص�a��
	public void getLine(String str, ArrayList<Point> a, Graph g, List l) {

		ArrayList<Integer> b = new ArrayList<Integer>();
		g.getLine(l.indexOf(str), b);// ���Ҫ�Ķ�������
		for (int i = 0; i < b.size(); i++)
			a.add(l.getPoint(b.get(i).intValue()));// ������λ����ӵ�a��
	}
}
