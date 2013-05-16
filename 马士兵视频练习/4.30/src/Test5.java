//import java.io.File;
//public class Test5 {
//	/**
//	 * @param args
//	 * 以树状打印出"E:/A"目录下的所有文件名
//	 */
//	public static void main(String[] args) {
//		File f = new File("E:/A");
//		System.out.println(f.getName());
//		tree(f, 1);
//	}
//	private static void tree(File f, int l){
//		String s = "";
//		for(int i=0; i<l; i++){
//			s += "  ";
//		}
//		File[] childs = f.listFiles();
//		for(int i=0; i<childs.length; i++){
//			System.out.println(s + childs[i].getName());
//			if(childs[i].isDirectory()){
//				tree(childs[i], l+1);
//			}
//		}
//	}
//}
