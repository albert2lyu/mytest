//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//public class Test8 {
//	/**
//	 * @param args
//	 * Stream FileReader
//	 */
//	public static void main(String[] args) {
//		int b = 0;
//		FileReader fr = null;
//		try{
//			fr = new FileReader("E:\\Workspaces\\guan\\src\\com\\test\\test.java");
//			while((b = fr.read()) != -1){
//				System.out.print((char)b);
//			}
//			fr.close();
//		} catch(FileNotFoundException e){
//			System.out.print("zhaobudaowenjian");
//			System.exit(-1);
//		} catch(IOException e1){
//			System.out.print("wenjianduqucuowu");
//			System.exit(-1);
//		}
//	}
//}
