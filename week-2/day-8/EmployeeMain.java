package day8;

public class EmployeeMain {

	 public static void main(String[] args) 
	  {
	   Employee emp1 = new Employee(); // created an object of an employee class
	   emp1.eid = 101;
	   emp1.ename = "John";
	   emp1.job = "Manager";
	   emp1.sal = 70000;
	   
	   emp1.display();
	   
	   Employee emp2 = new Employee(); 
	   emp2.eid =102;
	   emp2.ename ="Ralph";
	   emp2.job = "Senior VP";
	   emp2.sal = 100000;
	   
	   emp2.display();
	   
	   
	   
	  }

}