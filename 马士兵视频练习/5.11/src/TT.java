//public class TT implements Runnable {
//	int b = 100;
//	public synchronized void m1() throws Exception{
//		b = 1000;
//		Thread.sleep(5000);
//		System.out.println("b = " + b);
//	}
//	public synchronized void m2() throws Exception {
//		//当对象中两个方法同时修改同一个值时，必须全部加锁:synchronized
//		//将m2()的synchronized去掉后，输出结果为2000	b = 2000
//		Thread.sleep(2500);
//		b = 2000;
//	}
//	public void run() {
//		try {
//			m1();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) throws Exception {
//		TT tt = new TT();
//		Thread t = new Thread(tt);
//		t.start();	
//		tt.m2();
//		System.out.println(tt.b);
//	}
//}