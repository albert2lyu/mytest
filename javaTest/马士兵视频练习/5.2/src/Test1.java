//import java.util.HashSet;
//import java.util.Set;
//public class Test1 {
//	/**
//	 * @param args
//	 * set和list容器类，都有Constructor(Collection c)构造方法，用以初始化容器类
//	 */
//	public static void main(String[] args) {
//		Set s1 = new HashSet();
//		Set s2 = new HashSet();
//		s1.add("a");s1.add("b");s1.add("c");
//		s2.add("d");s2.add("a");s2.add("b");
//		
//		Set sn = new HashSet(s1);
//		sn.retainAll(s2);//求两者交集
//		Set su = new HashSet(s2);
//		su.addAll(s1);// 因为Set容器类中不能有相同元素，所以相当于并集
//		System.out.println(sn);
//		System.out.println(su);
//	}
//}
