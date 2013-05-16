package com.sina.work3.test;
import com.sina.work3.shape.Circle;
import com.sina.work3.shape.MyExceptionColor;
import com.sina.work3.shape.MyExceptionZero;
import com.sina.work3.shape.Rectangle;
public class Test {
	/**
	 * @param args
	 * ���Ը��Ĺ��췽����set�����д��ݵĲ����������쳣����
	 */
	public static void main(String[] args) {
		try {
			Circle cir = new Circle("RED",3);
			Rectangle rect = new Rectangle("BLUE",5,3);
			cir.showInfo();
			rect.showInfo();
			cir.setColor("YELLOW");
			rect.setColor("GREEN");
			cir.setR(5);
			rect.setHeight(7);
		} catch (MyExceptionColor e) {
			System.out.println(e.getMessage());
		} catch (MyExceptionZero ec) {
			System.out.println(ec.getMessage());
		}	
	}
}
