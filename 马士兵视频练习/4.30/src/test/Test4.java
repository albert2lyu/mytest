//package test;
//import java.io.File;
//import java.io.IOException;
//public class Test4 {
//	/**
//	 * @param args
//	 * File����
//	 * ���File�����ڰ��У��������ļ�ʱ������Ұ����ϲ�Ŀ¼��������class�ļ����ϲ�Ŀ¼
//	 * �����������ִ��f.createNewFile();ʱ�������test���ϲ�Ŀ¼
//	 * ������Test4.class���ϲ�Ŀ¼��������Ҳ����test���ڵ�Ŀ¼
//	 */
//	public static void main(String[] args) {
//		String separator = File.separator;
//		String fileName = "mytxt.txt";
//		String directory = "mydir1" + separator + "mydir2";
//		File f = new File(directory,fileName);
//		if(f.exists()){
//			System.out.println("�ļ�����" + f.getAbsolutePath());
//			System.out.println("�ļ���С��" + f.length());
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
