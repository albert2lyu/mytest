//public class Test2 {
//	/**
//	 * @param args
//	 * Thread yield()����
//	 */
//	public static void main(String[] args) {
//		MyThread mt1 = new MyThread("MT1");
//		MyThread mt2 = new MyThread("----MT2");
//		mt1.start();
//		mt2.start();
//	}
//}
//
//class MyThread extends Thread {
//	MyThread(String s){
//		super(s);
//	}
//	public void run(){
//		for(int i=0; i<=1000; i++){
//			System.out.println(getName()+" "+i);
//			if(i % 10 == 0){
//				yield();//��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������߳�
//			}
//		}
//	}
//}