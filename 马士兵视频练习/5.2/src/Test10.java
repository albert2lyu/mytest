//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//public class Test10 {
//	/**
//	 * @param args
//	 * Stream BufferedInputStream
//	 * 流读取数据后返回int型，可以(char)强制转换成字符型
//	 */
//	public static void main(String[] args) {
//		try{
//			FileInputStream fi = 
//				new FileInputStream("E:\\Workspaces\\guan\\src\\com\\test\\test.java");
//			BufferedInputStream bi = 
//				new BufferedInputStream(fi);
//			int c = 0;
//			System.out.println((char)bi.read());
//			System.out.println((char)bi.read());
//			bi.mark(100);//标记输入流当前读取处，参数表示标记失效前，允许继续读取的字节数
//			for(int i=0; i<=10 && (c=bi.read()) != -1; i++){
//				System.out.print((char)c + " ");
//			}
//			System.out.println();
//			bi.reset();//重新定位到mark()方法定位的地方，以后读取从定位处开始
//			for(int i=0; i<=10 && (c=bi.read()) != -1; i++){
//				System.out.print((char)c + " ");
//			}
//			bi.close();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//}