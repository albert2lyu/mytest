//package test;
//import java.io.File;
//import java.io.IOException;
//public class Test4 {
//	/**
//	 * @param args
//	 * File对象
//	 * 如果File对象在包中，创建新文件时，会查找包的上层目录，而不是class文件的上层目录
//	 * 比如此例，在执行f.createNewFile();时，会查找test的上层目录
//	 * 而不是Test4.class的上层目录————也就是test所在的目录
//	 */
//	public static void main(String[] args) {
//		String separator = File.separator;
//		String fileName = "mytxt.txt";
//		String directory = "mydir1" + separator + "mydir2";
//		File f = new File(directory,fileName);
//		if(f.exists()){
//			System.out.println("文件名：" + f.getAbsolutePath());
//			System.out.println("文件大小：" + f.length());
//		} else {
//			f.getParentFile().mkdirs();
//			try{
//				f.createNewFile();
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
