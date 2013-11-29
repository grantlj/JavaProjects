
public class UserHandler {
	public static int UserCount=0;
	public static int UserMax=100;
    public static  SortedArrayList<UserInfo> s=new SortedArrayList<UserInfo>(100);
    public static void newInfo(String line1)
 	{
 		  String usrSur,usrFir;
 		  int p=line1.lastIndexOf(' ');
 		  usrSur=line1.substring(p+1);
 		  usrFir=line1.substring(0,p);
 		  UserInfo tmp=new UserInfo(usrSur,usrFir);
 		  s.insert(tmp);
 		  UserCount++;
 	}
    
   public static void showUserInfo()
   {
	for (int i=0;i<s.size();i++)
	{
		System.out.println("Username:"+s.get(i).usrFir+" "+s.get(i).usrSur);
	}
   }
   
	public static boolean checkUser(String userName)
	{
	  for (int i=0;i<s.size();i++)
		  if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur) && s.get(i).holding<UserInfo.holdMax)
			  return true;
	  return false;
	}
	
	public static void issue(String bookName,String auName,String userName)
	{
		int p=0;
		for (int i=0;i<s.size();i++)
			if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur))
			{
				p=i;
				break;
			}
		s.get(p).holding++;
		s.get(p).book[s.get(p).holding]=bookName;
		s.get(p).bookau[s.get(p).holding]=auName;
	}
	
	public static void unIssue(String bookName,String auName, String userName)
	{
		int p=0;
		for (int i=0;i<s.size();i++)
			if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur))
			{
				p=i;
				break;
			}
		int p2=0;
		for (int i=0;i<s.get(p).holding;i++)
			if (s.get(p).book[i].equals(bookName) && s.get(p).bookau[i].equals(auName))
			{
				p2=i;
				break;
			}
		for (int i=p2;i<UserInfo.holdMax-1;i++)
			s.get(p).book[i]=s.get(p).book[i+1];
		s.get(p).holding--;
	}

		  
}
