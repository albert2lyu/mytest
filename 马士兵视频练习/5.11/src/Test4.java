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
//		synchronized(this){//���ַ�����ִ�и÷���ʱ��������ǰ���󣬹ؼ���synchronized
//						//ȥ���ؼ���synchronized���������н��ΪT1 di 2 ge T2 di 2 ge
//						//��Ϊ�߳�t1ִ�е�sleep(1)˯��1���룬numΪ1��
//						//��ʱt2��ʼִ�У���sleep(1)ʱ˯�ߣ�numΪ2��t1�ٴ�ִ��
//						//����ʱ����ΪnumΪstatic������t1�������У����numΪ2
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {		
//			}
//			System.out.println(s+" di "+num+" ge");
//		}
//	}
//}