//public class TestDeadLock implements Runnable{
//	/**
//	 * @param args
//	 * 验证死锁的程序，产生死锁，无法运行完全
//	 */
//	public int flag = 0;
//	static String s1 = "a";
//	static String s2 = "";
//	public static void main(String[] args) {
//		TestDeadLock tdl1 = new TestDeadLock();
//		TestDeadLock tdl2 = new TestDeadLock();
//		tdl1.flag = 1;
//		tdl2.flag = 0;
//		Thread t1 = new Thread(tdl1);
//		Thread t2 = new Thread(tdl2);
//		t1.start();
//		t2.start();
//	}	
//	public void run(){
//		System.out.println("flag="+flag);
//		if(flag == 1){
//			synchronized(s1){
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				synchronized(s2){
//					System.out.println(true);
//				}
//			}	
//		}
//		if(flag == 0){
//			synchronized(s2){
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				synchronized(s1){
//					System.out.println(false);
//				}
//			}	
//		}
//	}
//}
