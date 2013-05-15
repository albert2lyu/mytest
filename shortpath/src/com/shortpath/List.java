package com.shortpath;

import java.awt.Point;
import java.util.ArrayList;

//类List
//基于ArrayList类的一个自定义类,用于存储当前地图中所有的地点(Place)的相关信息
public class List {
	private ArrayList<Place> list;
	
	public ArrayList<Place> getList() {
		return list;
	}


	//构造方法
	//存储当前地图
	public List(){
		list=new ArrayList<Place>();
		add(0,new Place(new String("empty"),new Point(0,0)));
		add(1,new Place(new String("a"),new Point(100,150)));
		add(2,new Place(new String("b"),new Point(300,150)));
		add(3,new Place(new String("c"),new Point(100,350)));
		add(4,new Place(new String("d"),new Point(300,350)));
		add(5,new Place(new String("e"),new Point(450,250)));
	}
	
	
	//在List尾部追加新元素
	//参数element(Place)是新添加的元素
	public List add(Place element){
		list.add(element);
		return this;
	}
	
	
	//在List指定位置添加指定元素
	//参数index为指定位置参数,element为指定元素
	public List add(int index,Place element){
		list.add(index, element);
		return this;
	}
	
	//若列表中包含名称为e的元素,则返回true
	public boolean contains(String e){
		for(int i=0;i<list.size();i++)
			if(list.get(i).getName().equals(e))
				return true;
		return false;
	}
	
	//若列表中包含名称为e的元素,则返回true
	public boolean contains(Point e){
		for(int i=0;i<list.size();i++)
			if(list.get(i).getPoint().x == e.x && list.get(i).getPoint().y == e.y)
				return true;
		return false;
	}
	
	//返回指定位置元素的名称
	public String getName(int index){
		return list.get(index).getName();
	}
	
	//返回指定位置元素的位置
	public Point getPoint(int index){
		if(index>0&&index<list.size())
			return list.get(index).getPoint();
		else
			return null;
	}
	
	//返回名称为e的元素的索引,若列表中不存在该元素,则返回-1;
	public int indexOf(String e){
		for(int i=1;i<list.size();i++)
			if(list.get(i).getName().equals(e))
				return i;
		return -1;	
	}

	//返回名称为e的元素的索引,若列表中不存在该元素,则返回-1;
	public int indexOf(Point e){
		for(int i=1;i<list.size();i++)
			if(list.get(i).getPoint().x == e.x && list.get(i).getPoint().y == e.y)
				return i;
		return -1;	
	}	
	
	//从列表中移除名称为e元素,若列表中不存在该元素返回false
	public boolean remove(String e){
		int i = indexOf(e);
	    return list.remove(list.get(i));
	}
	
	//返回此列表中的元素数
	public int size(){
		return list.size();
	}
	
	//返回此列表中指定位置index的元素
	public Place get(int index){
		return list.get(index);
	}
	
	

}

