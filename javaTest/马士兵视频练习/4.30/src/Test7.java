//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//public class Test7 {
//	/**
//	 * @param args
//	 * 所有实现了Collection接口的容器类，都有一个iterator方法
//	 * 返回一个实现了Iterator接口的对象
//	 * Iterator对象成为迭代器，用以方便的实现对容器内元素的便利操作
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
//				i.remove();//如果换成c.remove(n);会产生例外
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
