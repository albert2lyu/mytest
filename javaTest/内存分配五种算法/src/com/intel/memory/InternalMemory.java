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
	}//������ҵ
	public int hasEmptySite(Job j){
		for(int k=j.getLumpStart(); k<=j.getLumpEnd(); k++){
			if(pageLump[k] == null){
				return k;
			}
		}
		return -1;
	}//���������pageLump��Ϊ�յ�λ��
	public int jobLumpNum(int n1){
		return LUMPNUM/n1;
	}//����ÿ����ҵ�÷�����������
	public int memLumpNum(int n1){
		return LUMPNUM%n1;
	}//��������OS�Ŀ��������
	
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
		}//��һ��ѭ���ҳ�ҳ����λ�Ĵ����У���1���ٵ�ҳ���λ��
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){
			if(pageLump[i] != null){
				if(pageLump[i].getShiftRegisterCallNum() == pageLump[returnNum].getShiftRegisterCallNum()){
					arrk[n] = i;
					n++;	
				}
			}
		}//�ڶ���ѭ�����ҳ�����λ�Ĵ�����1����ҳ����ͬ��ҳ��λ��
		if(permuteNum < n-1){
			permuteNum++;
		} else {
			permuteNum = j.getLumpStart();
		}//������λ�Ĵ����к�1������ͬ��ҳ������£�ͨ������permuteNumѡ��Ҫ�û���ҳ���λ�ã���ֹ���û�ҳ������ͬһλ��
		returnNum = arrk[permuteNum];
		return returnNum;
	}//������Ҫ���û���ҳ�棬��pageLump�е�λ��
	
	public int permutePageSiteOPT(Job j){
		int k = j.getNowPageSite();
		int returnNum = 0;
		lackPageNum++;
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){//ѭ���жϺ���pageBunch[]�л�����ָ�ҳ��Ĵ���
			if(pageLump[i] != null){
				while(k < j.getPageBunch().size()){
					if(pageLump[i].getBelongJobId() == j.getJobId() && 
							pageLump[i].getPageId() == j.getPageBunch().get(k).getPageId()){
						pageLump[i].setSurplusCallNum(1);//ʹ�ô˷�����Ӧ�õ���setSurplusCallNum(0)
					}
					k++;
				}
				k = j.getNowPageSite();
			}
		}
		for(int i=j.getLumpStart(); i<=j.getLumpEnd(); i++){//��ʼ����Сֵ
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
				pageLump[i].setSurplusCallNum(0);//ʹsurplusCallNum����
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
	}//������pageLump�У���nowJob��ʱҪִ�е�ҳ�棬��ͬ��ҳ���λ��
	public void allocateLump(int n1, Job nowJob){//�������Ĺ���ҳ����û�
		while(false == isExecute){
			
		}
		int gNPS = nowJob.getNowPageSite();
		if(gNPS != -1){//ȷ������ִ��job�У�����δִ�����ҳ��
			String s = null;
			int k = hasSamePageSite(nowJob);//�ж�pageLump�У��Ƿ��Ѿ����ڸ���ҵ����ͬ��ҳ��
			if(k != -1){//���ڸ���ҵ����ͬҳ�棬ֱ�ӽ���ҳ����λ�Ĵ�����λ��1
				pageLump[k].setShiftRegister(true);
				pageLump[k].setArriveLumpTime(arriveLumpTime);
				pageLump[k].setLastTime(0);
				pageLump[k].setBeCall(true);
				for(int i=0; i<pageLump.length; i++){
					if(pageLump[i] != null && i != k){
						pageLump[i].setLastTime(1);
					}
				}
				s = "��ҵ"+nowJob.getJobId()+"��"+(timeTurn-1)+"-"+timeTurn+"����ִ�У�ҳ��"
					+pageLump[k].getPageId()+"�������"+k+"������ͬҳ�棬ֱ�ӽ���������";
				MyFrame.l2.add(s);
				MyFrame.panel3.launchPanel(k, pageLump[k]);
				arriveLumpTime++;
				nowJob.setNowPageSite();
			} else {//�����жϷ��������ҵ��pageLump���Ƿ��п��е������
				k = this.hasEmptySite(nowJob);
				if(k != -1){//�п��У�ֱ�Ӵ���ҳ��
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
					s = "��ҵ"+nowJob.getJobId()+"��"+(timeTurn-1)+"-"+timeTurn+"����ִ�У�" +
							"����ҵ�������"+k+"�Կ��У�ҳ��"+pageLump[k].getPageId()+"ֱ�ӽ���������";
					MyFrame.l2.add(s);
					MyFrame.panel3.launchPanel(k, pageLump[k]);
					arriveLumpTime++;
					nowJob.setNowPageSite();
				} else {//û�п��еģ����жϷ����ϵͳ����������Ƿ��ɿ��е�
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
					s = "��ҵ"+nowJob.getJobId()+"��"+(timeTurn-1)+"-"+timeTurn+"����ִ�У�ҳ��" +
						pageLump[k].getPageId()+"�û������"+k+"��ԭ��ҳ��";
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
			}//ÿִ��һ��ҳ�棬��ʹҳ�����λ�Ĵ�������һλ
		}
	}//������Ҫִ�е�nowJob�е�ҳ�棬����pageLump
	public void manageJob(){//���������
		this.sortJobList();
		List<Job> jobLink = new ArrayList<Job>();
		jobLink.addAll(jobList);
		int i = 0;//�����ж�jobLink�Ƿ�Ϊ��
		int g = 0;//�����ж�����ִ�е���ҵ��һ��Ҫִ�ж���ҳ��
		int k = 1;//��nowJobִ��һ��ʱ��Ƭ����û��ִ�����ʱ�������ж�jobLink�����У�ʣ��Job�ĵ���ʱ����ʱ��Ƭ�Ĵ�С���һ�λ
		while(i != -1){
//			while(true == isExecute) {
				Job nowJob = jobLink.get(0);
				if(!jobLink.isEmpty()){
					while(timeTurn < nowJob.getArrivedTime()){//�������ʱ��С�ڴ˿�ִ�е���ҵ�ĵ���ʱ�䣬�ȴ�����ʱ��͵���ʱ����ͬ				
						timeTurn++;
					}				
					nowJob.setCpuTime();//ִ�е���ҵռ��CPU��ʱ���һ
					timeTurn++;
					nowJob.setFinishTime(timeTurn);//��ҵ�����ʱ�䣬����ʱ��Ƭ�˿̵�ʱ��
					while(g < nowJob.getExecutePageNum()){//��ҵִ��һ�Σ���Ҫִ�е�ҳ������
						this.allocateLump(jobList.size(), nowJob);//ִ���������ȷ������жϸ���ҵ�˿̵�ҳ��������������
						g++;
					}
					g = 0;
					isExecute = true;//����g��isExecute������һ�´�ѭ��ʱʹ��
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(nowJob.getCpuTime() >= nowJob.getRequiredTime()){//�����ҵռ��CPU��ʱ������������Ҫ��ʱ�䣬��ô����ִ�����
						nowJob.setState(true);//����ҵ��״̬����Ϊtrue����ʾ�����
						jobLink.remove(nowJob);//�Ƴ�����ɵ���ҵ
					} else {
						if(jobLink.size() != 1){//�������ҵδ��ɣ��жϺ�����ҵ�ĵ���ʱ���Ƿ�С�ڵ������ڵ�ʱ��
							while(jobLink.size() > k
								&& timeTurn >= jobLink.get(k).getArrivedTime()){//����ǣ���ô������ҵ��������з��ϴ��������ҵ��λ
								Job temp = jobLink.get(k-1);
								jobLink.set(k-1,jobLink.get(k));
								jobLink.set(k,temp);//������ҵ�ӵ��ȴ�ִ����ҵ���е�ĩβ
								k++;
							}
							k=1;//����k���Ա���һ��ѭ��ʹ��
						}				
						
					}
				}
				if(jobLink.isEmpty()){//�����ҵ����Ϊ�գ���ô�˳�ѭ��
					isExecute = false;
					i = -1;
				}
//			}
		}
		this.lackPageProbability(jobList.size(), jobList.get(0).getPageBunch().size());
	}
	public void sortJobList(){//���յ���ʱ������
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
				null, "�Ƿ��Զ�����ȫ����ҵ��ҳ�洮", "s", JOptionPane.YES_NO_OPTION);
		int surplus = 0;
		int last = 0;
		if(allYesOrNo == 0){//�ǵĻ���ȫ���Զ�����
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
					int pageId = j%n2;//pageId�Ǹ�ҳ����ҳ�洮�е�λ����Job��С������
					Page p = new Page(pageId,i);
					nowJob.setPageBunch(p);//��job��ҳ�洮�м����ҳ��
				}
				Collections.shuffle(nowJob.getPageBunch());//��ҳ�洮��������
				this.showPageBunch(i,n3,nowJob);
				jobList.add(nowJob);				
			}
		} else {//һ��ҳ��һ��ҳ�����ɣ�ÿ��ҳ���ֿ����Զ����ɺ��ֶ�����
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
						null, "���ǵ�"+(i)+"����ҵ���Ƿ��Զ�����ҳ�洮",
							"s", JOptionPane.YES_NO_OPTION);
				if(yesORno==0){//�ǵĻ��Զ����ɸ�ҳ��
					for(int j=0; j<n3; j++){
						int pageId = j%n2;
						Page p = new Page(pageId,i);
						nowJob.setPageBunch(p);
					}
					Collections.shuffle(nowJob.getPageBunch());
					this.showPageBunch(i,n3,nowJob);
				} else if(yesORno==1){//��Ļ����ֶ�����ҳ�洮
					try{
						String str = JOptionPane.showInputDialog(
								"ҳ�淶Χ0-"+(n2-1)+"," +
										"������ҳ�洮,���ȵ���"+n3+",��Ӣ��״̬�µġ�,������");
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
							JOptionPane.showMessageDialog(null, "���������뷵�ش�ͷ�������ã�");
							break;
						}
					} catch(NullPointerException e){
						JOptionPane.showMessageDialog(null, "���������뷵�ش�ͷ�������ã�");
						break;
					}
				}
				jobList.add(nowJob);
			}
		}
	}
	public void showPageBunch(int i, int n3, Job nowJob){//��ʾҳ�洮
		String s = "";
		for(int j=0; j<n3; j++){
			s = s + nowJob.getPageBunch().get(j) + ",";
		}
		
		MyFrame.l1.add("��ҵ" + i + "��ҳ�洮��" + s + "\n");
	}
	public void removeAll(){//�Ƴ�pageLump��jobList�е����ݣ���ʱ��ƬtimeTurn����
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
