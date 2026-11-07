package day8;

public class CloudBerryMain {

	public static void main(String[] args)
	{
		//CloudBerryStudent stu = new CloudBerryStudent();
		//1. Using an object reference variable
		
		//stu.sid = 101;
		//stu.sname = "John";
		//stu.grade = 'A';
		
		//stu.printStuData();
		
		//2.Using a method (User defined Method)
		//stu.setStuData(101, "John", 'A');
		//stu.printStuData();
		
		//3. Assign a value Using a constructor
		CloudBerryStudent stu = new CloudBerryStudent(101, "John", 'A');
		stu.printStuData();
		
		CloudBerryStudent stu2 = new CloudBerryStudent(101);
		stu2.printStuData();
		
		

	}

}