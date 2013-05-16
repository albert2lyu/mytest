package com.sina.work3.shape;
public class Rectangle extends Shape{
	private double width;
	private double height;
	public Rectangle(String color, double width, double height) 
		throws MyExceptionColor, MyExceptionZero {
		super(color);
		if(height <= 0 && width <= 0){
			throw new MyExceptionZero("����С�ڻ������");
		} else {
			this.height = height;
		}
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) throws MyExceptionZero {
		if(width <= 0){
			throw new MyExceptionZero("����С�ڻ������");
		} else {
			this.width = width;
		}
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) throws MyExceptionZero {
		if(height <= 0){
			throw new MyExceptionZero("����С�ڻ������");
		} else {
			this.height = height;
		}
	}
	public double calcArea() {
		return width*height;
	}
	public double calcRound() {
		return 2*(width+height);
	}
	public void showInfo() {
		System.out.println("�����Σ�"+getColor()+",�����"+calcArea()+",�ܳ���"+calcRound());
	}
}
