package com.shortpath;

import java.awt.Point;
import java.util.ArrayList;

//��List
//����ArrayList���һ���Զ�����,���ڴ洢��ǰ��ͼ�����еĵص�(Place)�������Ϣ
public class List {
	private ArrayList<Place> list;
	
	public ArrayList<Place> getList() {
		return list;
	}


	//���췽��
	//�洢��ǰ��ͼ
	public List(){
		list=new ArrayList<Place>();
		add(0,new Place(new String("empty"),new Point(0,0)));
		add(1,new Place(new String("a"),new Point(100,150)));
		add(2,new Place(new String("b"),new Point(300,150)));
		add(3,new Place(new String("c"),new Point(100,350)));
		add(4,new Place(new String("d"),new Point(300,350)));
		add(5,new Place(new String("e"),new Point(450,250)));
	}
	
	
	//��Listβ��׷����Ԫ��
	//����element(Place)������ӵ�Ԫ��
	public List add(Place element){
		list.add(element);
		return this;
	}
	
	
	//��Listָ��λ�����ָ��Ԫ��
	//����indexΪָ��λ�ò���,elementΪָ��Ԫ��
	public List add(int index,Place element){
		list.add(index, element);
		return this;
	}
	
	//���б��а�������Ϊe��Ԫ��,�򷵻�true
	public boolean contains(String e){
		for(int i=0;i<list.size();i++)
			if(list.get(i).getName().equals(e))
				return true;
		return false;
	}
	
	//���б��а�������Ϊe��Ԫ��,�򷵻�true
	public boolean contains(Point e){
		for(int i=0;i<list.size();i++)
			if(list.get(i).getPoint().x == e.x && list.get(i).getPoint().y == e.y)
				return true;
		return false;
	}
	
	//����ָ��λ��Ԫ�ص�����
	public String getName(int index){
		return list.get(index).getName();
	}
	
	//����ָ��λ��Ԫ�ص�λ��
	public Point getPoint(int index){
		if(index>0&&index<list.size())
			return list.get(index).getPoint();
		else
			return null;
	}
	
	//��������Ϊe��Ԫ�ص�����,���б��в����ڸ�Ԫ��,�򷵻�-1;
	public int indexOf(String e){
		for(int i=1;i<list.size();i++)
			if(list.get(i).getName().equals(e))
				return i;
		return -1;	
	}

	//��������Ϊe��Ԫ�ص�����,���б��в����ڸ�Ԫ��,�򷵻�-1;
	public int indexOf(Point e){
		for(int i=1;i<list.size();i++)
			if(list.get(i).getPoint().x == e.x && list.get(i).getPoint().y == e.y)
				return i;
		return -1;	
	}	
	
	//���б����Ƴ�����ΪeԪ��,���б��в����ڸ�Ԫ�ط���false
	public boolean remove(String e){
		int i = indexOf(e);
	    return list.remove(list.get(i));
	}
	
	//���ش��б��е�Ԫ����
	public int size(){
		return list.size();
	}
	
	//���ش��б���ָ��λ��index��Ԫ��
	public Place get(int index){
		return list.get(index);
	}
	
	

}

