//public class Test1 {
//	/**
//	 * @param args
//	 * Thread join()方法
//	 */
//	public static void main(String[] args) {
//		MyThread mt = new MyThread("abc");
//		mt.start();
//		try {
//			mt.join();//等待mt线程终止
//		} catch (InterruptedException e) {
//			for(int i=0; i<=10; i++){
//				System.out.println("I am main Thread!");
//			}
//		}
//	}
//}
//
//class MyThread extends Thread {
//	MyThread(String s){
//		super(s);
//	}
//	public void run(){
//		for(int i=0; i<=100; i++){
//			System.out.println("I am "+getName());
//			try {
//				sleep(100);
//			} catch (InterruptedException e) {
//				return;
//			}
//		}
//	}
//}