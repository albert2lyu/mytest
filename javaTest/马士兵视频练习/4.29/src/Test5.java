public class Test5 {

	/**
	 * @param args
	 * 二分法查找数组中指定成员的位置
	 */
	public static void main(String[] args) {
		int[] a = {1,3,6,8,9,10,12,18,20,34};
		int n = 10;
		System.out.println(binarySearch(a,n));
	}
	
	public static int binarySearch(int[] a,int i){
		if(a.length == 0){
			return -1;
		}
		
		int startPos = 0;
		int endPos = a.length - 1;
		int m;
		
		while(startPos <= endPos){
			m = (startPos + endPos)/2;
			if(a[m] == i){
				return m;
			} else if(a[m] > i){
				endPos = m-1;
			} else if(a[m] < i){
				startPos = m+1;
			}
		}
		return -1;
	}
}
