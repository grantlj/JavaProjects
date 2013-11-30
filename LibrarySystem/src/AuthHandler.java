
public class AuthHandler {
	public static int AuthCount=0;
	public static int AuthMax=100;
    public static  SortedArrayList<AuthorInfo> s=new SortedArrayList<AuthorInfo>(100);
	
    public static void newInfo(String line1, String line2)
	{
		  String auSur,auFir;
		  String bookName=line1;
		  int p=line2.lastIndexOf(' ');
		  auSur=line2.substring(p+1);
		  auFir=line2.substring(0,p);
		  boolean isNewAuth=true;
		  int authIndex=-1;
		 
		  for (int i=0;i<s.size();i++)
			  if (s.get(i).auFir.equals(auFir) && s.get(i).auSur.equals(auSur))
			  {
				  isNewAuth=false;
				  authIndex=i;
				  break;
			  }
		 
		  if (!isNewAuth)
			  s.get(authIndex).addBookToOwner(bookName);
		  else
		  {
			  AuthCount++;
			  AuthorInfo tmp=new AuthorInfo(auSur,auFir);
			  tmp.addBookToOwner(bookName);
			  s.insert(tmp);
		  }
	}
	
	public static void showBookInfo()
	{
		for (int i=0;i<s.size();i++)
		{
			System.out.println("Author:"+s.get(i).auFir+" "+s.get(i).auSur);
			for (int j=0;j<s.get(i).bookCount;j++)
			{
				System.out.println("NO."+(j+1)+":"+s.get(i).book[j]);
				System.out.println("State:"+s.get(i).owner[j]);
				System.out.println();
			}
			
		}
	}
	
	public static String checkBook(String bookName, String auName)
	{
		for (int i=0;i<s.size();i++)
		{
			if (auName.equals(s.get(i).auFir+" "+s.get(i).auSur))
			{
				for (int j=0;j<s.get(i).bookCount;j++)
				  if ((s.get(i).book[j]).equals(bookName))
					  return s.get(i).owner[j];
			}
		}
		return "NOT VALID";
	}
	
	public static void issue(String bookName,String auName,String userName)
	{
		int p=0;
		for (int i=0;i<s.size();i++)
			if (auName.equals(s.get(i).auFir+" "+s.get(i).auSur))
			{
				p=i;
				break;
			}
		for (int i=0;i<s.get(p).bookCount;i++)
			if (s.get(p).book[i].equals(bookName))
			  s.get(p).owner[i]=userName;
		
	}
	
	public static void unIssue(String bookName,String auName, String userName)
	{
		int p=0;
		for (int i=0;i<s.size();i++)
			if (auName.equals(s.get(i).auFir+" "+s.get(i).auSur))
			{
				p=i;
				break;
			}
		for (int i=0;i<s.get(p).bookCount;i++)
			if (s.get(p).book[i].equals(bookName))
			  s.get(p).owner[i]="AVAIL";
	}
	

}
