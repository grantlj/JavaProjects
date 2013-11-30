import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LibSystem {
  public static String fileName="test_input.txt";
  
  private static boolean isValidMenuChoice(char c)
  {
	  if (c=='i' || c=='r' || c=='b' || c=='u' || c=='f')
		  return true;
	  else 
		  return false;
  }
  
  public static void showMenu()
  {
	System.out.println();
	System.out.println("Function Menu");
	System.out.println("===========================");
    System.out.println("i-Issue a book to user.");
	System.out.println("r-Return a book to system.");
	System.out.println("b-Display book information.");
	System.out.println("u-Display user information.");
	System.out.println("f-Save and exit.");
	Scanner sc=new Scanner(System.in);
	System.out.print("Choose a function to coutinue:i/r/b/u/f.");

	String sel=sc.nextLine();
	char c=sel.charAt(0);
	
	while (!isValidMenuChoice(c))
	{
		System.out.print("Choose a function to coutinue:i/r/b/u/f.");
        sel=sc.nextLine();
	    c=sel.charAt(0);
	}
	
	switch (c)
	{
	case 'i':
		issueBook();
		break;
	case 'r':
		returnBook();
		break;
	case 'b':
	    displayBook();
	    break;
	case 'u':
		displayUser();
		break;
	case 'f':
		exit();
		break;
	}
	
	
  }

  
  private static void exit() {
	// TODO Auto-generated method stub
	System.exit(0);
	
}

private static void displayUser() {
	// TODO Auto-generated method stub
	System.out.println("User information:");
	System.out.println("==================");
	UserHandler.showUserInfo();
	showMenu();
	
}

private static void displayBook() {
	// TODO Auto-generated method stub
	System.out.println("Book information:");
	System.out.println("===================");
	AuthHandler.showBookInfo();
	showMenu();
	
}

private static void returnBook() {
	// TODO Auto-generated method stub
	System.out.println("Return book:");
	System.out.println("===================");
	
	Scanner sc=new Scanner(System.in);
	String bookName;
	String auName;
	String userName;
	System.out.print("Enter the user name:");
    userName=sc.nextLine();
    System.out.print("Enter the book name:");
    bookName=sc.nextLine();
    System.out.print("Enter the book's author name:");
    auName=sc.nextLine();
    if (!UserHandler.checkUserUnissue(userName))
    	System.out.println("User is not valid!(Wrong name or holding no book)");
    else
    {
    	String ret=AuthHandler.checkBook(bookName,auName);
    	if (ret.equals(userName))
    	{
    		//Do return;
    		 AuthHandler.unIssue(bookName,auName,userName);
             UserHandler.unIssue(bookName,auName,userName);
             System.out.println("Return book:"+bookName+" ,Author:"+auName+" by:"+userName+" successfully...");
    	}
    	else
    	  if (ret.equals("AVAIL"))
    	  {
    		  //The book is not issued.
    		  System.out.println("Book:"+bookName+" ,Author:"+auName+" has not been issued.");
    	  }
    	  else
    	  {
    		//Book info is not valid.
  			System.out.println("Book:"+bookName+" ,Author:"+auName+" is not valid...");
    	  }
    }
	showMenu();
}

private static void issueBook() {
	// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	System.out.println("Issue book:");
	System.out.println("===================");
	String bookName;
	String auName;
	String userName;
	System.out.print("Enter the user name:");
    userName=sc.nextLine();
    System.out.print("Enter the book name:");
    bookName=sc.nextLine();
    System.out.print("Enter the book's author name:");
    auName=sc.nextLine();
    if (!UserHandler.checkUserIssue(userName))
    	System.out.println("User is not valid!(Wrong name or already holding 3 books)");
    else
    {
    	String ret=AuthHandler.checkBook(bookName,auName);
    	if (ret.equals("AVAIL"))
    	{
    		//Do issue.
    		System.out.println("Issue book:"+bookName+" ,Author:"+auName+" to:"+userName+" successfully...");
            AuthHandler.issue(bookName,auName,userName);
            UserHandler.issue(bookName,auName,userName);
    	}
    	else
    	{
    		if (ret.equals("NOT VALID"))
    		{
    			//Book info is not valid.
    			System.out.println("Book:"+bookName+" ,Author:"+auName+" is not valid...");
    		}
    		else 
    		{
    			//IT HAS BEEN ISSUED.
    			System.out.println("Book:"+bookName+" ,Author:"+auName+" has been issued by:");
    			System.out.println(ret);
    			System.out.println("Please wait for his return...");
    		}
    	}
    }
    showMenu();
}

private static void loadFromFile() throws FileNotFoundException
{
	File file=new File(fileName);
	Scanner sc=new Scanner(file);
    int bookCount=sc.nextInt();
    sc.nextLine();
    for (int i=0;i<bookCount;i++)
    {
    	String line1=sc.nextLine();
    	String line2=sc.nextLine();
    	AuthHandler.newInfo(line1, line2);
    }
    int usrCount=sc.nextInt();
    sc.nextLine();
    for (int i=0;i<usrCount;i++)
    {
    	String line1=sc.nextLine();
    	UserHandler.newInfo(line1);
    }
    sc.close();
	
}

public static void main(String[] args) 
  {
	 try {
		loadFromFile();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	showMenu();
  }
}
