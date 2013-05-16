//
//public class Test2 {
//	public static void main(String[] args) {
//		Animal a = new Animal("animalName");
//		Dog d = new Dog("dogName");
//		Cat c = new Cat("catName");
//		Girl g1 = new Girl("girlName1",a);
//		Girl g2 = new Girl("girlName1",d);
//		Girl g3 = new Girl("girlName1",c);
//		g1.petEnjoy();
//		g2.petEnjoy();
//		g3.petEnjoy();
//
//	}
//}
//
//class Animal{
//	private String name;
//	Animal(String n){
//		this.name = n;
//	}
//	public String getName(){
//		return this.name;
//	}
//	public void enjoy(){
//		System.out.println(this.name+"发出叫……");
//	}
//}
//class Dog extends Animal{
//	Dog(String n){
//		super(n);
//	}
//	public void enjoy(){
//		System.out.println(getName()+"发出狗叫……");
//	}
//}
//class Cat extends Animal{
//	Cat(String n){
//		super(n);
//	}
//	public void enjoy(){
//		System.out.println(getName()+"发出猫叫……");
//	}
//}
//class Girl{
//	private String name;
//	private Animal pet;
//	
//	Girl(String n,Animal a){
//		this.name = n;
//		this.pet = a;
//	}
//	public void petEnjoy(){
//		System.out.print(this.name+"的宠物");
//		this.pet.enjoy();
//	}
//}
