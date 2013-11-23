package skiplist;

import java.io.PrintStream;

public class I_Skiplists<T> implements Skiplists<T>{
	private boolean empFlag=true;
	private int nodeCount=0;
	@SuppressWarnings("rawtypes")
	Slnode head,t;
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void refreshNode(Slnode pre,Slnode t)
    {
    	//refresh and insert the node.
		t.p[0]=pre.p[0];
    	t.index=pre.index+1;
    	pre.p[0]=t;
    	Slnode rfHead=t.p[0];
    	while (rfHead!=null)
    	{
    		rfHead.index++;
    		rfHead=rfHead.p[0];
    	}
    	
    	//refresh and maintain the node list.
    	Slnode tmph=head;
    	do
    	{
    		boolean flag=false;
    		int bak=tmph.pCount;
    		if (tmph.index%2==0)
    			try
    		{
    			  tmph.p[1]=tmph.p[0].p[0];
    			  tmph.pCount=2;
    			  flag=true;
    			  
    		}
    		catch (Exception e)
    		{
    			tmph.p[1]=null;
    			if (flag==false) tmph.pCount=bak;
    		}
    		
    		if (tmph.index%4==0)
    			try
    		{
    		      tmph.p[2]=tmph.p[0].p[0].p[0].p[0];
    		      tmph.pCount=3;
    		      flag=true;
    		      
    		}
    		catch (Exception e)
    		{
    			tmph.p[2]=null;
    			if (flag==false) tmph.pCount=bak;
    		}
    		
    		if (tmph.index%4!=0 && tmph.index%2!=0)
    			tmph.pCount=1;
    		tmph=tmph.p[0];
    	}
    	while (tmph!=null);
    }
	
    
	@SuppressWarnings("rawtypes")
	private Slnode findPosition(Object val,Slnode nowP,int level)
	{
		if (level==1) 
				{
	              if (nowP.index==nodeCount-1)                                    //The last point.
	            	  return nowP; 
	            	  else if (nowP.val.toString().compareTo(val.toString())<0 && (  nowP.p[level-1].val.toString().compareTo(val.toString())>0 || nowP.p[level-1].val.toString().equals(val.toString())|| nowP.p[level-1]==null ))
	            	          return nowP;                                        //Found the exact point to insert.
	            	  else return findPosition(val,nowP.p[level-1],level);        //Continue to search next point.				
				}
		else
		{
			//System.out.println("node count:"+(nodeCount-1));
			if (nowP.index==nodeCount-1)                                          //The last point.
				return nowP;
			else if (nowP.val.toString().compareTo(val.toString())<0 && (nowP.p[level-1]==null || nowP.p[level-1].val.toString().equals(val.toString()) || nowP.p[level-1].val.toString().compareTo(val.toString())>0))                    //To a low level.
				return findPosition(val,nowP,level-1);
			else return findPosition(val,nowP.p[level-1],level);                  //Continue to next point in this level.
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void insert(Object val) {

		if (nodeCount==0)
		{
			//so head is 0;
			t=new Slnode(val,nodeCount);
			empFlag=false;
			nodeCount++;
			head=t;
		}
		else
		{
		  t=new Slnode(val,nodeCount);
		  
		  Slnode ind=findPosition(val,head,head.pCount);
		  nodeCount++;
		 // System.out.println("****Insert"+val+" @pos:"+ind.index+" after insert:"+nodeCount);
		  refreshNode(ind,t);
		}
		
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(int index) throws MyException {
		if (index<0 || index>nodeCount-1) 
		{
			throw new MyException("invalid delete request.");
		}
		Slnode tmph=head;
		for (int i=0;i<index-1;i++)
			tmph=tmph.p[0];
		tmph.p[0]=tmph.p[0].p[0];
		nodeCount--;
		
		tmph=head;int i=-1;
    	do
    	{
    		boolean flag=false;
    		i++;
    		tmph.index=i;
    		int bak=tmph.pCount;
    		if (tmph.index%2==0)
    			try
    		{
    			  tmph.p[1]=tmph.p[0].p[0];
    			  tmph.pCount=2;
    			  flag=true;
    			  
    		}
    		catch (Exception e)
    		{
    			tmph.p[1]=null;
    			if (flag==false) tmph.pCount=bak;
    		}
    		
    		if (tmph.index%4==0)
    			try
    		{
    		      tmph.p[2]=tmph.p[0].p[0].p[0].p[0];
    		      tmph.pCount=3;
    		      flag=true;
    		      
    		}
    		catch (Exception e)
    		{
    			tmph.p[2]=null;
    			if (flag==false) tmph.pCount=bak;
    		}
    		
    		if (tmph.index%4!=0 && tmph.index%2!=0)
    			tmph.pCount=1;
    		tmph=tmph.p[0];
    	}
    	while (tmph!=null);
		
	}

	public int search(Object val) {
		
		int p=findPosition(val,head,head.pCount).index+1;
		if (p==nodeCount)
			return -1;  //not found.
		else
			return p;   //found, return pos.
	}


	public boolean isEmpty() {
		
		return empFlag;
	}


	@SuppressWarnings("rawtypes")
	public void show(PrintStream p) {
		System.out.println();
		System.out.println("====================Node Count:"+nodeCount+"========================");
		Slnode tmph=head;
		do
		{
			p.print("NO."+tmph.index+" ");
			p.print("PCount:"+tmph.pCount+" val:"+tmph.val+" next:");
			for (int i=1;i<=tmph.pCount;i++)
		      if (tmph.p[i-1]!=null) 
		    	  p.print(tmph.p[i-1].index+" ");
			p.println();
			tmph=tmph.p[0];
		}
		while (tmph!=null);
	
		
	}
	
	

}
