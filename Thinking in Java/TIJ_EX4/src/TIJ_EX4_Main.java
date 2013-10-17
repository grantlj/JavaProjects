import java.util.*;
public class TIJ_EX4_Main {
 static class TTree
{
	private
		int i;
	public
    TTree(int i)
	{
		this.i=i;
	}
	TTree Growth()
	{
		i++;
		return this;
	}
	void prt()
	{
	   System.out.println(i);
	}
}
	public static void main(String[] args)
  {
	 TTree my1=new TTree(2);
	 my1.Growth().Growth();
	 my1.prt();
	 
  }
}
