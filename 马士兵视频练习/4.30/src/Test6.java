//import java.util.Collection;
//import java.util.HashSet;
//public class Test6 {
//	/**
//	 * @param args
//	 * ����������ڵ���remove��contains����ʱ����Ҫ�Ƚ϶����Ƿ����
//	 * ����漰�������equals��hashCode����
//	 * �����Զ������ͣ���Ҫ��д��������������ʵ���Զ�������ȹ���
//	 * ע�⣺��ȶ���Ӧ�þ�����ȵ�hash codes
//	 */
//	public static void main(String[] args) {
//		Collection c = new HashSet();
//		c.add("hello");
//		c.add(new Name("f1","l1"));
//		c.add(new Integer(100));
//		c.remove("hello");
//		c.remove(new Integer(100));
//		System.out.println(c.remove(new Name("f1","l1")));
//		System.out.println(c);
//	}
//}
//class Name{
//	private String firstName, lastName;
//	public Name(String f, String l){
//		this.firstName = f;
//		this.lastName = l;
//	}
//	public String getFirstName(){
//		return this.firstName;
//	}
//	public String getLastName(){
//		return this.lastName;
//	}
//	public String toString(){
//		return this.firstName + " " + this.lastName;
//	}
//	public boolean equals(Object obj){
//		if(obj instanceof Name){
//			Name n = (Name)obj;
//			return (this.firstName.equals(n.firstName)) && (this.lastName.equals(n.lastName));
//		} else {
//			return super.equals(obj);
//		}	
//	}
//	public int hashCode(){
//		return this.firstName.hashCode();
//	}
//}
