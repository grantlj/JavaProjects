
public class UserInfo {
	public String usrSur;
	public String usrFir;
	public static final int holdMax=3;
	public String book[]=new String[holdMax];
	public String bookau[]=new String[holdMax];
	public int holding=0;
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
	@Override
	  public String toString() {
		return this.usrSur+this.usrFir;
	}
}
