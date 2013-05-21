package com.java.yufa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.java.cifa.CifaAnalyse;

public class YufaAnalyse {
	private String[][] key = new String[1000][2];
	private int num = 0;
	private int curr = 0;
	private int wrong = 1;
	private int row = 1;
	private boolean bool = true;
	private String xx;
	private PrintWriter output;
	public void analysis(String inputFile, String outputFile){
		CifaAnalyse cifaAnalyse = new CifaAnalyse();
		cifaAnalyse.analysis(inputFile, outputFile);
		
		try {
			File outFile = new File(outputFile);
			if(outFile.exists() == false){
				outFile.createNewFile();
			}
			output = new PrintWriter(new FileOutputStream(outFile,true));
		} catch (IOException e) {
			System.out.println("����ļ�����");
		}
		num = cifaAnalyse.getK();
		for (int i = 0; i < num; i++) {
			key[i][0] = cifaAnalyse.result[i][0];
			key[i][1] = cifaAnalyse.result[i][1];
		}
		run();
	}

	/* �﷨�������� */
	public void run() {
		try {
			if (Integer.parseInt(key[curr][0]) == 10) {
				boolean yn = doWhile();
				if (!yn) {
					output.println("�﷨���󣬴���λ��Ϊ��" + row + "���" + wrong
							+ "������");
					findNext();
				} else
					output.println("While-do ѭ����䣬Ƕ�׸�ֵ���");
				wrong = 1;
			} else if (Integer.parseInt(key[curr][0]) == 6) {
				boolean yn = branch();
				if (!yn) {
					output.println("�﷨���󣬴���λ��Ϊ��" + row + "���" + wrong
							+ "������");
					findNext();
				} else
					output.println(xx);
				wrong = 1;
			} else if (Integer.parseInt(key[curr][0]) == 11) {
				boolean yn = setValue();
				if (!yn) {
					output.println("�﷨���󣬴���λ��Ϊ��" + row + "���" + wrong
							+ "������");
					findNext();
				} else
					output.println("��ֵ���");
				wrong = 1;
			} else {
				findNext();
			}
			bool = true;
			if (curr < num) {
				run();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			output.println("�����ԷֺŽ�β");
		} finally {
			output.flush();
			output.close();
		}
	}

	/* �������﷨�������ķ������� */
	private boolean doWhile() {
		boolean yn;
		curr++;
		wrong++;
		isBool();
		if (Integer.parseInt(key[curr][0]) == 9) {
			curr++;
			wrong++;
			if (setValue()) {
				yn = true;
			} else
				yn = false;
		} else
			yn = false;
		return yn;
	}

	/* ѭ�������ж� */
	private boolean branch() {
		boolean yn;
		curr++;
		wrong++;
		if (isBool()) {
			if (Integer.parseInt(key[curr][0]) == 7) {
				curr++;
				wrong++;
				if (setValue()) {
					if (Integer.parseInt(key[curr][0]) == 21) {
						yn = true;
						xx = "If-then��֧��䣬Ƕ�׸�ֵ���";
					} else if (Integer.parseInt(key[curr][0]) == 8) {
						curr++;
						wrong++;
						if (setValue()) {
							if (Integer.parseInt(key[curr][0]) == 21) {
								yn = true;
								xx = "If-then-else��֧��䣬Ƕ�׸�ֵ���";
							} else
								yn = false;
						} else
							yn = false;
					} else
						yn = false;
				} else
					yn = false;
			} else {
				yn = false;
			}
		} else
			yn = false;
		return yn;
	}

	/* ��֧�����ж� */
	private boolean setValue() {
		boolean yn;
		if (Integer.parseInt(key[curr][0]) == 11) {
			curr++;
			wrong++;
		}
		if (Integer.parseInt(key[curr][0]) == 23) {
			curr++;
			wrong++;
			if (expression()
					&& (Integer.parseInt(key[curr][0]) == 21 || Integer
							.parseInt(key[curr][0]) == 8)) {
				yn = true;
			} else {
				yn = false;
			}
		} else {
			yn = false;
		}
		return yn;
	}

	/* ���������ж� */
	private void findNext() {
		if (curr < num)
			while (Integer.parseInt(key[curr][0]) != 21)
				curr++;
		wrong = 1;
		row++;
		curr++;
	}

	/* ������һ������ */
	private boolean isBool() {
		boolean yn;
		if (Integer.parseInt(key[curr][0]) == 11
				| Integer.parseInt(key[curr][0]) == 12) {
			curr++;
			wrong++;
			yn = true;
		} // System.out.println("..........."+key[curr][1]);}
		else
			yn = false;
		return yn;
	}

	/* �ж��Ƿ�Ϊ�������߳��� */
	private boolean expression() {

		if (Integer.parseInt(key[curr][0]) == 15) {
			curr++;
			wrong++;

			expression();

			if (Integer.parseInt(key[curr][0]) == 16) {
				curr++;
				wrong++;

				if (Integer.parseInt(key[curr][0]) != 21
						&& Integer.parseInt(key[curr][0]) != 16
						&& Integer.parseInt(key[curr][0]) != 11
						&& Integer.parseInt(key[curr][0]) != 12) {
					goOn();
				}
			}

			else {
				bool = false;
			}
		} else if (Integer.parseInt(key[curr][0]) == 11
				|| Integer.parseInt(key[curr][0]) == 12) {
			curr++;
			wrong++;
			goOn();
		} else
			bool = false;
		return bool;
	}

	/*���ʽ���ж�*/
	private void goOn() {
		if (Integer.parseInt(key[curr][0]) == 13
				| Integer.parseInt(key[curr][0]) == 14
				| Integer.parseInt(key[curr][0]) == 24
				| Integer.parseInt(key[curr][0]) == 25) {
			curr++;
			wrong++;
			expression();
		}
	}
	/*���ʽ�ж��е�һ���ӳ���*/
}
