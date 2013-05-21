//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//public class Test5 {
//	/**
//	 * @param args
//	 * Map 容器
//	 * Auto-boxing and unboxing自动打包和解包
//	 * Generic 泛型  <>
//	 */
//	public static void main(String[] args) {
////		Map m1 = new HashMap();
////		Map m2 = new TreeMap();
//		Map<String,Integer> m1 = new HashMap<String,Integer>();//泛型
//		Map<String,Integer> m2 = new TreeMap<String,Integer>();//泛型
////		m1.put("one", new Integer(1));
//		m1.put("one", 1);//会将1自动打包成以上格式，将1打包为Integer对象
////		m1.put("two", new Integer(2));
//		m1.put("two", 2);
////		m1.put("three", new Integer(3));
//		m1.put("three", 3);
////		m2.put("A", new Integer(1));
//		m2.put("A", 1);
////		m2.put("B", new Integer(2));
//		m2.put("A", 2);
//		System.out.println(m1.size());
//		System.out.println(m1.containsKey("one"));
////		System.out.println(m2.containsValue(new Integer(2)));
//		System.out.println(m2.containsValue(2));
//		if(m1.containsKey("two")){
////			int i = ((Integer)m1.get("two")).intValue();
//			//以上是自动打包解包和泛型都不使用
////			int i = (Integer)m1.get("two");
//			//以上是不使用泛型时，会将得到的Integer对象自动解包为int
//			//注意前面必须加Integer(或其他类型)说明m1.get()得到的对象为Integer(或其他)类型
//			int i = m1.get("two");
//			System.out.println(i);
//		}
////		Map m3 = new HashMap(m1);//没有用泛型
//		Map<String,Integer> m3 = new HashMap<String,Integer>(m1);
//		m3.putAll(m2);
//		System.out.println(m3);
//	}
//}
