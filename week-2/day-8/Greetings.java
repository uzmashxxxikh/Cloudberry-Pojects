package day8;

public class Greetings 
{
	//No Parameters, No Return Value
	
	void m1()
	{
		System.out.println("Hello");
	}
	
	//No Parameters, Returns Value
	
	String m2()
	{
		return ("Hello - How are you?");
	}
	
	//Takes Parameters, No Return Value
	
	void m3(String name)
	{
		System.out.println("Hello"+name);
	}
	
	//Takes Parameters, Returns Value
	
	String m4(String name)
	{
		return ("Hello"+name);
	}
}