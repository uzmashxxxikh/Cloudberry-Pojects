package day12;

class GrandParent
{
	String lastName = "Johnson";
	void message1()
	{
		System.out.println("Message from GrandParent");
	}
}

class Parent1 extends GrandParent
{
	String parentJob = "Engineer";
	
	void message2()
	{
		System.out.println("Message from Parent");
	}
}

class Child1 extends Parent1
{
	String school = "Greenwood High";
	
	void message3()
	{
		System.out.println("Message from Child");
	}
}

public class MultiLevelInheritanceExample {

	public static void main(String[] args) 
	{
		
		Child1 obj = new Child1(); //object of Child1 class
		System.out.println(obj.school); //Child's variable
		obj.message3(); //Child's method
		
		System.out.println(obj.parentJob); // Inherited from Parent 1
		obj.message2(); //Inherited from Parent1
		
		System.out.println(obj.lastName); // Inherited from Grand Parent
		obj.message1(); // Inherited from Grand Parent
		
		

	}

}