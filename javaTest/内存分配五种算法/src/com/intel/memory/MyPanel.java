package com.intel.memory;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8509887979033306895L;
	Color c0 = Color.red;
	Color c1 = Color.blue;
	Color c2 = Color.cyan;
	Color c3 = Color.DARK_GRAY;
	Color c4 = Color.gray;
	Color c5 = Color.green;
	Color c6 = Color.LIGHT_GRAY;
	Color c7 = Color.magenta;
	Color c8 = Color.orange;
	Color c9 = Color.pink;
	Color c10 = Color.white;
	Color c11 = Color.yellow;
	Color c12 = new Color(252, 171, 133);
	Color c13 = new Color(142, 185, 242);
	Color c14 = new Color(222, 227, 157);
	Color color[] = {c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14};//����������ҵ����ɫ������
	
	/**
	 * ��ͼʱ����ʼ����
	 * @author jiangshujuan
	 *
	 */
	public class Point {

		int x =0;
		int y = 0;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
	/**
	 * �õ�Ҫ�����������ʼλ��
	 * @param i Ҫ���ĵڼ��������
	 * @return λ��
	 */
	public Point getPoint(int i){
		
		Point p = new Point();
		p.setX(i%5*160+5);
		p.setY(i/5*50+20);
		return p;
	}
	/**
	 * �õ�����ҳ����������ķ����λ��
	 * @param i �ڼ�����ҵ
	 * @return λ��
	 */
	public Point showColorForWork(int i){
		Point p = new Point();
		p.setX(i%15*50+20);
		p.setY(i/15*40+170);
		return p;
	}
	/**
	 * ��15�������
	 */
	public void paintComponent(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.black);

		for(int i=0;i<15;i++){
			Point p = getPoint(i);
			g.drawString("��"+i+"�������", p.getX(), p.getY());
			g.drawRect(p.getX(), p.getY()-10, 150, 40);
		}
		
		g.setColor(c);
		
	}
	/**
	 * ��ʾ��ҵ��Ӧ�ڳ����е���ɫ
	 */
	public void showColor(List<Job> jobList){
		Graphics g = this.getGraphics();
		for(int i=0;i<jobList.size();i++){
			Point p2 = showColorForWork(i);
			g.drawString("��ҵ"+(i+1), p2.getX(), p2.getY());
			Color c = g.getColor();
			g.setColor(color[i]);
			g.fillRect(p2.getX(), p2.getY(), 30, 20);
			g.setColor(c);
		}
	}
	/**
	 * lfu�û�ʱҳ��������������
	 * @param Frame f
	 */
	public void launchPanel(int k,Page page){
		Point p = getPoint(k);
		Graphics g = this.getGraphics();
		Graphics g1 = this.getGraphics();
		Color c = g.getColor();
		g.setColor(color[page.getBelongJobId()-1]);
		g.fillRect(p.getX(), p.getY()-10, 150, 40);
		g1.drawString("��"+page.getBelongJobId()+"����ҵ��ҳ��"+page.getPageId(),p.getX(),p.getY()+3);
		g1.drawString("ռ�������"+k, p.getX(), p.getY()+25);
		g.setColor(c);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}