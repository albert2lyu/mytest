//
//public class Test3 {
//	
//	/**
//	 * @param args
//	 * 五百个人，手拉手围成圈，数三退（离开）一，最后留一个。
//	 * 这个人是从开始位置0,之后的第几个？
//	 */
//	
//	public static void main(String[] args) {
//		boolean[] b = new boolean[500];
//		for(int i=0; i<b.length; i++){
//			b[i] = true;
//		}
//		
//		int leftCount = b.length;//还剩多少人
//		int countNum = 0;//计数
//		int index = 0;//人的位置
//		
//		while(leftCount > 1){
//			if(b[index] == true){//只数true
//				countNum++;
//				if(countNum == 3){
//					countNum = 0;
//					b[index] = false;//计数3，使相应的人为false
//					leftCount--;
//				}
//			}
//			index++;
//			if(index == b.length){
//				index = 0;
//			}
//		}
//		
//		for(int i=0; i<b.length; i++){
//			if(b[i] == true){
//				System.out.print(i);
//			}
//		}
//	}
//}
