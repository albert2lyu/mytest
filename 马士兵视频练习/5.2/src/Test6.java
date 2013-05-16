//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//public class Test6 {
//	/**
//	 * @param args
//	 * Á÷ FileInputStream
//	 */
//	public static void main(String[] args) {
//		int b = 0;
//		FileInputStream f = null;
//		try{
//			f = new FileInputStream("E:\\Workspaces\\guan\\src\\com\\form\\form.java");
//		} catch(FileNotFoundException e) {
//			System.out.println("zhaobudao");
//			System.exit(-1);
//		}
//		try{
//			long num = 0;
//			while((b=f.read()) != -1){
//				System.out.print((char)b);
//				num++;
//			}
//			f.close();
//			System.out.println();
//			System.out.println(num);
//		} catch(IOException e1) {
//			System.out.println("duqushibai");
//			System.exit(-1);
//		}
//	}
//}
