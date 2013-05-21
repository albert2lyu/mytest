package com.algorithms;

import java.util.Arrays;

public class MainTest {
	//求一个数的绝对值
	public static int intAbs(int x){
		if(x < 0) return -x;
		else return x;
	}
	public static double doubleAbs(int x){
		if(x < 0.0) return -x;
		else return x;
	}
	//判断素数
	public static boolean isPrime(int N){
		if(N < 2) return false;
		for(int i=2; i*i<=N; i++){
			if(N % i == 0) return false;
		}
		return true;
	}
	//计算平方根（牛顿迭代法）,不断令t等于t和c/t的平均数，迭代个六七次后t的值就已经相当精确
	//0x5f375a86魔数，魔神之锤
	/* 	float xhalf = 0.5f*x;
		int i = *(int*)&x; // get bits for floating VALUE 
		i = 0x5f375a86- (i>>1); // gives initial guess y0
		x = *(float*)&i; // convert bits BACK to float
		x = x*(1.5f-xhalf*x*x); // Newton step, repeating increases accuracy
		return x;
	 */
	public static double sqrt(double c){
		if(c < 0) return Double.NaN;
		double err = 1e-15;
		double t = c;
		while(Math.abs(t - c/t) > err * t){
			t = (c/t + t) / 2.0;
		}
		return t;
	}
	//计算调和级数
	public static double H(int N){
		double sum = 0.0;
		for(int i=1; i<=N; i++){
			sum += 1.0/i;
		}
		return sum;
	}
	//二分法查找（递归实现）
	public static int rank(int key, int[] a){
		Arrays.sort(a);
		return rank(key, a, 0, a.length - 1);
	}
	public static int rank(int key, int[] a, int lo, int hi){
		//如果key存在于a[]中，它的索引不会小于lo且不会大于hi
		if(lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if(key < a[mid]) return rank(key, a, lo, mid - 1);
		else if(key > a[mid]) return rank(key, a, lo + 1, mid);
		else return mid;
	}
	//作业1.1.14
	public static int lg(int N){
		int i=0, j = 2;
		while(j <= N){
			j *= 2;
			i++;
		}
		
		return i;
	}
	//Fibonacci 递归实现，效率极慢╮(╯Д╰)╭
	public static long F(int N){
		if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N-1) + F(N-2);
	}
	//用数组保存已经计算过的值，效率8错，挺快
	public static long FArray(int N){
		if(N == 0) return 0;
		if(N == 1) return 1;
		int[] arr = new int[N];
		arr[0] = 0;
		arr[1] = 1;
		int i=2, j, k;
//		long sum=1;
		while(i < N){
			j = i - 1;
			k = i - 2;
			arr[i] = arr[j] + arr[k];
			//sum += arr[i];
			i++;
		}
		return arr[i-1];
	}
	//欧几里得算法
	/* 	定理：gcd(a,b) = gcd(b,a mod b) (a>b 且a mod b 不为0)
		证明：a可以表示成a = kb + r，则r = a mod b
		假设d是a,b的一个公约数，则有
		d|a,d|b，而r = a - kb，因此d|r
		因此d也是（b,a mod b）的公约数
		因此（a,b）和（b,a mod b）的公约数是一样的，其最大公约数也必然相等，得证  */
	public static int gcd(int m, int n){
		if(m % n == 0) return n;
		else return gcd(n, m % n);
	}
	
	public static void main(String[] args) {
		System.out.println(myStery("abcd"));
	}
	
	//递归字符串，倒序输出
	public static String myStery(String s){
		int n = s.length();
		if(n <= 1) return s;
		String a = s.substring(0, n/2);
		String b = s.substring(n/2, n);
		return myStery(b) + myStery(a);
	}
}
