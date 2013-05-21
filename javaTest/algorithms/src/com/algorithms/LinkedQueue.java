package com.algorithms;

import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T> {

	private class Node {
		T self;
		Node next;
	}
	
	private Node first;
	private Node last;
	private int N = 0;
	
	public boolean isEmpty(){
		return N == 0;//或first == null
	}
	
	public int size(){
		return N;
	}
	
	public void enqueue(T t){
		Node oldLast = last;
		last = new Node();
		last.self = t;
		last.next = null;
		if(true == isEmpty()){
			first = last;
		} else {
			oldLast.next = last;
		}
		N++;
	}
	
	public T dequeue(){
		if(true == isEmpty()){
			return null;
		}
		T t = first.self;
		first = first.next;//如果删除的是最后一个元素，则first.next为null
		N--;
		if(true == isEmpty()){
			last = null;//删除最后一个元素后，保证元素被垃圾回收
		}
		return t;
	}
	
	public T delLast(){
		if(true == isEmpty()) return null;
		T t = last.self;
		N--;
		if(first == last){
			first = null;
			last = null;
			return t;
		}
		Node lastButOne = first;
		for(int i=1; i<N-1; i++){
			if(lastButOne.next == last){//判断引用
				lastButOne.next = null;
				last = null;
				last = lastButOne;
				break;
			}
			lastButOne = lastButOne.next;
		}
		return t;
	}
	
	public T delete(int k){
		if(k >= N) return null;
		if(k == 0) return dequeue();
		if(k == N-1) return delLast();
		Node before = first;
		Node current = before.next;
		for(int i=1; i<N-2; i++){
			if(i == k){
				before = current.next;
				current.next = null;
				break;
			}
			before = current;
			current = current.next;
		}
		return current.self;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueIterator();
	}
	private class LinkedQueueIterator implements Iterator<T> {

		private Node current = first;
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			T t = current.self;
			current = current.next;
			return t;
		}

		@Override
		public void remove() {
		}
	}
}
