//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//public class Test4 {
//	/**
//	 * @param args
//	 * Comparable接口CompareTo方法实现，用于比较两个对象
//	 * String类已经实现此方法
//	 */
//	public static void main(String[] args) {
//		List l = new LinkedList();
//		l.add(new Name("Karl", "M"));
//		l.add(new Name("Steven", "Lee"));
//		l.add(new Name("Joho", "O"));
//		l.add(new Name("Tom", "M"));
//		System.out.println(l);
//		Collections.sort(l);
//		System.out.println(l);
//	}
//}
//class Name implements Comparable{
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
//	public int compareTo(Object o){
//		Name n = (Name)o;
//		int i = this.lastName.compareTo(n.lastName);
//		return (i != 0 ? i : this.firstName.compareTo(n.firstName));
//	}
//}