//public class Test4 {
//	/**
//	 * @param args
//	 * ���̣߳�ʵ��Runnable�ӿ�
//	 */
//	public static void main(String[] args) {
//		Runner r = new Runner();
//		Thread t = new Thread(r);//����ʵ��Thread��
//		t.start();
//		for(int i=0; i<=1000; i++){
//			System.out.println("Main:"+i);
//		}
//	}
//}
//class Runner implements Runnable {
//	public void run(){
//		for(int i=0; i<=1000; i++){
//			System.out.println("Runner:"+i);
//		}
//	}
//}