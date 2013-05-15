package com.shortpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

//��Graph
//ʹ�ö�ά����洢��Ȩ����ͼ�ĸ���Ȩֵ,��ĳЩ·����Ϣ
public class Graph {

	public static final double NoEdge = Double.MAX_VALUE;// ����:�����û�б�
	private double[][] a;// �洢���߼�Ȩֵ
	private int n;// ������
	private int e;// ����
	private int max;// ������������

	// ���캯��
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

	// ��ָ�����������ڱ�,����ture,���򷵻�false
	public boolean exsit(int i, int j) {
		if (a[i][j] == NoEdge)
			return false;
		return true;
	}

	// ����ͼ�б���
	public int Edge() {
		return e;
	}

	// ����;�ж�����
	public int Vertices() {
		return n;
	}

	// ��ͼ����ӱ�i-j,����Ϊw
	public Graph add(int i, int j, double w) {
		a[i][j] = a[j][i] = w;
		e++;
		return this;
	}

	// ɾ����i-j
	public Graph delete(int i, int j) {
		a[i][j] = a[j][i] = NoEdge;
		e--;
		return this;
	}

	// ����������������,ÿ��������2
	public void resize() {
		double[][] b = new double[max + 2][max + 2];
		max = max + 2;
		for (int i = 1; i <= n; i++)
			// ��a���������ݸ��Ƶ�b
			for (int j = 1; j <= n; j++)
				b[i][j] = a[i][j];
		for (int i = n + 1; i < max; i++)
			// b��������ΪNoEdge
			for (int j = 1; j < max; j++)
				b[i][j] = NoEdge;
		for (int j = n + 1; j < max; j++)
			for (int i = 1; i < max; i++)
				b[i][j] = NoEdge;
		a = b;// ����a������2
	}

	// ��ͼ����Ӷ���
	public void addPoint() {
		if (n == max - 1)// ���Դﵽ�������,resize����a
			resize();
		n++;
	}

	// ɾ��ָ������
	public Graph deletePoint(int v) {

		// ��λ��λ��Ҫɾ�������Ժ����������ıߵ������Ϣǰ��
		for (int i = v; i < n; i++)
			for (int j = 1; j <= n; j++)
				a[i][j] = a[i + 1][j];
		for (int j = v; j < n; j++)
			for (int i = 1; i <= n; i++)
				a[i][j] = a[i][j + 1];

		// �ճ���λ����Ϊ��ʼֵNoEdge
		for (int j = 1; j <= n; j++)
			a[n][j] = NoEdge;
		for (int i = 1; i <= n; i++)
			a[i][n] = NoEdge;
		n--;// ��������һ
		return this;
	}

	// ���붥��v����·���ĵ㷵�ص��б�p��
	public void getLine(int v, ArrayList<Integer> p) {
		for (int i = 1; i <= n; i++)
			if (a[v][i] != NoEdge)
				p.add(new Integer(i));

	}

	// ��ȡ����s��������������·��,dΪ·������,pΪǰ�̶���
	public void ShortestPaths(int s, double[] d, int[] p) {
		LinkedList<Integer> L = new LinkedList<Integer>();// ����L���ڴ洢�ɵ�������ж���
		ListIterator<Integer> l;// L�ı�����

		// ��ʼ��d,p,L
		for (int i = 1; i <= n; i++) {
			d[i] = a[s][i];
			if (d[i] == NoEdge)
				p[i] = 0;
			else {
				p[i] = s;
				L.add(0, new Integer(i));
			}
		}

		// ����d,p
		while (!L.isEmpty()) {// Ѱ�Ҿ�����Сd�Ķ���v
			l = L.listIterator();// ��������ʼ��
			int v = l.next().intValue();
			while (l.hasNext()) {
				int w = l.next().intValue();
				if (d[w] < d[v])
					v = w;
			}

			// ��L��ɾ��ͨ�򶥵�v����һ�����·��������d
			int i = v;
			L.remove(new Integer(v));
			for (int j = 1; j <= n; j++) {
				if (a[i][j] != NoEdge && (p[j] == 0 || d[j] > d[i] + a[i][j])) {// v��j��ֱ��·����;������i������·��
					d[j] = d[i] + a[i][j];// ����d
					if (p[j] == 0)
						L.add(0, new Integer(j));// ��j�ɲ���ֱ�ﵽ�ɴﵽ,��������L
					p[j] = i;
				}
			}

		}
	}
}
