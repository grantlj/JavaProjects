package core.grant;

public class MidToSuffix {
    String mid;
    String Suffix;
    char[] opStack;
    private int opCount;
    
    private static final char[] numSet=new char[]{'0','1','2','3','4','5','6','7','8','9','.'};
    private static final char[] opSet=new char[]{'+','-','*','/'};
    
    private static final int opsMax=1000;
    
    private String tmpNum;

    
    private boolean isDigit(char x)
    {
    	for (int i=0;i<numSet.length;i++)
    	  if (x==numSet[i])
    		  return true;
    	return false;
    }
    
    private boolean isOp(char x)
    {
    	for (int i=0;i<opSet.length;i++)
    		if (x==opSet[i])
    			return true;
    	return false;
    }
    
    private void addNumber(String num)
    {
    	Suffix=Suffix+num+" ";
    }
    
    private void addOperator(char x)
    {
    	Suffix=Suffix+x;
    }
    
    private int checkPrivilege(char a,char b)
    {
    	int PriA=0,PriB=0;
    	if (a=='+' || a=='-') PriA=1;
    	if (a=='*' || a=='/') PriA=2;
    	if (b=='+' || b=='-') PriB=1;
    	if (b=='*' || b=='/') PriB=2;
    	return PriA-PriB;
    }
    
    private void refreshOpStackbyOperators(char x)
    {
    	for (int i=opCount-1;i>=0;i--)
    		if (checkPrivilege(x,opStack[i])<=0)
    		{
    			addOperator(opStack[i]);
    			opStack[i]='\0';
    			opCount--;
    		}
    		else
    			break;
    	opStack[opCount]=x;
    	opCount++;
    	
    }
    
    private void refreshOpStackbyRight()
    {
       int p=opCount-1;
       while (opStack[p]!='(')
       {
    	   addOperator(opStack[p]);
    	   opStack[p]='\0';
    	   opCount--;
    	   p--;
       }
       opStack[p]='\0';
       opCount--;
       
    }
    
    private void clearOpStack()
    {
  
    	while (opCount>0)
    	{
    		addOperator(opStack[opCount-1]);
    		opStack[opCount-1]='\0';
    		opCount--;
    	}
    }
    
    private void doTransfer()
    {
    	opCount=0;
    	boolean numing=false;
    	tmpNum="";
    	for (int i=0;i<mid.length();i++)
    	{
    		if (isDigit(mid.charAt(i)))
    		{
    			tmpNum=tmpNum+mid.charAt(i);
    			if (!numing) 
    				numing=true;
    		}
    		else
    		{
    			if (numing)
    			{
    				numing=false;
    				addNumber(tmpNum);
    				tmpNum="";
    			}
    			
    			char x=mid.charAt(i);
    			
    			if (isOp(x))
    				refreshOpStackbyOperators(x);
    			
    			if (x=='(')
    				opStack[opCount++]=x;
    			
    			if (x==')')
    				refreshOpStackbyRight();
    			
    				
    		}
    	}
    	
    	if (numing)
    	{
    		numing=false;
    		addNumber(tmpNum);
    		tmpNum="";
    	}
    	
    	clearOpStack();
    	
    }
	
    public MidToSuffix(String s)
	{
		this.mid=s;
		opStack=new char[opsMax];
		this.Suffix="";
		doTransfer();
	}
    
    public String getSuffix()
    { 
    	return Suffix;
    }
}
