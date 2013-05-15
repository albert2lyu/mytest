package com.shortpath;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//类Path
//包含输入文本信息后的实现相关操作的方法
public class Path {

	// 获得图g,对应列表l中,从start到end的最短路径,并在a中返回前继地点
	// 方法返回最短路径长度
	public double getPath(String start, String end, ArrayList<Place> a,
			Graph g, List l) {

		int s = 0, e = 0;

		try {
			if (l.contains(start))// 若图中不存在地点start,抛出异常
				s = l.indexOf(start);
			else
				throw new Exception();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "起始地点不存在", "error",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		try {
			if (l.contains(end))// 若图中不存在地点end,抛出异常
				e = l.indexOf(end);
			else
				throw new Exception();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "终止地点不存在", "error",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		double[] d = new double[g.Vertices() + 1];
		int[] p = new int[g.Vertices() + 1];

		g.ShortestPaths(s, d, p);// 获得顶点s到其它顶点的最短路径

		int i = e;
		if (d[e] == Double.MAX_VALUE)// 若不存在start到end的路径,返回0;
			return 0;

		while (i != s) {
			a.add(0, l.get(i));// 再列表a中依次插入前继顶点
			i = p[i];

		}

		a.add(0, l.get(s));
		return d[e];
	}

	// 在图g中添加名称为str,位置为p的顶点
	public void add(String str, Graph g, List l, Point p) {
		l.add(l.size(), new Place(str, p));// 在地点列表中追加新地点
		g.addPoint();// 在图中添加新顶点
	}

	// 在图g中添加从start到end的路径,路径长度为w
	public void addPath(String start, String end, double w, Graph g, List l) {
		int s = 0, e = 0;
		if (l.contains(start))
			s = l.indexOf(start);
		else
			// 若start不是图中地点,结束方法
			return;

		if (l.contains(end))
			e = l.indexOf(end);
		else
			// 若end不是图中地点,结束方法
			return;

		if (g.exsit(s, e))// 若图中已存在该路径,则添加失败,结束方法
			return;

		g.add(s, e, w);
	}
	
	// 在图g中添加从start到end的路径,路径长度为w
	public void addPath(Point start, Point end, double w, Graph g, List l) {
		int s = 0, e = 0;
		if (l.contains(start))
			s = l.indexOf(start);
		else
			// 若start不是图中地点,结束方法
			return;

		if (l.contains(end))
			e = l.indexOf(end);
		else
			// 若end不是图中地点,结束方法
			return;

		if (g.exsit(s, e))// 若图中已存在该路径,则添加失败,结束方法
			return;

		g.add(s, e, w);
	}

	// 在图g中删除名称为str的地点
	public void delete(String str, Graph g, List l) {
		g.deletePoint(l.indexOf(str));
		l.remove(str);
	}

	// 在图g中删除从start到end的路径
	public void deletePath(String start, String end, Graph g, List l) {
		int s = 0, e = 0;
		try {// 若图中不存在地点start或end,删除失败,抛出异常
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
			JOptionPane.showMessageDialog(null, "您所要删除的路线不存在，请重新输入", "error",
					JOptionPane.ERROR_MESSAGE);
		}

		g.delete(s, e);// 删除s和e之间的路径
	}

	// 将图g中所有与地点str存在路径的顶点位置,返回到a中
	public void getLine(String str, ArrayList<Point> a, Graph g, List l) {

		ArrayList<Integer> b = new ArrayList<Integer>();
		g.getLine(l.indexOf(str), b);// 获得要的顶点索引
		for (int i = 0; i < b.size(); i++)
			a.add(l.getPoint(b.get(i).intValue()));// 将定定位置添加到a中
	}
}
