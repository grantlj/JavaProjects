/*UserHandler.java
 * =========================================
 * The main class to handle user.
 */
public class UserHandler {
	public static int UserCount=0;
	public static int UserMax=100;
    public static  SortedArrayList<UserInfo> s=new SortedArrayList<UserInfo>(100);
    
    /*newInfo(String)
     * handle a new user, get his surname and 
     * first name, then save to UserInfo.
     */
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
    
   /*showUserInfo()
    * print out users' information to screen.
    */
   public static void showUserInfo()
   {
	for (int i=0;i<s.size();i++)
	{
		System.out.println("Username:"+s.get(i).usrFir+" "+s.get(i).usrSur);
		System.out.println("=======Book Info:========");
		for (int j=0;j<s.get(i).holding;j++)
		{
			System.out.println("NO."+(j+1)+": Book name:"+s.get(i).book[j]);
			System.out.println("Author:"+s.get(i).bookau[j]);
			System.out.println();
		}
		System.out.println();
	}
   }
   
   /*checkUserIssue(String)
    * to check whether a user can issue a book.
    */
	public static boolean checkUserIssue(String userName)
	{
	  for (int i=0;i<s.size();i++)
		  if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur) && s.get(i).holding<UserInfo.holdMax)
			  return true;
	  return false;
	}
	
	/*checkUserUnissue(String)
	 * to check whether a user can unissue a book.
	 */
	public static boolean checkUserUnissue(String userName)
	{
	  for (int i=0;i<s.size();i++)
		  if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur) && s.get(i).holding>=1)
			  return true;
	  return false;
	}
	
	/*issue(String,String,String)
	 * to issue a book to an user and refresh his info.
	 */
	public static void issue(String bookName,String auName,String userName)
	{
		int p=0;
		for (int i=0;i<s.size();i++)
			if (userName.equals(s.get(i).usrFir+" "+s.get(i).usrSur))
			{
				p=i;
				break;
			}
	
		s.get(p).book[s.get(p).holding]=bookName;
		s.get(p).bookau[s.get(p).holding]=auName;
		s.get(p).holding++;
	}
	
	/*unIssue(String,String,String)
	 * to UNissue a book to an user and refresh his info.
	 */
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
