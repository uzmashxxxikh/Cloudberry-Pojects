package day12;

class Parent {
	String familyName = "Smith";

	void greet() {
		System.out.println("Hello from Parent!");
	}
}

class Child extends Parent {
	int age = 10;
	// String familyName = "Johnson";

	void welcome() {
		System.out.println("Welcome from Child");
	}
}

public class SingleInheritanceExample {

	public static void main(String[] args) {
		Child obj = new Child(); // object of the child class
		System.out.println(obj.age); // child's variable
		obj.welcome();// Child's method

		System.out.println(obj.familyName); // Inherited variable

		obj.greet(); // Inherited Method

	}

}