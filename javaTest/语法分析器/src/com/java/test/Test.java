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
		System.out.println("����Դ�ļ���Ŀ���ļ�������·�����ļ�����׺��");
		inputFile = scan.next();
		OutputFile = scan.next();		
//		String aFile="E:\\java\\input.txt";
//		String bFile="E:\\java\\output.txt";
		
//		CifaAnalyse cifaAnalyse = new CifaAnalyse();
//		cifaAnalyse.analysis(inputFile, OutputFile);
		
		YufaAnalyse yufaAnalyse = new YufaAnalyse();
		yufaAnalyse.analysis(inputFile, OutputFile);
		System.out.println("������ɣ�");
	}

}
