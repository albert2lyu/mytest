//
//public class Test4 {
//
//	/**
//	 * @param args
//	 * 五百个人，手拉手围成圈，数三退（离开）一，最后留一个。
//	 * 这个人是从开始位置0，之后的第几个？
//	 */
//	
//	public static void main(String[] args) {
//		KidCircle kC = new KidCircle(500);
//		int countNum = 0;
//		Kid k = new Kid();
//		k = kC.first;
//		while(kC.count > 1){			
//			countNum++;
//			if(countNum == 3){
//				countNum = 0;
//				kC.delete(k);
//			}
//			k = k.right;			
//		}
//		System.out.print(kC.first.id);
//	}
//}
//
//class Kid{
//	int id;
//	Kid left;
//	Kid right;
//}
//class KidCircle{
//	int count = 0;
//	Kid first, last;
//	KidCircle(int n){
//		for(int i=0; i<n; i++){
//			add();
//		}
//	}
//	void add(){
//		Kid k = new Kid();
//		k.id = count;
//		if(count <= 0){
//			first = k;
//			last = k;
//			k.left = k;
//			k.right = k;
//		} else {
//			last.right = k;
//			k.left = last;
//			k.right = first;
//			first.left = k;
//			last = k;		
//		}
//		count++;		
//	}
//	void delete(Kid k){
//		if(count <= 0){
//			System.out.println("已经没有成员！");
//			return;
//		} else if(count == 1){
//			first = last = null;
//		} else {
//			k.left.right = k.right;
//			k.right.left = k.left;
//			if(k == first){
//				first = k.right;
//			} else if(k == last){
//				last = k.left;
//			}
//		}
//		count--;
//	}
//}
