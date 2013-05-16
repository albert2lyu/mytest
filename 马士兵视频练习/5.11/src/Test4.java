//public class Test4 implements Runnable{
//	/**
//	 * @param args
//	 * synchronized
//	 */
//	Timer tm = new Timer();
//	public static void main(String[] args) {
//		Test4 t = new Test4();
//		Thread t1 = new Thread(t);
//		Thread t2 = new Thread(t);
//		t1.setName("T1");
//		t2.setName("T2");
//		t1.start();
//		t2.start();		
//	}
//	public void run(){
//		tm.add(Thread.currentThread().getName());
//	}
//}
//
//class Timer{
//	public static int num = 0;
//	public /*synchronized*/ void add(String s){
//		num++;
//		synchronized(this){//两种方法，执行该方法时，锁定当前对象，关键字synchronized
//						//去掉关键字synchronized，程序运行结果为T1 di 2 ge T2 di 2 ge
//						//因为线程t1执行到sleep(1)睡眠1毫秒，num为1。
//						//此时t2开始执行，到sleep(1)时睡眠，num为2，t1再次执行
//						//而此时，因为num为static，所以t1继续运行，输出num为2
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {		
//			}
//			System.out.println(s+" di "+num+" ge");
//		}
//	}
//}