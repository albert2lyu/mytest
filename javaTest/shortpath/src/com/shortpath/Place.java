package com.shortpath;

import java.awt.Point;

//类Place
//存储某个地点的名称(String)及该地点在地图中的位置(Point)
public class Place {
	private String n;// 名称
	private Point p;// 位置

	// 构造函数
	// 参数n为该地点名称,p为该地点位置
	public Place(String n, Point p) {
		this.n = n;
		this.p = p;
	}

	// 得到某地点名称
	public String getName() {
		return n;
	}

	// 得到某地点位置
	public Point getPoint() {
		return p;
	}

}
