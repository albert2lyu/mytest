package com.java.test;

import java.util.Scanner;

//import com.java.cifa.CifaAnalyse;
import com.java.yufa.YufaAnalyse;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFile = null;
		String OutputFile = null;

		Scanner scan = new Scanner(System.in);
		System.out.println("输入源文件和目标文件，包括路径和文件名后缀！");
		inputFile = scan.next();
		OutputFile = scan.next();		
//		String aFile="E:\\java\\input.txt";
//		String bFile="E:\\java\\output.txt";
		
//		CifaAnalyse cifaAnalyse = new CifaAnalyse();
//		cifaAnalyse.analysis(inputFile, OutputFile);
		
		YufaAnalyse yufaAnalyse = new YufaAnalyse();
		yufaAnalyse.analysis(inputFile, OutputFile);
		System.out.println("分析完成！");
	}

}
