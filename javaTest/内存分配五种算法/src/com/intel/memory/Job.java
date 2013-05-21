package com.intel.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Job {
	
	private List<Page> pageBunch = new ArrayList<Page>();	
	private int jobId;
	private int arrivedTime;
	private int requiredTime;
	private int cpuTime = 0;
	private int finishTime = arrivedTime;
	private boolean state = false;
	private int nowPageSite = 0; 
	private int lumpStart = 0;
	private int lumpEnd = 0;
	
	public Job(int i) {
		Random ran = new Random();
		this.jobId = i;
		this.arrivedTime = ran.nextInt(8);				
		this.requiredTime = ran.nextInt(10);
		if(this.requiredTime == 0){
			this.requiredTime += 5;
		}
	}

	public int getArrivedTime() {
		return arrivedTime;
	}
	public int getRequiredTime() {
		return requiredTime;
	}
	public int getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(int i) {
		this.finishTime = i;
	}
	public boolean isState() {
		return state;
	}
	public boolean getState(){
		return this.state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getJobId() {
		return jobId;
	}
	public int getCpuTime() {
		return cpuTime;
	}
	public void setCpuTime() {
		this.cpuTime++;
	}
	public List<Page> getPageBunch() {
		return pageBunch;
	}
	public void setPageBunch(Page p){
		this.pageBunch.add(p);
	}
	public int getNowPageSite() {
		if(nowPageSite < pageBunch.size()){
			return nowPageSite;
		} else {
			this.nowPageSite = 0;
			return -1;
		}	
	}

	public void setNowPageSite() {
		this.nowPageSite++;
	}
	
	public int getLumpStart() {
		return lumpStart;
	}

	public void setLumpStart(int lumpStart) {
		this.lumpStart = lumpStart;
	}

	public int getLumpEnd() {
		return lumpEnd;
	}

	public void setLumpEnd(int lumpEnd) {
		this.lumpEnd = lumpEnd;
	}

	public int getExecutePageNum() {
//		executeCount++;
		if(cpuTime >= requiredTime){
//			executeCount = 0;
			return pageBunch.size()/requiredTime+pageBunch.size()%requiredTime;
		} else {
			return pageBunch.size()/requiredTime;
		}
	}//返回作业在一次执行期间，需要执行的页面数，为总页面数除以所需时间，如果余数大于0，则执行一次执行页面数
}
