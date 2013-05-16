
public class Test3 {
	public static void main(String[] args) {
		GoldenMonkey g = new GoldenMonkey();
		g.enjoy();
		g.test();
	}

}

interface Valuable{
	public double getMoney();
}
interface Protected{
	public void beProtected();
}
interface A extends Protected{
	public void m();
}

abstract class Animal{
//	private String name;
	abstract void enjoy();
}
class GoldenMonkey extends Animal implements Valuable,Protected{
	public double getMoney(){
		return 300000;
	}
	public void beProtected(){
		System.out.println("�ܱ������");
	}
	public void enjoy(){
		System.out.println("�л���");
	}
	public void test(){
		Valuable v = new GoldenMonkey();
		System.out.println(v.getMoney());
		Protected p = new GoldenMonkey();
		p.beProtected();
	}
}