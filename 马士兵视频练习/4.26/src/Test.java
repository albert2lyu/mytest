
//public class Test {
//	public static void main(String[] args) {
//		Test t = new Test();
//		Animal a = new Animal("animal");
//		Dog d = new Dog("dogName","white");
//		Cat c = new Cat("catName","yellow");
//		
//		t.compare(a);
//		t.compare(d);
//		t.compare(c);
//	}
//
//	public void compare(Animal a){
//		System.out.print("Name£º"+a.name);
//		if(a instanceof Dog){
//			Dog dog = (Dog)a;
//			System.out.println(" has "+dog.furColor+" fur!");
//		}
//		else if(a instanceof Cat){
//			Cat cat = (Cat)a;
//			System.out.println(" has "+cat.eyesColor+" eyes!");
//		}
//		else
//			System.out.println(" This is an animal!");
//	}
//}

//class Animal{
//	public String name;
//	Animal(String name){
//		this.name = name;
//	}
//}
//class Dog extends Animal{
//	public String furColor;
//	Dog(String n,String f){
//		super(n);
//		this.furColor = f;
//	}
//}
//class Cat extends Animal{
//	public String eyesColor;
//	Cat(String n,String e){
//		super(n);
//		this.eyesColor = e;
//	}
//}