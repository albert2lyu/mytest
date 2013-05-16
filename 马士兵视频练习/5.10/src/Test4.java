//public class Test4 {
//	/**
//	 * @param args
//	 * 多线程，实现Runnable接口
//	 */
//	public static void main(String[] args) {
//		Runner r = new Runner();
//		Thread t = new Thread(r);//必须实例Thread类
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