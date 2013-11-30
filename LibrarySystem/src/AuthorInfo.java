/*AuthorInfo.java
 * =========================================
 * AuthorInfo is the basic element of the 
 * SortedArrayList for authors.
 */

class AuthorInfo{
  public String auSur;
  public String auFir;
  public static int bookMax=100;
  public int bookCount;
  public String book[]=new String[bookMax];
  public String owner[]=new String[bookMax];
  
  /*Constructor:
	 * initial the element.
	 */
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
  
  /*addBookToOwner(String)
   * to assign one of the author's book to 
   * a new user.
   */
  public void addBookToOwner(String bookname)
  {
	  if (bookCount+1<=bookMax)
	  {
		  book[bookCount]=bookname;
		  bookCount++;
	  }
  }
  
  /*toString()
	 *We override it, to return the surname
	 *, in order to sort.
	 */
 @Override
  public String toString() {
	return this.auSur;
}
  
  
}
