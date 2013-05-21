//public class ProducerConsumer {
//	/**
//	 * @param args
//	 * 生产者消费者问题
//	 */
//	public static void main(String[] args) {
//		SyncStack ss = new SyncStack();
//		Producer p = new Producer(ss);
//		Consumer c = new Consumer(ss);
//		new Thread(p).start();//可以有多个生产者
//		//new Thread(p).start();
//		new Thread(c).start();//也可以有多个消费者
//		//new Thread(c).start();
//			//但要注意生产ManTou和消费ManTou的个数应该相同，不然会产生死锁
//	}
//}
//class ManTou{
//	private int id;
//	ManTou(int id){
//		this.id = id;
//	}
//	public String toString(){
//		return "ManTou:"+id;
//	}
//}
//class SyncStack{
//	int index = 0;
//	ManTou[] arrMt = new ManTou[6];
//	public synchronized void push(ManTou mt){
//		while(index == arrMt.length){
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		this.notify();
//		arrMt[index] = mt;
//		index ++;
//	}
//	public synchronized ManTou pop(){
//		while(index == 0){
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		this.notify();
//		index --;
//		return arrMt[index];
//	}
//}
//class Producer implements Runnable{
//	SyncStack ss = null;
//	Producer(SyncStack s){
//		this.ss = s;
//	}
//	public void run(){
//		for(int i=1; i<=20; i++){
//			ManTou mt = new ManTou(i);
//			ss.push(mt);
//			System.out.println("shengchangle "+mt);
//			try {
//				Thread.sleep((int)(Math.random() * 1000));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
//class Consumer implements Runnable {
//	SyncStack ss = null;
//	Consumer(SyncStack s){
//		this.ss = s;
//	}
//	public void run(){
//		for(int i=1; i<=20; i++){
//			ManTou mt = ss.pop();
//			System.out.println("xiaofeile " + mt);
//			try {
//				Thread.sleep((int)(Math.random() * 1000));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}