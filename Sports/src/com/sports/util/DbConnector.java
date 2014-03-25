package com.sports.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
  
  private static final String driverName="com.mysql.jdbc.Driver";
  private static final String userName="root";
  private static final String userPwd="940414";
  private static final String dbName="sportmeeting";
  private static final String url="jdbc:mysql://localhost/";
	
  private static Connection connection=null;
  
  private DbConnector()
  {
	  
  }
  
  private static void createConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
  {

	  Class.forName(driverName).newInstance();
	  connection=DriverManager.getConnection(url+dbName,userName,userPwd);
	 
  }
  
  public static Connection getConnection() 
  
  {
	if (connection==null)
		try {
			createConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return connection;
	
  }

public static void closeConnection() {
	// TODO Auto-generated method stub
	if (connection!=null)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection=null;
	}
	
}
}
