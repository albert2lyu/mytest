//
//public class Test2 {
//
//	public static void main(String[] args) {
//		Date[] d = new Date[5];
//		d[0] = new Date(2004,5,8);
//		d[1] = new Date(2006,7,9);
//		d[2] = new Date(2004,3,8);
//		d[3] = new Date(2008,5,9);
//		d[4] = new Date(2006,3,9);
//		
//		bubbleSort(d);
//		for(int i=0;i<d.length;i++){
//			System.out.println(d[i]);
//		}
//	}
//	public static Date[] bubbleSort(Date[] d){
//		for(int i=d.length-1;i>=1;i--){
//			for(int j=0;j<=i-1;j++){
//				if(d[j].compare(d[j+1]) == 1){
//					Date temp = d[j];
//					d[j] = d[j+1];
//					d[j+1] = temp;
//				}
//			}
//		}
//		return d;
//	}
//}
//class Date{
//	int year, month, day;
//	Date(int y, int m, int d){
//		this.year = y;
//		this.month = m;
//		this.day = d;
//	}
//	public int compare(Date d){
//		return this.year > d.year ? 1 :
//			this.year < d.year ? -1 :
//				this.month > d.month ? 1 :
//					this.month < d.month ? -1 :
//						this.day > d.day ? 1 :
//							this.day < d.day ? -1 : 0;
//	}
//	public String toString(){
//		return "Yaer:Month:Day -- "+this.year+":"+this.month+":"+this.day;
//	}
//}