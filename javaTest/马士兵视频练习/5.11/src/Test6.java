//public class Test6 implements Runnable {
//	/**
//	 * @param args
//	 * ���������е�һ����������Ӱ��Ըö���������û�м�synchronized�ؼ��ַ����ĵ���
//	 */
//	int a = 100;
//	public static void main(String[] args) throws InterruptedException {
//		Test6 t6 = new Test6();
//		Thread t = new Thread(t6);
//		t.start();
//		Thread.sleep(1000);
//		t6.m2();
//	}
//	public void run(){
//		try {
//			m1();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	public synchronized void m1() throws InterruptedException{
//		a = 1000;
//		Thread.sleep(5000);
//		System.out.println("a = "+a);
//	}
//	public void m2(){
//		System.out.println(a);
//	}
//}
