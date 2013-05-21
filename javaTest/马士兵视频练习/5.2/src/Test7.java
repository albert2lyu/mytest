//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//public class Test7 {
//	/**
//	 * @param args
//	 * Á÷ FileInputStream and FileOutputStream
//	 */
//	public static void main(String[] args) {
//		int b = 0;
//		FileInputStream in = null;
//		FileOutputStream out = null;
//		try{
//			in = new FileInputStream("E:\\Workspaces\\guan\\src\\com\\form\\form.java");
//			out = new FileOutputStream("E:\\Workspaces\\guan\\src\\com\\form\\ff.java");
//			while((b = in.read()) != -1){
//				out.write(b);
//			}
//			in.close();
//			out.close();
//		} catch(FileNotFoundException e) {
//			System.out.print("zhaobudaowenjian");
//			System.exit(-1);
//		} catch(IOException e1) {
//			System.out.print("fuzhicuowu");
//			System.exit(-1);
//		}
//		System.out.print("fuzhichenggong");	
//	}
//}
