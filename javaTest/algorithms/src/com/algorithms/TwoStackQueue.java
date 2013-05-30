package com.algorithms;

import java.util.Iterator;

public class TwoStackQueue<T> implements Iterable<T> {
	private ArrayStack<T> stack1 = new ArrayStack<T>();
	private ArrayStack<T> stack2 = new ArrayStack<T>();
	
	private int N = 0;
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public void enqueue(T t){
		stack1.push(t);
		N++;
	}
	
	private void reversal(){
		while(false == stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
	}
	
	public T dequeue(){
		if(N == 0){
			//throw new emptyexception
			return null;
		}
		if(true == stack2.isEmpty()){
			reversal();
		} 
		N--;
		return stack2.pop();
	}


	@Override
	public Iterator<T> iterator() {
		return new TwoStackQueueIterator();
	}
	
	private class TwoStackQueueIterator implements Iterator<T>{

		private int count = N;
		private Iterator<T> it = null;
		private ArrayStack<T> temps = null;
		
		public TwoStackQueueIterator(){
			if(true == stack2.isEmpty()){
				
			}
		}
		
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public T next() {
			T t;
			if(it == null){
				it = stack2.iterator();
			}
			if(false == it.hasNext()){
				if(temps == null){
					temps = new ArrayStack<T>();
					for(T tt : stack1){
						temps.push(tt);
					}
					it = temps.iterator();
				}
			}
			t = it.hasNext() ? it.next() : null;
			count--;
			return t;
		}

		@Override
		public void remove() {
		}
	}
	
	public static void main(String[] args) {
		TwoStackQueue<Integer> tsq = new TwoStackQueue<Integer>();
		for(int i=0; i<10; i++){
			tsq.enqueue(i);
		}
		for(Integer ii : tsq){
			System.out.print(ii + "  ");
		}
		System.out.println();
		tsq.dequeue();
		tsq.dequeue();
		tsq.enqueue(10);
		tsq.dequeue();
		tsq.enqueue(11);
		for(Integer iii : tsq){
			System.out.print(iii + "  ");
		}
	}
}
