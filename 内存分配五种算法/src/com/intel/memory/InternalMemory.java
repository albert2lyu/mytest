package com.intel.memory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class InternalMemory {
	private Page[] pageLump = new Page[15];
	private List<Job> jobList = new ArrayList<Job>();
	private static int timeTurn = 0;
	private static int permuteNum = 0;
	private final int LUMPNUM = 15;
	private boolean isExecute = true;
	private static int lackPageNum = 15;
	private static int arriveLumpTime = 0;
	private int choose;
	
	public void allocateJob(int n1, int n2, int n3){
		if(jobList.size() != 0){
			jobList.clear();
			timeTurn = 0;
		}
		if(MyFrame.l1.getItemCount() != 0){
			this.setFilePageBunch(n1, n3);
		} else {
			this.allocatePageBunch(n1,n2,n3);
		}
		MyFrame.panel3.showColor(jobList);
	}//分配作业
	public int hasEmptySite(Job j){
		for(int k=j.getLumpStart(); k<=j.getLumpEnd(); k++){
			if(pageLump[k] == null){
				return k;
			}
		}
		return -1;
	}//返回物理块pageLump中为空的位置
	public int jobLumpNum(int n1){
		return LUMPNUM/n1;
	}//计算每个作业该分配的物理块数
	public int memLumpNum(int n1){
		return LUMPNUM%n1;
	}//计算分配给OS的空闲物理块
	
	public int permutePageSiteLFU(Job j){
		int[] arrk = new int[15];
		int returnNum = 0;
		int n = 0;
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				returnNum = i;
				break;
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null && pageLump[i].getShiftRegisterCallNum() <= pageLump[returnNum].getShiftRegisterCallNum()){
				returnNum = i;		
			}
		}//第一次循环找出页面移位寄存器中，含1最少的页面的位置
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				if(pageLump[i].getShiftRegisterCallNum() == pageLump[returnNum].getShiftRegisterCallNum()){
					arrk[n] = i;
					n++;	
				}
			}
		}//第二次循环，找出和移位寄存器含1最少页面相同的页面位置
		if(permuteNum < n-1){
			permuteNum++;
		} else {
			permuteNum = j.getLumpStart();
		}//在有移位寄存器中含1数量相同的页面情况下，通过变量permuteNum选择要置换的页面的位置，防止被置换页面总在同一位置
		returnNum = arrk[permuteNum];
		return returnNum;
	}//返回需要被置换的页面，在pageLump中的位置
	
	public int permutePageSiteOPT(Job j){
		int k = j.getNowPageSite();
		int returnNum = 0;
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){//循环判断后面pageBunch[]中还会出现该页面的次数
			if(pageLump[i] != null){
				while(k < j.getPageBunch().size()){
					if(pageLump[i].getBelongJobId() == j.getJobId() && 
							pageLump[i].getPageId() == j.getPageBunch().get(k).getPageId()){
						pageLump[i].setSurplusCallNum(1);//使用此方法后，应该调用setSurplusCallNum(0)
					}
					k++;
				}
				k = j.getNowPageSite();
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){//初始化最小值
			if(pageLump[i] != null){
				returnNum = i;
				break;
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null && pageLump[i].getBelongJobId() == j.getJobId()){
				if(pageLump[i].getSurplusCallNum() < pageLump[returnNum].getSurplusCallNum()){
					returnNum = i;
				}
				
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				pageLump[i].setSurplusCallNum(0);//使surplusCallNum清零
			}
		}
		return returnNum;
	}	
	public int permutePageSiteFIFO(Job j){
		int returnNum = 0;
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				returnNum = i;
				break;
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null && pageLump[i].getArriveLumpTime() <= pageLump[returnNum].getArriveLumpTime()){
				returnNum = i;
			}
		}
		return returnNum;
	}
	
	public int permutePageSiteLRU(Job j){
		int returnNum = 0;
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				returnNum = i;
				break;
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null && pageLump[i].getLastTime() >= pageLump[returnNum].getLastTime()){
				returnNum = i;
			}
		}
		return returnNum;
	}
	public int permutePageSiteClock(Job j){
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				if(false == pageLump[i].isBeCall()){
					return i;
				} else {
					pageLump[i].setBeCall(false);
				}
			}
		}
		return j.getLumpStart();
	}
	
	public int hasSamePageSite(Job j){
		int g = -1;
		for(int k=j.getLumpStart(); k<=j.getLumpEnd(); k++){
			if(pageLump[k] != null){
				if(pageLump[k].getBelongJobId() == j.getJobId() &&
						pageLump[k].getPageId() == j.getPageBunch().get(j.getNowPageSite()).getPageId()){
					g = k;
					return g;
				}
			}
		}
		return g;
	}//返回在pageLump中，和nowJob此时要执行的页面，相同的页面的位置
	public void allocateLump(int n1, Job nowJob){//对物理块的管理，页面的置换
		while(false == isExecute){
			
		}
		int gNPS = nowJob.getNowPageSite();
		if(gNPS != -1){//确保正在执行job中，还有未执行完的页面
			String s = null;
			int k = hasSamePageSite(nowJob);//判断pageLump中，是否已经存在该作业中相同的页面
			if(k != -1){//存在该作业中相同页面，直接将该页面移位寄存器首位置1
				pageLump[k].setShiftRegister(true);
				pageLump[k].setArriveLumpTime(arriveLumpTime);
				pageLump[k].setLastTime(0);
				pageLump[k].setBeCall(true);
				for(int i=0; i<pageLump.length; i++){
					if(pageLump[i] != null && i != k){
						pageLump[i].setLastTime(1);
					}
				}
				s = "作业"+nowJob.getJobId()+"在"+(timeTurn-1)+"-"+timeTurn+"秒内执行，页面"
					+pageLump[k].getPageId()+"在物理块"+k+"内有相同页面，直接进入该物理块";
				MyFrame.l2.add(s);
				MyFrame.panel3.launchPanel(k, pageLump[k]);
				arriveLumpTime++;
				nowJob.setNowPageSite();
			} else {//否则判断分配给该作业的pageLump中是否还有空闲的物理块
				k = this.hasEmptySite(nowJob);
				if(k != -1){//有空闲，直接存入页面
					pageLump[k] = nowJob.getPageBunch().get(gNPS);
					pageLump[k].setShiftRegister(true);
					pageLump[k].setArriveLumpTime(arriveLumpTime);
					pageLump[k].setLastTime(0);
					pageLump[k].setBeCall(true);
					for(int i=0; i<pageLump.length; i++){
						if(pageLump[i] != null && i != k){
							pageLump[i].setLastTime(1);
						}
					}
					s = "作业"+nowJob.getJobId()+"在"+(timeTurn-1)+"-"+timeTurn+"秒内执行，" +
							"该作业的物理块"+k+"仍空闲，页面"+pageLump[k].getPageId()+"直接进入该物理块";
					MyFrame.l2.add(s);
					MyFrame.panel3.launchPanel(k, pageLump[k]);
					arriveLumpTime++;
					nowJob.setNowPageSite();
				} else {//没有空闲的，再判断分配给系统的物理块中是否由空闲的
					if(this.choose == 1){
						k = permutePageSiteOPT(nowJob);
					} else if(this.choose == 2){
						k = permutePageSiteFIFO(nowJob);
					} else if(this.choose == 3){
						k = permutePageSiteLRU(nowJob);
					} else if(this.choose == 4){
						k = permutePageSiteClock(nowJob);
					} else if(this.choose == 5){
						k = permutePageSiteLFU(nowJob);
					}
					pageLump[k] = nowJob.getPageBunch().get(gNPS);
					pageLump[k].setShiftRegister(true);
					pageLump[k].setArriveLumpTime(arriveLumpTime);
					pageLump[k].setLastTime(0);
					pageLump[k].setBeCall(true);
					for(int i=0; i<pageLump.length; i++){
						if(pageLump[i] != null && i != k){
							pageLump[i].setLastTime(1);
						}
					}
					s = "作业"+nowJob.getJobId()+"在"+(timeTurn-1)+"-"+timeTurn+"秒内执行，页面" +
						pageLump[k].getPageId()+"置换物理块"+k+"中原有页面";
					MyFrame.l2.add(s);
					MyFrame.panel3.launchPanel(k, pageLump[k]);
					arriveLumpTime++;
					nowJob.setNowPageSite();
				}
			}
			for(int i=0; i<pageLump.length; i++){
				if(i != k && pageLump[i] != null){
					pageLump[i].setShiftRegister(false);
				}
			}//每执行一个页面，就使页面的移位寄存器右移一位
		}
	}//给下面要执行的nowJob中的页面，分配pageLump
	public void manageJob(){//处理机调度
		this.sortJobList();
		List<Job> jobLink = new ArrayList<Job>();
		jobLink.addAll(jobList);
		int i = 0;//用来判断jobLink是否为空
		int g = 0;//用来判断正在执行的作业，一次要执行多少页面
		int k = 1;//当nowJob执行一个时间片，且没有执行完毕时，用来判断jobLink队列中，剩余Job的到达时间与时间片的大小，且换位
		while(i != -1){
//			while(true == isExecute) {
				Job nowJob = jobLink.get(0);
				if(!jobLink.isEmpty()){
					while(timeTurn < nowJob.getArrivedTime()){//如果现在时间小于此刻执行的作业的到达时间，等待现在时间和到达时间相同				
						timeTurn++;
					}				
					nowJob.setCpuTime();//执行的作业占用CPU的时间加一
					timeTurn++;
					nowJob.setFinishTime(timeTurn);//作业的完成时间，等于时间片此刻的时间
					while(g < nowJob.getExecutePageNum()){//作业执行一次，需要执行的页面数量
						this.allocateLump(jobList.size(), nowJob);//执行物理块调度方法，判断该作业此刻的页面进入物理块的情况
						g++;
					}
					g = 0;
					isExecute = true;//重置g和isExecute，方便一下次循环时使用
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(nowJob.getCpuTime() >= nowJob.getRequiredTime()){//如果作业占用CPU的时间等于完成所需要的时间，那么它就执行完毕
						nowJob.setState(true);//将作业的状态设置为true，表示已完成
						jobLink.remove(nowJob);//移除已完成的作业
					} else {
						if(jobLink.size() != 1){//如果该作业未完成，判断后面作业的到达时间是否小于等于现在的时间
							while(jobLink.size() > k
								&& timeTurn >= jobLink.get(k).getArrivedTime()){//如果是，那么将该作业与后面所有符合此情况的作业换位
								Job temp = jobLink.get(k-1);
								jobLink.set(k-1,jobLink.get(k));
								jobLink.set(k,temp);//将该作业加到等待执行作业序列的末尾
								k++;
							}
							k=1;//重置k，以便下一次循环使用
						}				
						
					}
				}
				if(jobLink.isEmpty()){//如果作业序列为空，那么退出循环
					isExecute = false;
					i = -1;
				}
//			}
		}
		this.lackPageProbability(jobList.size(), jobList.get(0).getPageBunch().size());
	}
	public void sortJobList(){//按照到达时间排序
		for(int i=0; i<jobList.size()-1; i++){
			for(int j=i+1; j<jobList.size(); j++){
				if(jobList.get(i).getArrivedTime() 
						> jobList.get(j).getArrivedTime()){
					Job tempJob = jobList.get(i);
					jobList.set(i, jobList.get(j));
					jobList.set(j, tempJob);
				}
			}
		}
	}
	public void allocatePageBunch(int n1, int n2, int n3){
		int allYesOrNo = JOptionPane.showConfirmDialog(
				null, "是否自动生成全部作业的页面串", "s", JOptionPane.YES_NO_OPTION);
		int surplus = 0;
		int last = 0;
		if(allYesOrNo == 0){//是的话，全部自动生成
			if(LUMPNUM%n1 > 0){
				surplus = LUMPNUM%n1;
			}
			for(int i=1; i<=n1; i++){
				Job nowJob = new Job(i);
				if(i == 1){
					nowJob.setLumpStart(0);
					nowJob.setLumpEnd(LUMPNUM/n1-1);
				} else {
					nowJob.setLumpStart(last+1);
					nowJob.setLumpEnd(last+LUMPNUM/n1);
				}
				if(surplus != 0){
					nowJob.setLumpEnd(nowJob.getLumpEnd()+1);
					last = nowJob.getLumpEnd();
					surplus--;
				} else {
					last = nowJob.getLumpEnd();
				}
				for(int j=0; j<n3; j++){
					int pageId = j%n2;//pageId是该页面在页面串中的位置与Job大小的余数
					Page p = new Page(pageId,i);
					nowJob.setPageBunch(p);//在job的页面串中加入该页面
				}
				Collections.shuffle(nowJob.getPageBunch());//讲页面串乱序排列
				this.showPageBunch(i,n3,nowJob);
				jobList.add(nowJob);				
			}
		} else {//一个页面一个页面生成，每个页面又可以自动生成和手动输入
			if(LUMPNUM%n1 > 0){
				surplus = LUMPNUM%n1;
			}
			for(int i=1; i<=n1; i++){
				Job nowJob = new Job(i);
				if(i == 1){
					nowJob.setLumpStart(0);
					nowJob.setLumpEnd(LUMPNUM/n1-1);
				} else {
					nowJob.setLumpStart(last+1);
					nowJob.setLumpEnd(last+LUMPNUM/n1);
				}
				if(surplus != 0){
					nowJob.setLumpEnd(nowJob.getLumpEnd()+1);
					last = nowJob.getLumpEnd();
					surplus--;
				} else {
					last = nowJob.getLumpEnd();
				}
				int yesORno = JOptionPane.showConfirmDialog(
						null, "这是第"+(i)+"个作业，是否自动生成页面串",
							"s", JOptionPane.YES_NO_OPTION);
				if(yesORno==0){//是的话自动生成该页面
					for(int j=0; j<n3; j++){
						int pageId = j%n2;
						Page p = new Page(pageId,i);
						nowJob.setPageBunch(p);
					}
					Collections.shuffle(nowJob.getPageBunch());
					this.showPageBunch(i,n3,nowJob);
				} else if(yesORno==1){//否的话，手动输入页面串
					try{
						String str = JOptionPane.showInputDialog(
								"页面范围0-"+(n2-1)+"," +
										"请输入页面串,长度等于"+n3+",用英文状态下的‘,’隔开");
						String[] arrayStr = str.split(",");
						if(arrayStr.length == n3){
							for(int j=0; j<arrayStr.length; j++){
								int pageId = Integer.parseInt(arrayStr[j]);
								Page p = new Page(pageId,i);
								nowJob.setPageBunch(p);
							}
							Collections.shuffle(nowJob.getPageBunch());
							this.showPageBunch(i,n3,nowJob);
						} else {
							JOptionPane.showMessageDialog(null, "输入有误，请返回从头重新设置！");
							break;
						}
					} catch(NullPointerException e){
						JOptionPane.showMessageDialog(null, "输入有误，请返回从头重新设置！");
						break;
					}
				}
				jobList.add(nowJob);
			}
		}
	}
	public void showPageBunch(int i, int n3, Job nowJob){//显示页面串
		String s = "";
		for(int j=0; j<n3; j++){
			s = s + nowJob.getPageBunch().get(j) + ",";
		}
		
		MyFrame.l1.add("作业" + i + "的页面串：" + s + "\n");
	}
	public void removeAll(){//移除pageLump、jobList中的数据，将时间片timeTurn清零
		for(int i=0; i<pageLump.length; i++){
			pageLump[i] = null;
		}
		this.jobList.clear();
		timeTurn = 0;
	}
	public void setIsExecute(boolean bool){
		this.isExecute = bool;
	}
	public void setFilePageBunch(int n1,int n3){
		String line = null;
		String lineSub = null;
		int surplus = 0;
		int last = 0;
		Pattern p = Pattern.compile("[\\d(1,2),]+$");
		Matcher m = null;
		if(LUMPNUM%n1 > 0){
			surplus = LUMPNUM%n1;
		}
		for(int i=1; i<=n1; i++){
			Job nowJob = new Job(i);
			if(i == 1){
				nowJob.setLumpStart(0);
				nowJob.setLumpEnd(LUMPNUM/n1-1);
			} else {
				nowJob.setLumpStart(last+1);
				nowJob.setLumpEnd(last+LUMPNUM/n1);
			}
			if(surplus != 0){
				nowJob.setLumpEnd(nowJob.getLumpEnd()+1);
				last = nowJob.getLumpEnd();
				surplus--;
			} else {
				last = nowJob.getLumpEnd();
			}
			line = MyFrame.l1.getItem(i-1);
			m = p.matcher(line);
			if(m.find()){
				lineSub = m.group();
				String[] arrayStr = lineSub.split(",");
				if(arrayStr.length == n3){
					for(int j=0; j<arrayStr.length; j++){
						int pageId = Integer.parseInt(arrayStr[j]);
						Page page = new Page(pageId,i);
						nowJob.setPageBunch(page);
					}
				} 
			}
			jobList.add(nowJob);
		}
	}
	public void lackPageProbability(int n1,int n3){
		double dn1 = 0;
		double dn3 = 0;
		double d = 0;
		dn1 = Double.parseDouble(n1+"");
		dn3 = Double.parseDouble(n3+"");
		d = lackPageNum/(dn1*dn3);
		DecimalFormat dcmFmt = new DecimalFormat("0.00%");
		MyFrame.lb8.setText("      "+lackPageNum);
		MyFrame.lb10.setText("   "+dcmFmt.format(d));
		lackPageNum = 0;
	}
	public void setChoose(int choose) {
		this.choose = choose;
	}
	
}
