package com.java.cifa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CifaAnalyse {
	private char ch = ' ';
	private int act = 0;
	private char[] context = new char[1000];
	private int current = 0;
	private int strNO = 0;
	private char[] strToken = new char[20];
	private String[] symbol = new String[1000];
	private int symbolNO = 0;
	private int code = 0;
	private int value = 0;
	public String[][] result = new String[1000][2];
	private int k = 0;
	private BufferedReader console;
	private PrintWriter output;
	
	public void analysis(String inputFile, String outputFile) {
		try{
			File inFile = new File(inputFile);
			console = new BufferedReader(
					new InputStreamReader(new FileInputStream(inFile)));
			act = console.read(context);
			console.close();

			File outFile = new File(outputFile);
			if(outFile.exists() == false){
				outFile.createNewFile();
			}
			output = new PrintWriter(new FileOutputStream(outFile));

			while (current < act) {
				analyse();
				strNO = 0;
			}
			for (int i = 0; i < k; i++) {
				output.println("(" + result[i][0] + ","
						+ result[i][1] + ")");
			}
			
		} catch (FileNotFoundException e) {
			output.println("找不到文件！");
		} catch (IOException e) {
			output.println("IOException！");
		} finally {
			output.println();
			output.println();
			output.flush();
			output.close();
		}
	}

	/* 词法分析的流程 */
	public void analyse() {
		GetChar();
		GetBC();
		if (IsLetter()) {
			while (IsLetter() || IsDigit()) {
				Concat();
				GetChar();
			}
			Retract();
			code = Reserve();
			if (code != 0) {
				result[k][0] = String.valueOf(code);
				result[k][1] = String.copyValueOf(strToken, 0, strNO);
				k++;
			} else {
				value = InsertId();
				result[k][0] = "11";
				result[k][1] = symbol[value];
				k++;
			}
		} // 判断标识符
		else if (IsDigit()) {
			while (IsDigit()) {
				Concat();
				GetChar();
			}
			Retract();
			value = InsertConst();
			result[k][0] = "12";
			result[k][1] = String.valueOf(value);
			k++;
		} // 判断常量
		else
			switch (ch) {
			case '+': // 判断+
				result[k][0] = "13";
				result[k][1] = "+";
				k++;
				break;
			case '-': // 判断-
				result[k][0] = "14";
				result[k][1] = "-";
				k++;
				break;
			case '(': // 判断 (
				result[k][0] = "15";
				result[k][1] = "(";
				k++;
				break;
			case ')': // 判断 )
				result[k][0] = "16";
				result[k][1] = ")";
				k++;
				break;
			case '=': // 判断=
				result[k][0] = "17";
				result[k][1] = "=";
				k++;
				break;
			case '>': // 判断 >
				result[k][0] = "18";
				result[k][1] = ">";
				k++;
				break;
			case '<': // 判断 <
				result[k][0] = "19";
				result[k][1] = "<";
				k++;
				break;
			case ',': // 判断 ,
				result[k][0] = "20";
				result[k][1] = ",";
				k++;
				break;
			case ';': // 判断 ;
				result[k][0] = "21";
				result[k][1] = ";";
				k++;
				break;
			case ':':
				GetChar(); // 判断 :
				if (ch != '=') {
					Retract();
					result[k][0] = "22";
					result[k][1] = ":";
					k++;
				} else { // 判断 :=
					result[k][0] = "23";
					result[k][1] = ":=";
					k++;

				}
				break;
			case '*': // 判断 *
				result[k][0] = "24";
				result[k][1] = "*";
				k++;
				break;
			case '/': // 判断 /
				result[k][0] = "25";
				result[k][1] = "/";
			default: // System.out.println("over");
			}
	}

	void GetBC() {
		if (ch == ' ' || ch == '\r' || ch == '\n') {
			GetChar();
			GetBC();
		} else
			;
	}

	/* 检查ch中是否为空白 */
	void GetChar() {
		ch = context[current++];
	}

	/* 将下一输入字符读到ch中 */
	void Concat() {
		strToken[strNO++] = ch;
	}

	/* 将ch中的字符连接到strToken之后 */
	boolean IsLetter() {
		if ((ch <= 'Z' && ch >= 'A') || (ch >= 'a' && ch <= 'z'))
			return true;
		else
			return false;
	}

	/* 判断字符 */
	boolean IsDigit() {
		if (ch <= '9' && ch >= '0')
			return true;
		else
			return false;
	}
	
	/* 判断数字 */
	int Reserve() {
		String word = String.copyValueOf(strToken, 0, strNO);
		if (word.toLowerCase().equals("program"))
			return 1;
		else if (word.toLowerCase().equals("begin"))
			return 2;
		else if (word.toLowerCase().equals("end"))
			return 3;
		else if (word.toLowerCase().equals("var"))
			return 4;
		else if (word.toLowerCase().equals("integer"))
			return 5;
		else if (word.toLowerCase().equals("if"))
			return 6;
		else if (word.toLowerCase().equals("then"))
			return 7;
		else if (word.toLowerCase().equals("else"))
			return 8;
		else if (word.toLowerCase().equals("do"))
			return 9;
		else if (word.toLowerCase().equals("while"))
			return 10;
		else
			return 0;
	}

	/* 判断strToken中的字符串是否为保留字，并返回它的编码 */
	void Retract() {
		--current;
		ch = ' ';
	}
	/* 搜索指示器毁掉一个位置，ch中置空白 */
	int InsertId() {
		symbol[symbolNO] = String.copyValueOf(strToken, 0, strNO);
		return symbolNO++;
	}
	/* 将strToken中的标识符插入符号表，返回符号表指针 */
	int InsertConst() {
		String digit = String.copyValueOf(strToken, 0, strNO);
		return Integer.parseInt(digit);
	}
	/*将strToken中的常量插入常量表，返回符号表指针*/
	public int getK() {
		return k;
	}
}
