class TC
{
	int x;
}
public class TIJ_EX23_Main {
	public static void main(String[] args)
	{
		TC[] b,c;
		b=new TC[5];
		c=new TC[5];       //New a handle for the b array.
		for (int i=0;i<5;i++)
		{
			b[i]=new TC();  //Every b[i] has a TC's handle. so new it.
			c[i]=new TC();
			b[i].x=i;
			c[i].x=5-i;
		}
		b=c; //Give the handle....
		for (int i=0;i<5;i++)
		{
			System.out.println("b["+i+"]="+b[i].x+"  c["+i+"]="+c[i].x);
		}
		//System.out.println(b[1].i);
		int[] d=new int[5];
	    for (int i=0;i<5;i++)
	    	d[i]=(int)(Math.random()*100+10); //int is not a class here. NO CLASS-NO NEW.
	    for (int i=0;i<5;i++)
	    	System.out.println(d[i]);
	    
	}
}
