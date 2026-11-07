package day12;

interface Interface1 {
	String commonTrait = "Hardworking"; // Variable in interface are public, static, final;

	void method1();
}

interface Interface2 {
	String uniqueSkill = "Creative Thinking"; // Variable in interface are public, static, final;

	void method2();
}

class Child4 implements Interface1, Interface2 {
	String name = "Alex";

	public void method1() {
		System.out.println("Method1 from Interface1");
	}

	public void method2() {
		System.out.println("Method2 from Interface2");
	}
}

public class MultipleInheritanceExample {

	public static void main(String[] args) {

		Child4 obj = new Child4();
		System.out.println(obj.name); // Child's variable
		obj.method1();
		obj.method2();

		// System.out.println(obj.commonTrait); // from Interface1
		// System.out.println(obj.uniqueSkill); // from Interface2

		System.out.println(Interface1.commonTrait); // From Interface 1
		System.out.println(Interface2.uniqueSkill); // from Interface 2

		/*
		 * obj.method1(); obj.method2();
		 */

	}

}