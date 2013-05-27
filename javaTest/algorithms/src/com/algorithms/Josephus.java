package com.algorithms;

import com.std.StdOut;

public class Josephus {
    public static void main(String[] args) {
//        int M = Integer.parseInt(args[0]);
//        int N = Integer.parseInt(args[1]);
    	
    	int M = 3;
    	int N = 10;
    	
        // initialize the queue
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        for (int i = 0; i < N; i++)
            q.enqueue(i);

        while (!q.isEmpty()) {
            for (int i = 0; i < M-1; i++)
                q.enqueue(q.dequeue());
            StdOut.print(q.dequeue() + " ");
        } 
        StdOut.println();
    }
}
