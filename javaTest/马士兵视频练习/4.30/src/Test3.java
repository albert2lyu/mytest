//
//public class Test3 {
//	/**
//	 * @param args
//	 * 解析字符串，得到二维数组
//	 * "1,2;3,4,5;6,7,8,9"
//	 */
//	public static void main(String[] args) {
//		String s = "1,2;3,4,5;6,7,8,9";
//		String[] ss = s.split(";");
//		String[][] sss = new String[ss.length][];
//		double[][] d = new double[sss.length][];
//		for(int i=0; i<ss.length; i++){
//			sss[i] = ss[i].split(",");
//		}	
//		for(int i=0; i<sss.length; i++){
//			d[i] = new double[sss[i].length];
//			for(int j=0; j<sss[i].length; j++){
//				d[i][j] = Double.parseDouble(sss[i][j]);
//			}
//		}
//		for(int i=0; i<d.length; i++){
//			for(int j=0; j<d[i].length; j++){
//				System.out.println("d["+i+"]["+j+"] = "+d[i][j]);
//			}
//		}
//	}
//}
