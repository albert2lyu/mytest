package com.sina.work3.shape;
public abstract class Shape {
	private String color = "";
	public Shape(String c) throws MyExceptionColor {
		if(c.equals(Color.RED.toString()) || c.equals(Color.BLUE.toString()) || 
				c.equals(Color.YELLOW.toString()) || c.equals(Color.GREEN.toString())){
			this.color = c;
		} else {
			throw new MyExceptionColor("指定颜色为RED,YELLOW,BLUE,GREEN");
		}
	}
	public String getColor() {
		return color;
	}
	public void setColor(String c) throws MyExceptionColor {
		if(c.equals(Color.RED.toString()) || c.equals(Color.BLUE.toString()) || 
				c.equals(Color.YELLOW.toString()) || c.equals(Color.GREEN.toString())){
			this.color = c;
		} else {
			throw new MyExceptionColor("指定颜色为RED,YELLOW,BLUE,GREEN");
		}
	}
	abstract void showInfo();
	abstract double calcArea();
	abstract double calcRound();
}
