import java.util.LinkedList;
import java.util.List;
public class Test2 {
	/**
	 * @param args
	 * List接口与一些方法
	 */
	public static void main(String[] args) {
		List l = new LinkedList();
		for(int i=0; i<=5; i++){
			l.add("a"+i);
		}
		System.out.println(l);
		l.add(3,"a100");
		System.out.println(l);
		l.set(6, "a200");
		System.out.println(l);
		System.out.print((String)l.get(2) + " ");
		System.out.println(l.indexOf("a3"));
		l.remove(1);
		System.out.println(l);
	}
}
