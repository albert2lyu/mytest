package com.sina.work2;

public class ExceptionTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			go();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void go() throws Exception {
		throw new Exception("´íÎó");
	}
}
