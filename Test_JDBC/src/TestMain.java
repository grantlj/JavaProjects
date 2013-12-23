import java.sql.*;

public class TestMain {
  public static void main(String[] args)
  {
	  String driverName="com.mysql.jdbc.Driver";
	  String userName="root";
	  String userPasswd="940414";
	  String dbName="xinxibu";
	  String tableName="leaders";
	  String url="jdbc:mysql://localhost/";
	  
	  try
	  {
	    Class.forName(driverName).newInstance();
	    Connection connection=DriverManager.getConnection(url+dbName,userName,userPasswd);
	    
	    //Basic trail of the usage of the DatabaseMetaData;
	    
	    DatabaseMetaData dbMD=connection.getMetaData();
	    System.out.println("UserName: "+dbMD.getUserName());
	    System.out.println("DB col: "+dbMD.getMaxColumnsInIndex());
	     
	    //Basic trail of the usage of statement.
	    Statement statement=connection.createStatement();
	    String sql="SELECT * FROM "+tableName;
	    
	    ResultSet rs=statement.executeQuery(sql);
	    
	    ResultSetMetaData rmeta=rs.getMetaData();
	    
	    int numColumns=rmeta.getColumnCount();
	    
	    for (int i=1;i<=numColumns;i++)
	    	System.out.print(rmeta.getColumnName(i)+"|");
	    System.out.println();
	    
	     while( rs.next() ){
	           for( int i = 1;i <=numColumns;i++ )
	               System.out.print(rs.getString(i)+" "); 
	       
	           System.out.println();
	       } 
	     rs.close(); 
	     
	     //Basic trail of PreaparedStatement.
	     /*
	     String insertSql="INSERT INTO leaders(xname,xstuid,xsex) values(?,?,?)";
	     PreparedStatement pst=connection.prepareStatement(insertSql);
	     pst.setString(1, "test");
	     pst.setInt(2,2013210000);
	     pst.setString(3,"F");
	     pst.executeUpdate();
	     */
	     connection.close();
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
			  
  }
}
