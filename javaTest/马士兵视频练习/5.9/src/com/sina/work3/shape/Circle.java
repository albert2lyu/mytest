package com.sina.work3.shape;
public class Circle extends Shape {
	private double r;	
	public Circle(String color, double r) throws MyExceptionColor, MyExceptionZero {
		super(color);
		if(r <= 0){
			throw new MyExceptionZero("长度小于或等于零");
		} else {
			this.r = r;
		}
	}
	public double getR() {
		return r;
	}
	public void setR(double r) throws MyExceptionZero {
		if(r <= 0){
			throw new MyExceptionZero("长度小于或等于零");
		} else {
			this.r = r;
		}
	}
	public double calcArea() {
		return Math.PI*r*r;
	}
	public double calcRound() {
		return 2*Math.PI*r;
	}
	public void showInfo(){
		System.out.println("圆形："+getColor()+",面积："+calcArea()+",周长："+calcRound());
	}
}
