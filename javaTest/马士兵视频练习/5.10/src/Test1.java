//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//public class Test1 {
//	/**
//	 * @param args
//	 * PrintStream
//	 * System.setOut(ps);��ԭ��System.out���������ת��ps,Ҳ����java.txt�ļ�
//	 */
//	public static void main(String[] args) {
//		PrintStream ps = null;
//		try{
//			FileOutputStream fos = new FileOutputStream("E:/java/java.txt");
//			ps = new PrintStream(fos);
//		} catch(IOException e) {
//			System.out.println(e.getMessage());
//		}
//		if(ps != null){
//			System.setOut(ps);
//		}
//		int ln = 0;
//		for(char c=0; c<100; c++){
//			System.out.print(c + " ");
//			if(ln++ >= 100){
//				System.out.println();
//				ln = 0;
//			}
//		}
//	}
//}
