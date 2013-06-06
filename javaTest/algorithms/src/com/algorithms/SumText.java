/**
 * 输入整形数组，假设数组中的数字正负不等，求总共有两两相加或三三相加等于0的次数
 * 如果该整形数组非常大，其中有数万条甚至数十万条数据，通过先排序结合二分法查找的方式
 * 两两相加的时间复杂度能由平方级，降低到对数级别
 * 三三相加的时间复杂度能由立方级，降低到线性对数级别
 */
package com.algorithms;

import java.util.Arrays;

public class SumText {
	public static int threeCount(int[] a){
		int N = a.length;
		int cnt = 0;
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				for(int k=j+1; k<N; k++){
					if(a[i] + a[j] +a[k] == 0)
						cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static int threeFastCount(int[] a){
		int N = a.length;
		int cnt = 0;
		Arrays.sort(a);
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				if(BinarySearch.rank(-a[i]-a[j], a) > j)
					cnt++;
			}
		}
		return cnt;
	}
	
	public static int twoCount(int[] a){
		int N = a.length;
		int cnt = 0;
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				if(a[i] + a[j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
	
	public static int twoFastCount(int[] a){
		int N = a.length;
		int cnt = 0;
		Arrays.sort(a);
		for(int i=0; i<N; i++){
			if(BinarySearch.rank(-a[i], a) > i)
				cnt++;
		}
		return cnt++;
	}
}
