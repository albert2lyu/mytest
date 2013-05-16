package com.intel.memory;

public class ExecuteThread implements Runnable{
	private InternalMemory interMem = null;
	public ExecuteThread(InternalMemory im){
		this.interMem = im;
	}
	public void run(){	
		interMem.manageJob();
	}
}
