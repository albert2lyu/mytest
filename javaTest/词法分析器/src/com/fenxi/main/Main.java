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
	 * (1)关键字"begin","end","if","then","else","while","do"
	 * (2)运算符："+","-","*","/","=","<",">","<=",">="	
	 * (3)界符：{, }, [, ], ;, ,, ., (, ), :	,
	 * (4)无符号实数
	 * (5)其他标记，如字符串，表示以字母开头的标识符。
	 * (6)空格、回车、换行符跳过。
	 */
	public static void main(String[] args) {
		String s1 = null;
		String s2 = null;
		System.out.println("输入源文件和目标文件名，包括路径和后缀");
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
				System.out.println("读取错误！");
			}
		}
	}
}
