package com.shortpath;

import java.awt.Point;

//��Place
//�洢ĳ���ص������(String)���õص��ڵ�ͼ�е�λ��(Point)
public class Place {
	private String n;// ����
	private Point p;// λ��

	// ���캯��
	// ����nΪ�õص�����,pΪ�õص�λ��
	public Place(String n, Point p) {
		this.n = n;
		this.p = p;
	}

	// �õ�ĳ�ص�����
	public String getName() {
		return n;
	}

	// �õ�ĳ�ص�λ��
	public Point getPoint() {
		return p;
	}

}
