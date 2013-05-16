package com.intel.memory;

public class Page {
	private int pageId;
	private int belongJobId;
	private int surplusCallNum = 0;
	private int arriveLumpTime;
	private int lastTime = 0;
	private boolean beCall;
	private int[] shiftRegister = {0,0,0,0,0,0,0,0};
	public Page(int i, int j){
		pageId = i;
		belongJobId = j;
	}
	public int getPageId(){
		return pageId;
	}
	public String toString(){
		return pageId+"";
	}
	public int getBelongJobId() {
		return belongJobId;
	}
	public int getShiftRegisterCallNum() {
		int j = 0;
		for(int i=0; i<shiftRegister.length; i++){
			if(shiftRegister[i] == 1){
				j++;
			}
		}
		return j;
	}//返回移位寄存器中1的个数
	public void setShiftRegister(boolean isCall) {
		if(true == isCall){
			this.shiftRegister[0] = 1;
		} else {
			for(int i=shiftRegister.length-1; i>0; i--){
				this.shiftRegister[i] = this.shiftRegister[i-1];
			}
			this.shiftRegister[0] = 0;
		}
	}//设置移位寄存器，true时首位置1，false时右移
	public int getSurplusCallNum() {
		return this.surplusCallNum;
	}
	public void setSurplusCallNum(int i) {
		if(i == 0){
			this.surplusCallNum = 0;
		} else {
			this.surplusCallNum++;
		}
	}
	public int getArriveLumpTime() {
		return arriveLumpTime;
	}
	public void setArriveLumpTime(int arriveTime) {
		this.arriveLumpTime = arriveTime;
	}
	public int getLastTime() {
		return lastTime;
	}
	public void setLastTime(int i) {
		if(i == 0){
			this.lastTime = 0;
		} else {
			this.lastTime++;
		}	
	}
	public boolean isBeCall() {
		return beCall;
	}
	public void setBeCall(boolean beCall) {
		this.beCall = beCall;
	}
	
}
