package core.grant;

public class SuffixCalc {
    private String mid;
    @SuppressWarnings("unused")
	private int mode;                             //1 is integer;2 is double
    private double[] nums;
    public static final int numsMax=100;
    private int numsCount;
    private boolean WA=false;
    
    private static final char[] opSet=new char[]{'+','-','*','/'};
    
    private boolean isOp(char x)
    {
    	for (int i=0;i<opSet.length;i++)
    		if (x==opSet[i])
    			return true;
    	return false;
    }
    
    private void calc()
    {
    	numsCount=0;
    	double tmpInt=0,tmpFloat=0;
    	int tmpFloatCount=0;
    	boolean isInt=true;
    	boolean negativeFlag=false;
    	
    	for (int i=0;i<mid.length();i++)
    	{
    		if (mid.charAt(i)>='0' && mid.charAt(i)<='9')
           {
        	  if (isInt)
        		  tmpInt=tmpInt*10+mid.charAt(i)-'0';
        	  else
        	  {
        		  tmpFloatCount++;
        		  double bit=mid.charAt(i)-'0';
        		  for (int j=0;j<tmpFloatCount;j++)
        			  bit/=10;
        		  tmpFloat+=bit;
        	  }
           }
    	   
    	   if (mid.charAt(i)=='.')
    	   {
    		   tmpFloatCount=0;
    		   tmpFloat=0;
    		   isInt=false;
    	   }
    	   
    	   if (mid.charAt(i)==' ')
    	   {
    		   if (!negativeFlag)
    			   addNumberToStack(tmpInt+tmpFloat);
    		   else
    			   addNumberToStack(-(tmpInt+tmpFloat));
    		   
    		   tmpInt=0;tmpFloat=0;
    		   tmpFloatCount=0;
    		   isInt=true;
    		   negativeFlag=false;
    	   }
    	   
    	   if (isOp(mid.charAt(i)))
    	   {
    		   if (mid.charAt(i)=='-' && (i==0 || mid.charAt(i-1)==' '))
    				   negativeFlag=true;
    		   else
    		   refreshStackbyOperators(mid.charAt(i));
    		   
    	   }
    	}
    }
    
    private void addNumberToStack(double x)
    {
    	nums[numsCount++]=x;
    }
    
    private void refreshStackbyOperators(char x)
    {
    	double num1=nums[numsCount-1];
    	double num2=nums[numsCount-2];
    	numsCount-=2;
    	double t=0;
    	
    	switch (x)
    	{
    	case '+':
    		t=num2+num1;
    		break;
    	case '-':
    		t=num2-num1;
    		break;
    	case '*':
    		t=num2*num1;
    		break;
    	case '/':
    		t=num2/num1;
    		break;
    	}
    	nums[numsCount++]=t;
        
    }
    
	public SuffixCalc(String mid,int mode)
	{
		System.out.println("mid is:"+mid);
		nums=new double[numsMax];
		this.mid=mid;
		this.mode=mode;
		if (!mid.equals("Syntax Error"))
		try
		{
			calc();
		}
	    catch (Exception e)
	    {
	    	WA=true;
	    }
		else 
		  WA=true;
		  
	}
	
	public String getAns()
	{
		if (WA) return mid;
				
		else if (numsCount!=1)
			return "Syntax Error";
		else
			return String.valueOf(nums[0]);
	}
}
