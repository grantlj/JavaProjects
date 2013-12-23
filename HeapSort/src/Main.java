import java.util.Scanner;


public class Main {
  public static final int max=100;
  public static int[] inp=new int[max+1];
  public static int[] heap=new int[max+1];
  public static int[] ans=new int[max+1];
  private static int n;
  
  private static void insertVal(int p,int x,int[] val)
  {
	  val[p]=x;
	  
	  while (p/2>=1 && val[p/2]<val[p])
	  {
		  int t;
		  t=val[p];val[p]=val[p/2];val[p/2]=t;
		  p=p/2;
	  }
	  
  }
  
  private static int getHeapTop(int[] heap)
  {
	  return heap[1];
  }
  
  private static void showVal(int[] heap)
  {
	int count=0;
	boolean flag=false;
	for (int level=0;;level++)
	{
		for (int i=(int) Math.pow(2, level);i<=(int) Math.pow(2, level+1)-1;i++)
		{
			System.out.print(heap[i]+" ");
			count++;
			if (count==n)
			{
				flag=true;
				break;
			}
		}
		System.out.println();
		if (flag)
			 break;
	}
  }
  private static void createMaxHeap(int[] val)
  {
	  heap[1]=val[1];
	  for (int i=2;i<=n;i++)
	     insertVal(i,val[i],heap);
  }
  
  private static int getMaxPos(int p,int heap[], int size)
  {
	  if (p*2<=size)
	  {
		  if (p*2+1<=size)
			  if (heap[p*2]>=heap[p*2+1]) 
				  return p*2;
			  else 
				  return p*2+1;
		  else 
			  return p*2;
	  }
	  else
	     return 0;
  }
  
  private static int[] adjustHeap(int[] heap,int heapSize)
  {
     
     int p=1;
     
     while (p<=heapSize)
     {
    	 int des=getMaxPos(p,heap,heapSize);
    	 if (des==0)
    		 break;
    	 else if (heap[p]<heap[des])
    	 {
    		 int t2;
    		 t2=heap[p];heap[p]=heap[des];heap[des]=t2;
    		 p=des;
    	 }
    	 else
    		  break;
     }
     return heap;
     
  }
  
  public static void main(String[] args)
  {

	  n=new Scanner(System.in).nextInt();
	  
	  for (int i=1;i<=n;i++)
		  inp[i]=new Scanner(System.in).nextInt();  
	  
	  createMaxHeap(inp);
	  ans[0]=getHeapTop(heap);
	  
	  int heapSize=n;
	  
	  for (int i=0;i<n;i++)
	  {
		  showVal(heap);
		  ans[i]=getHeapTop(heap);
		  System.out.println("------------");
		  System.out.println();
		  int t=heap[1];heap[1]=heap[heapSize];heap[heapSize]=t;
		  heapSize--;
		  heap=adjustHeap(heap,heapSize);
		 
		  
	  }
	 
	  System.out.println("Sorted:");
	  for (int i=0;i<15;i++)
		  System.out.print(ans[i]+" ");
	  System.out.println();
	  
	  
  }
  
}
