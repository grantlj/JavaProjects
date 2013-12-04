/*
 * Filename: MidToSuffix.java
 * Description: This file is to change a infix expression to
 *              a suffix one and execute a primary syntax check.
 */
package core.grant;

public class MidToSuffix {
    
	//the mid and Suffix is to store the infix expression and the suffix of it.
	String mid;
    String Suffix;
    
    //the opStack is a Stack to store the operators.
    char[] opStack;
    private int opCount;
    
    //We need two sets numSet and opSet to define the digits and operators.
    private static final char[] numSet=new char[]{'0','1','2','3','4','5','6','7','8','9','.'};
    private static final char[] opSet=new char[]{'+','-','*','/'};
    
    //opsMax is just to define the size of opStack;
    private static final int opsMax=1000;
    
    //tmpNum is a temporary variable to store a independent number.
    private String tmpNum;
    
	/*Syntax check, to check whether the left parentheses and right 
     *parentheses match well or not.
     *For each left parentheses we add count; for the right we decrease
     *the count.
     *Finally, the count should be equal to 0.
     */
	private static boolean checkParentheses(String s)
	{
		int count=0;
		for (int i=0;i<s.length();i++)
		{
			if (s.charAt(i)=='(')
				count++;
			else if (s.charAt(i)==')')
			{
				count--;
				if (count<0) return false;
			}
		}
		return (count==0);
    }

    // To tell whether a character is a digit.
	
    private boolean isDigit(char x)
    {
    	for (int i=0;i<numSet.length;i++)
    	  if (x==numSet[i])
    		  return true;
    	return false;
    }
    
    
    // To tell whether a character is an operator.
    private boolean isOp(char x)
    {
    	for (int i=0;i<opSet.length;i++)
    		if (x==opSet[i])
    			return true;
    	return false;
    }
    
    // To add a number to the suffix expression.
    private void addNumber(String num)
    {
    	Suffix=Suffix+num+" ";
    }
    
    //To add an operator to the suffix expression.
    private void addOperator(char x)
    {
    	Suffix=Suffix+x;
    }
    
    /*In order to operate the stack of operators, we 
     *always need to compare the former operator with
     *the top one of the stack.
     */
    
    private int checkPrivilege(char a,char b)
    {
    	int PriA=0,PriB=0;
    	if (a=='+' || a=='-') PriA=1;
    	if (a=='*' || a=='/') PriA=2;
    	if (b=='+' || b=='-') PriB=1;
    	if (b=='*' || b=='/') PriB=2;
    	return PriA-PriB;
    }
    
    /*If the former operator is lower than the top one, 
     * we need to pop the stack.
     * Finally, we must push the former one into the
     * stack.
     */
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
    
    /*If we get an ) in stack, we need to pop the 
     * stack until we meet the (. All the elements 
     * should be send to the Suffix.
     */
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
    
    /*Clear the stack and add the left operators 
     * into the Suffix.
     */
    private void clearOpStack()
    {
  
    	while (opCount>0)
    	{
    		addOperator(opStack[opCount-1]);
    		opStack[opCount-1]='\0';
    		opCount--;
    	}
    }
    
    /*The main method to transfer a infix expression
     * to an suffix one.
     */
    private void doTransfer()
    {
    	//clear the stack.
    	opCount=0;
    	
    	//the variable numing is a flag to determine whether former character
    	//is a part of number or not.
    	boolean numing=false;
    	tmpNum="";
    	for (int i=0;i<mid.length();i++)
    	{
    		if (isDigit(mid.charAt(i)))
    		{
    			//We caught a digit, add it to tmpNum.
    			tmpNum=tmpNum+mid.charAt(i);
    			if (!numing) 
    				//If it is the first digit of a number, change the flag.
    				numing=true;
    		}
    		else
    		{
    			//We caught a operator or parentheses....
    			if (numing)
    			{
    				//change the flag, if the last one is digit.
    				numing=false;
    				
    				//then put the number to the suffix expression
    				//and clear tmpNum.
    				addNumber(tmpNum);
    				//System.out.println("adding:"+tmpNum);
    				tmpNum="";
    			}
    			
    			char x=mid.charAt(i);
    			
    			if (isOp(x))
    			{
    				//this is to check the coincidence of negative number.
    				if (x=='-' && (i==0 || isOp(mid.charAt(i-1)) || mid.charAt(i-1)=='('))
    				{
    		            //find negative number.
    					numing=true;
    		            tmpNum="-";
    				}
    				
    				else
    				    //the real operator, just add it to stack.
    					refreshOpStackbyOperators(x);
    			
    			}
    			if (x=='(')
    				if (i==0 || isDigit(mid.charAt(i-1)))
    				   opStack[opCount++]=x;
    				else 
    				{
    					//Invalid! We can never put an ( after a digit.
    					Suffix="Syntax Error";
                        break;
    				}
    			if (x==')')
    				//Caught the right parentheses, pop up the stack.
    				refreshOpStackbyRight();
    			
    			}		
    		}
    	
    	//To adding the last number to suffix expression.
    	if (numing)
    	{
    		numing=false;
    		addNumber(tmpNum);
    		//System.out.println("adding:"+tmpNum);
    		tmpNum="";
    	}
    	
    	//Clear the stack, although it seems no need to do so.
    	clearOpStack();
    	
    }
	
    //The constructor.
    public MidToSuffix(String s)
	{
		this.mid=s;
		opStack=new char[opsMax];
		this.Suffix="";
		if (checkParentheses(s))
		doTransfer();
		else 
			Suffix="Syntax Error";
	}
    
    //The method to get the suffix expression.
    public String getSuffix()
    { 
    	return Suffix;
    }
}
