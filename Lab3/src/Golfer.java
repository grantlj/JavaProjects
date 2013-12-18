
public class Golfer {
  private String name;
  private int[] holes=new int[9];
  private int total=0;
  private Golfer dest=null;
 
  public Golfer(String name, int[] args)
  {
	  this.name=name;
	  if (this.name.length()<15)
	  {
		  for (int i=0;i<15-name.length();i++)
			  this.name=this.name+" ";
	  }
	  
	  for (int i=0;i<9;i++)
	  {
		  this.holes[i]=args[i];
		  total+=this.holes[i];
	  }
  }
  
  public String toString()
  {
	  String retStr=name;
	  for (int i=0;i<9;i++)
		  retStr=retStr+String.format("%1$-4d", holes[i]);
	  retStr=retStr+String.format("%1$-6d", total)+String.format("%1$+6d",total-dest.total);
	  return retStr;
  }
  
  public String getName()
  {
	  return this.name;
  }
  
  public int totalStrokes()
  {
	  return total;
  }
  
  public int[] getHoles()
  {
	  return this.holes;
  }
  
  public void setPar(Golfer arg)
  {
	  this.dest=arg;
  }
}
