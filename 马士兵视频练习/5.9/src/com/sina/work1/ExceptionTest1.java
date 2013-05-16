package com.sina.work1;
import java.util.Scanner;
public class ExceptionTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		int j = 0;
		Scanner scan = new Scanner(System.in);
		i = scan.nextInt();
		try{
			j = 100/i;
			System.out.println(j);
		} catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("over");
		}
	}
}
