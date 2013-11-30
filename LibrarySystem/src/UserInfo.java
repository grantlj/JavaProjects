/*UserInfo.java
 * =========================================
 * UserInfo is the basic element of the 
 * SortedArrayList for users.
 */

public class UserInfo {
	public String usrSur;
	public String usrFir;
	public static final int holdMax=3;
	public String book[]=new String[holdMax];
	public String bookau[]=new String[holdMax];
	public int holding=0;

	/*Constructor:
	 * initial the element.
	 */
	public UserInfo(String usrSur, String usrFir)
	  {
		  this.usrSur=usrSur;
		  this.usrFir=usrFir;
		  for (int i=0;i<holdMax;i++)
		  {
			  book[i]="";
			  bookau[i]="";
		  }
	  }
	
	/*toString()
	 *We override it, to return the surname and
	 *first name, in order to sort.
	 */
	@Override
	  public String toString() {
		return this.usrSur+this.usrFir;
	}
}
