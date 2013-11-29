

class AuthorInfo{
  public String auSur;
  public String auFir;
  public static int bookMax=100;
  public int bookCount;
  public String book[]=new String[bookMax];
  public String owner[]=new String[bookMax];
  
  public AuthorInfo(String auSur, String auFir)
  {
	  this.auSur=auSur;
	  this.auFir=auFir;
	  for (int i=0;i<bookMax;i++)
	  {
		  book[i]="";
		  owner[i]="AVAIL";
	  }
  }
  
  public void addBookToOwner(String bookname)
  {
	  if (bookCount+1<=bookMax)
	  {
		  book[bookCount]=bookname;
		  bookCount++;
	  }
  }

 @Override
  public String toString() {
	return this.auSur;
}
  
  
}
