//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//public class Test10 {
//	/**
//	 * @param args
//	 * Stream BufferedInputStream
//	 * ����ȡ���ݺ󷵻�int�ͣ�����(char)ǿ��ת�����ַ���
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
//			bi.mark(100);//�����������ǰ��ȡ����������ʾ���ʧЧǰ�����������ȡ���ֽ���
//			for(int i=0; i<=10 && (c=bi.read()) != -1; i++){
//				System.out.print((char)c + " ");
//			}
//			System.out.println();
//			bi.reset();//���¶�λ��mark()������λ�ĵط����Ժ��ȡ�Ӷ�λ����ʼ
//			for(int i=0; i<=10 && (c=bi.read()) != -1; i++){
//				System.out.print((char)c + " ");
//			}
//			bi.close();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//}