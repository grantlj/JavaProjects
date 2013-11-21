/*Project Name: Time Arranger
 *Version     : 1.0
 *Author:       Grant Liu
 *              Deng Jie
 *      
 *History     :
 *Version 1.0   2013/11/17     *Finish basic functions. 
 *
 *Brief Introduction:
 *   Time Arranger is a program to simplify the process 
 *   of arranging the time for lecture high efficiently.
 *   The program is based on C/S frame, with the tech of
 *   Ftp and Servlet in Java language.   
 */

/*Module Name: infos
 * Version   : 1.0
 * Brief Introduction:
     infos stores the basic Time info and users info, which
     is final decorated.
 */

/*TimeSets
 * This class store 6 time interval on weekends, which
 * is probably for lecturer and users to choose.
 */
class TimeSets{
	static int SAT_NOON=1;static int SUN_NOON=4;
	static int SAT_ANOO=2;static int SUN_ANOO=5;
	static int SAT_EVEN=3;static int SUN_EVEN=6;
	public final static String[] weekDay=new String[7];
	static
	{
		weekDay[SAT_NOON]="Saturday 8 am - 12 am";
		weekDay[SAT_ANOO]="Saturday 14 pm - 18 pm";
		weekDay[SAT_EVEN]="Saturday 19 pm - 22 pm";
		weekDay[SUN_NOON]="Sunday 8 am - 12 am";
		weekDay[SUN_ANOO]="Sunday 14 pm - 18 pm";
		weekDay[SUN_EVEN]="Sunday 19 pm - 22 pm";
		
	}
}

/*MemberSets()
 * The class store 12 members of App group.
 * They MUST use their name and password(stu id) to
 * login the program and ftp server.
 */
class MemberSets{
	final static String[] Name=new String[12];
    final static String[] Pwd=new String[12];
	//private final static String[] Tel=new String[12];
	static 
	{
		Name[0]="admin";           Pwd[0]="ADMIN";              
		Name[1]="Liu Jiang";       Pwd[1]="2013210294";         
		Name[2]="Qiao Nan";        Pwd[2]="2013211453";         
		Name[3]="Gu Ruiqin";       Pwd[3]="2013214104";
		Name[4]="Deng Jie";        Pwd[4]="2013213733";
		Name[5]="Wang Jingjing";   Pwd[5]="2013212028";
		Name[6]="Wang Hexing";     Pwd[6]="2013211619";
		Name[7]="Quan Meng";       Pwd[7]="2013210261";
		Name[8]="Yan Youyu";       Pwd[8]="2013210315";
		Name[9]="Huang Ying";      Pwd[9]="2013213929";
		Name[10]="Hao Yu";         Pwd[10]="2013213808";
		Name[11]="Zhai Dongyan";   Pwd[11]="2013210102";
	}
	
	/*inNameList()
	 * To check whether a user is valid.
	 * x is user name.
	 * y is user password.
	 */
	public boolean inNameList(String x,String y)
	{
		boolean bool=false;
		for (int i=0;i<12;i++)
			if (Name[i].toUpperCase().equals(x.toUpperCase()) && Pwd[i].equals(y.toUpperCase())) 
			{
				bool=true;
				break;
			}
		return bool;
	}
}
public class infos {

}
