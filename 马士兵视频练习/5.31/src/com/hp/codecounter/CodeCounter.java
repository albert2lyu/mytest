package com.hp.codecounter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class CodeCounter {
	/**
	 * @param args
	 * 练习正则表达式,统计java文件中正常代码、注释、空白的行数
	 */
	private static int j = 0;
	private static File[] files = new File[10];
	private static int normalLines = 0;
	private static int commentLines = 0;
	private static int whiteLines = 0;
	public static void main(String[] args) {
		File f = new File("E:\\java\\codeCounter");	
		File[] codeFiles = listCodeFiles(f);
		if(codeFiles != null){
			for(int i=0; i<j; i++){
				parse(codeFiles[i]);
			}
		}
		System.out.println("NormalLines:" + normalLines);
		System.out.println("CommentLines:" + commentLines);
		System.out.println("WhiteLines:" + whiteLines);
	}

	private static void parse(File f) {
		BufferedReader br = null;
		boolean comment = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while((line=br.readLine()) != null){
				line = line.trim();
				if(line.matches("^[\\s&&[^\\n]]*$")){
					whiteLines ++;
				} else if(line.startsWith("/*") && !line.endsWith("*/")){
					commentLines ++;
					comment = true;
				} else if(true == comment){
					commentLines ++;
					if(line.endsWith("*/")){
						comment = false;
					}
				} else if(line.startsWith("/*") && line.endsWith("*/")){
					commentLines ++;
				} else if(line.startsWith("//")){
					commentLines ++;
				} else {
					normalLines ++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null){
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}	
	}

	private static File[] listCodeFiles(File f) {
		File[] tempFiles = f.listFiles();
		for(int i=0; i<tempFiles.length; i++){
			if(tempFiles[i].isFile() && tempFiles[i].getName().matches(".*\\.java$")){
				files[j] = tempFiles[i];
				j++;
			} else if(tempFiles[i].isDirectory()){
				listCodeFiles(tempFiles[i]);
			} 
		}
		return files;
	}
}
