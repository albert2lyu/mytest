//import java.util.HashSet;
//import java.util.Set;
//public class Test1 {
//	/**
//	 * @param args
//	 * set��list�����࣬����Constructor(Collection c)���췽�������Գ�ʼ��������
//	 */
//	public static void main(String[] args) {
//		Set s1 = new HashSet();
//		Set s2 = new HashSet();
//		s1.add("a");s1.add("b");s1.add("c");
//		s2.add("d");s2.add("a");s2.add("b");
//		
//		Set sn = new HashSet(s1);
//		sn.retainAll(s2);//�����߽���
//		Set su = new HashSet(s2);
//		su.addAll(s1);// ��ΪSet�������в�������ͬԪ�أ������൱�ڲ���
//		System.out.println(sn);
//		System.out.println(su);
//	}
//}
