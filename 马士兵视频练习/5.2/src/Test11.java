//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//public class Test11 {
//	/**
//	 * @param args
//	 * Stream BufferedReader and BufferedWriter
//	 * Math.random()方法，返回0.0到1.0之间的随机double类型数
//	 */
//	public static void main(String[] args) {
//		try{
//			BufferedWriter bw = new BufferedWriter(
//					new FileWriter("E:\\Workspaces\\guan\\src\\com\\test\\test.txt"));
//			BufferedReader br = new BufferedReader(
//					new FileReader("E:\\Workspaces\\guan\\src\\com\\test\\test.txt"));
//			String s = null;
//			for(int i=0; i<=100; i++){
//				s = String.valueOf(Math.random());
//				bw.write(s);
//				bw.newLine();
//			}
//			bw.flush();
//			while((s=br.readLine()) != null){
//				System.out.println(s);
//			}
//			bw.close();
//			br.close();
//		} catch(IOException e){
//			e.printStackTrace();
//		}
//	}
//}
