package skiplist;

public class Slnode<T> {
  public Object val;
  public int pCount;
  public static int pMaxCount=3;
  public int index;
  @SuppressWarnings("rawtypes")
public Slnode[] p;
  
  private void generatePInfo(int index)
  {
	  for (int i=0;i<pMaxCount;i++)
		 p[i]=null;
	  if (index%4==0)
		  pCount=3;
	  else if (index%2==0)
	      pCount=2;
	  else
		  pCount=1;
		
  }
  Slnode(Object val, int index)
  {
	  this.val=val;
	  this.index=index;
	  p=new Slnode[pMaxCount];
	  generatePInfo(index);
  }
}
