//
//public class Test3 {
//	
//	/**
//	 * @param args
//	 * ��ٸ��ˣ�������Χ��Ȧ�������ˣ��뿪��һ�������һ����
//	 * ������Ǵӿ�ʼλ��0,֮��ĵڼ�����
//	 */
//	
//	public static void main(String[] args) {
//		boolean[] b = new boolean[500];
//		for(int i=0; i<b.length; i++){
//			b[i] = true;
//		}
//		
//		int leftCount = b.length;//��ʣ������
//		int countNum = 0;//����
//		int index = 0;//�˵�λ��
//		
//		while(leftCount > 1){
//			if(b[index] == true){//ֻ��true
//				countNum++;
//				if(countNum == 3){
//					countNum = 0;
//					b[index] = false;//����3��ʹ��Ӧ����Ϊfalse
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
