//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//public class Test7 {
//	/**
//	 * @param args
//	 * ����ʵ����Collection�ӿڵ������࣬����һ��iterator����
//	 * ����һ��ʵ����Iterator�ӿڵĶ���
//	 * Iterator�����Ϊ�����������Է����ʵ�ֶ�������Ԫ�صı�������
//	 */
//	public static void main(String[] args) {
//		Collection c = new HashSet();
//		
////		c.add(new Name("f1","l1"));
////		c.add(new Name("f2","l2"));
////		c.add(new Name("f3","l3"));
////		Iterator i = c.iterator();
////		while(i.hasNext()){
////			Name n = (Name)i.next();
////			System.out.println(n.getFirstName()+" "+n.getLastName());
////		}
//		
//		c.add(new Name("fff1","llll"));
//		c.add(new Name("f1","l1"));
//		c.add(new Name("fff2","lll2"));
//		for(Iterator i = c.iterator(); i.hasNext(); ){
//			Name n = (Name)i.next();
//			if(n.getFirstName().length() < 3){
//				i.remove();//�������c.remove(n);���������
//			}
//		}
//		System.out.println(c);
//		
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
