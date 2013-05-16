package com.fenxi.main;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import com.fenxi.analyse.Analyse;
public class Main {
	/**
	 * @param args
	 * (1)�ؼ���"begin","end","if","then","else","while","do"
	 * (2)�������"+","-","*","/","=","<",">","<=",">="	
	 * (3)�����{, }, [, ], ;, ,, ., (, ), :	,
	 * (4)�޷���ʵ��
	 * (5)������ǣ����ַ�������ʾ����ĸ��ͷ�ı�ʶ����
	 * (6)�ո񡢻س������з�������
	 */
	public static void main(String[] args) {
		String s1 = null;
		String s2 = null;
		System.out.println("����Դ�ļ���Ŀ���ļ���������·���ͺ�׺");
		Scanner scan = new Scanner(System.in);	
			s1 = scan.next();
			s2 = scan.next();	
		if(s1 != null && s2 != null){
			try {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(s1));
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(s2)));
				Analyse analyse = new Analyse(isr, bw);
				analyse.Test();
				bw.flush();
				bw.close();
			} catch (IOException e) {
				System.out.println("��ȡ����");
			}
		}
	}
}
