package com.shortpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

//类Graph
//使用二维数组存储加权无向图的各边权值,即某些路径信息
public class Graph {

	public static final double NoEdge = Double.MAX_VALUE;// 常量:两点间没有边
	private double[][] a;// 存储各边间权值
	private int n;// 顶点数
	private int e;// 边数
	private int max;// 数组的最大容量

	// 构造函数
	public Graph() {
		n = 5;
		e = 7;
		max = n + 1;
		a = new double[][] {{ 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 4, 2, NoEdge, 8 },
							{ 0, 4, 0, NoEdge, 4, 5 }, 
							{ 0, 2, NoEdge, 0, 1, NoEdge },
							{ 0, NoEdge, 4, 1, 0, 3 }, 
							{ 0, 8, 5, NoEdge, 3, NoEdge } };
	}

	// 若指定两顶点间存在边,返回ture,否则返回false
	public boolean exsit(int i, int j) {
		if (a[i][j] == NoEdge)
			return false;
		return true;
	}

	// 返回图中边数
	public int Edge() {
		return e;
	}

	// 返回途中顶点数
	public int Vertices() {
		return n;
	}

	// 向图中添加边i-j,长度为w
	public Graph add(int i, int j, double w) {
		a[i][j] = a[j][i] = w;
		e++;
		return this;
	}

	// 删除边i-j
	public Graph delete(int i, int j) {
		a[i][j] = a[j][i] = NoEdge;
		e--;
		return this;
	}

	// 扩充数组的最大容量,每次容量加2
	public void resize() {
		double[][] b = new double[max + 2][max + 2];
		max = max + 2;
		for (int i = 1; i <= n; i++)
			// 把a中已有内容复制到b
			for (int j = 1; j <= n; j++)
				b[i][j] = a[i][j];
		for (int i = n + 1; i < max; i++)
			// b中其余设为NoEdge
			for (int j = 1; j < max; j++)
				b[i][j] = NoEdge;
		for (int j = n + 1; j < max; j++)
			for (int i = 1; i < max; i++)
				b[i][j] = NoEdge;
		a = b;// 数组a容量加2
	}

	// 向图中添加顶点
	public void addPoint() {
		if (n == max - 1)// 若以达到最大容量,resize数组a
			resize();
		n++;
	}

	// 删除指定顶点
	public Graph deletePoint(int v) {

		// 将位置位于要删除顶点以后的其它顶点的边的相关信息前移
		for (int i = v; i < n; i++)
			for (int j = 1; j <= n; j++)
				a[i][j] = a[i + 1][j];
		for (int j = v; j < n; j++)
			for (int i = 1; i <= n; i++)
				a[i][j] = a[i][j + 1];

		// 空出的位置设为初始值NoEdge
		for (int j = 1; j <= n; j++)
			a[n][j] = NoEdge;
		for (int i = 1; i <= n; i++)
			a[i][n] = NoEdge;
		n--;// 顶点数减一
		return this;
	}

	// 将与顶点v存在路径的点返回到列表p中
	public void getLine(int v, ArrayList<Integer> p) {
		for (int i = 1; i <= n; i++)
			if (a[v][i] != NoEdge)
				p.add(new Integer(i));

	}

	// 获取顶点s到其它顶点的最短路径,d为路径长度,p为前继顶点
	public void ShortestPaths(int s, double[] d, int[] p) {
		LinkedList<Integer> L = new LinkedList<Integer>();// 链表L用于存储可到达的所有顶点
		ListIterator<Integer> l;// L的遍历器

		// 初始化d,p,L
		for (int i = 1; i <= n; i++) {
			d[i] = a[s][i];
			if (d[i] == NoEdge)
				p[i] = 0;
			else {
				p[i] = s;
				L.add(0, new Integer(i));
			}
		}

		// 更新d,p
		while (!L.isEmpty()) {// 寻找具有最小d的顶点v
			l = L.listIterator();// 遍历器初始化
			int v = l.next().intValue();
			while (l.hasNext()) {
				int w = l.next().intValue();
				if (d[w] < d[v])
					v = w;
			}

			// 从L中删除通向顶点v的下一个最点路径并更新d
			int i = v;
			L.remove(new Integer(v));
			for (int j = 1; j <= n; j++) {
				if (a[i][j] != NoEdge && (p[j] == 0 || d[j] > d[i] + a[i][j])) {// v与j无直达路径或途径顶点i课缩短路径
					d[j] = d[i] + a[i][j];// 更新d
					if (p[j] == 0)
						L.add(0, new Integer(j));// 若j由不可直达到可达到,加入链表L
					p[j] = i;
				}
			}

		}
	}
}
