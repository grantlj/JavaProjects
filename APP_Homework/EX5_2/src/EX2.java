/*
   Author        :  LiuJiang
   Compile Date  :  2013/11/13
   Introduction  :
     Homework    :  Ex5.2
     Exercise for class.
**/
class Student
{
	private String name;
	private int    age;
	Student()
	{
		this.name="Annoymous";
		this.age=20;
	}
	
	void setName(String x)
	{
		this.name=x;
	}
	
	String getName()
	{
		return this.name;
	}
	
	void setAge(int x)
	{
		this.age=x;
	}
	
	int getAge()
	{
		return this.age;
	}
	
	boolean isSameAge(Student that)
	{
		return this.age==that.age;
	}
}

public class EX2 {
  public static void main(String[] args)
  {
	  Student stu1=new Student();
	  System.out.println("stu1 initial: Name: "+stu1.getName()+" Age:"+stu1.getAge());
	  
	  Student stu2=new Student();
	  System.out.println("stu2 initial: Name: "+stu2.getName()+" Age:"+stu2.getAge());
	  
	  stu1.setName("LJ");stu1.setAge(20);
	  stu2.setName("HXY");stu2.setAge(19);
	  
	  System.out.println("stu1 setted: Name: "+stu1.getName()+" Age:"+stu1.getAge());
	  System.out.println("stu2 setted: Name: "+stu2.getName()+" Age:"+stu2.getAge());
	  
	  System.out.println("stu 1 compare with stu 2 in age:"+stu1.isSameAge(stu2));
  }
}
