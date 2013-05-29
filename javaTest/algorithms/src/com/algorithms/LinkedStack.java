package com.algorithms;

import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T> {

	private class Node{
		T self;
		Node next;
		
		Node(){
			
		}
		
		//通过递归或不递归构造函数来复制栈中节点
		Node(Node x, boolean isRecursion){
			if(true == isRecursion){
				self = x.self;
				if(x.next != null){
					next = new Node(x.next, isRecursion);
				}
			} else {
				self = x.self;
				next = x.next;
			}
		}
	}
	
	private Node first;
	private int N = 0;
	
	public LinkedStack() {
		
	}
	//通过递归或不递归构造函数来复制栈
	public LinkedStack(LinkedStack<T> stack, boolean isRecursion){
		if(true == isRecursion){
			first = new Node(stack.first, isRecursion);
		} else {
			if(stack.first != null){
				first = new Node(stack.first, isRecursion);
				for(Node node = first; node.next != null; node = node.next){
					node.next = new Node(node.next, isRecursion);
				}
			}
		}
	}
	
	public boolean isEmpty(){
		return N == 0;//或first == null
	}
	
	public int size(){
		return N;
	}
	
	public void push(T t){
		Node oldFirst = first;
		first = new Node();
		first.self = t;
		first.next = oldFirst;
		N++;
	}
	
	public T pop(){
		T t = first.self;
		first = first.next;
		N--;
		return t;
	}
	
	public T peek(){
		return first.self;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedStackIterator();
	}
	
	private class LinkedStackIterator implements Iterator<T> {

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
