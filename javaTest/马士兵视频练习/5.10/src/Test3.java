//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//public class Test3 {
//	/**
//	 * @param args
//	 * ObjectInputStream and ObjectOutputStream
//	 */
//	public static void main(String[] args) {
//		T t = new T();
//		t.f = 8;
//		try{
//		FileOutputStream fos = new FileOutputStream("E:/java/java.txt");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(t);
//		oos.flush();
//		oos.close();
//		
//		FileInputStream fis = new FileInputStream("E:/java/java.txt");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		T tR = (T)ois.readObject();
//		System.out.println(tR.i + " " + tR.j + " " + tR.f + " " + tR.k);
//		} catch(IOException e){
//			System.out.println(e.getMessage());
//		} catch(ClassNotFoundException cfe){
//			System.out.println(cfe.getMessage());
//		}
//	}
//}
//class T implements Serializable {//��Ǹö���������л�
//	int i = 10;
//	double j = 2.3;
//	int f = 7;
//	transient int k = 8;//transient�ؼ��֣���ʾ������������ļ��������ֵ��͸����
//						//�洢�����͵�Ĭ��ֵ������int���ͣ����ļ��д洢ΪĬ��ֵ0
//}