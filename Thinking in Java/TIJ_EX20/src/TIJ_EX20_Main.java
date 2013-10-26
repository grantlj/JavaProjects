//Training casting....
class SchoolMember 
{
	private String property;
	SchoolMember(String a)
	{
		System.out.println(a+" has been created.");
		property=a;
	}
	protected void toilet()
	{
		int x=(int) (Math.random()*20);
		System.out.println(property+" has toilet for:"+x+" minutes.");
	}
	
}

class Stu extends SchoolMember
{
	Stu()
	{
		super("student");
		System.out.println("Fuck2.");
	}
	
	protected void HaveClass()
	{
		System.out.println("Student is having classes.");
	}
	
	
}
public class TIJ_EX20_Main {
	public static void upcast(SchoolMember i)
	{
		i.toilet();
		//i.HaveClass();
	}
	public static void main(String[] args)
	{
		Stu s1=new Stu();
		upcast(s1);
		
	}

}
