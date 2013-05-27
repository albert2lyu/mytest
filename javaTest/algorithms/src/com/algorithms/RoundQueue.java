package com.algorithms;

import java.util.Iterator;

public class RoundQueue<T> implements Iterable<T> {

	private T[] queue;
	private int head = 0;
	private int tail = 0;
	private int realSize = 0;
	
	@SuppressWarnings("unchecked")
	public RoundQueue(int size){
		queue = (T[])new Object[size <= 10 ? 10 : size];
	}
	
	public boolean isFull(){
		return realSize == queue.length;
	}
	
	public boolean isEmpty(){
		return realSize == 0;
	}
	
	public void addLast(T t){
		if(true == isFull()){
			//throw new fullexception
		}
		tail = (head + realSize) % queue.length;
		queue[tail] = t;
		realSize++;
	}
	
	public int realSize(){
		return realSize;
	}
	
	public T removeFirst(){
		if(true == isEmpty()){
			//throw new emptyexception
		}
		T t = queue[head];
		queue[head] = null;
		head = (head + 1) % queue.length;
		realSize--;
		return t;
	}
	
	public void clear(){
		while(false == isEmpty()){
			removeFirst();
		}
	}
	
	public T get(int index){
		if(index < 0 || index >= realSize){
			//throw new IndexOutOfBoundsException
		}
		return queue[index];
	}
	
	public int indexOf(T t){
		if(t != null){
			int i = 0;
//			for(T temp : this){
//				if(temp.equals(t)){
//					return i;
//				}
//				i++;
//			}
			while(i < realSize){
				if(t.equals(queue[i])){
					return i;
				}
				i++;
			}
		}
		return -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new RoundQueueIteator();
	}
	
	private class RoundQueueIteator implements Iterator<T> {

		private int index = head;
		private int size = realSize;
		
		@Override
		public boolean hasNext() {
			return size != 0;
		}

		@Override
		public T next() {
			T t = queue[index];
			index = (index + 1) % queue.length;
			size--;
			return t;
		}

		@Override
		public void remove() {
		}
	}
	
	public static void main(String[] args) {
		RoundQueue<Integer> queue=new RoundQueue<Integer>(12);
		
		/* 添加数据 */
		for(int i=0;!queue.isFull();i++){
			queue.addLast(i);
		}
		
		System.out.println(" 1 indexOf : "+queue.indexOf(1));
		System.out.println(" real size before : "+queue.realSize());
		System.out.println(" first element : "+queue.removeFirst());
		System.out.println(" real size after: "+queue.realSize());
		
		if(!queue.isFull())  queue.addLast(10);
		
		
		/*遍历结果*/
		for(Integer t : queue){
			System.out.print(t + "  ");
		}
		System.out.println();
		while(!queue.isEmpty()){
			System.out.print(queue.removeFirst() + "  ");
		}
	
	}

}
