//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//public class Test12 {
//	/**
//	 * @param args
//	 * Stream OutputStreamWriter
//	 */
//	public static void main(String[] args) {
//		try{
//			OutputStreamWriter osw = new OutputStreamWriter(
//					new FileOutputStream("E:\\Workspaces\\guan\\src\\com\\test\\test.txt"));
//			osw.write("adertbtubevjrbhrwby");
//			System.out.println(osw.getEncoding());
//			osw.close();
//			osw = new OutputStreamWriter(
//					new FileOutputStream("E:\\Workspaces\\guan\\src\\com\\test\\test.txt",true),
//						"ISO8859_1");
//			osw.write("adertbtubevjrbhrwby");
//			System.out.println(osw.getEncoding());
//			osw.close();
//		} catch(IOException e){
//			e.printStackTrace();
//		}
//	}
//}
