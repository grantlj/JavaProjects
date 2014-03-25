package com.sports.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class IpListOperator {
	private final static String tableName="iplist";

	public static boolean checkIp(String ip) {
		// TODO Auto-generated method stub
		boolean ret=false;
		Connection connection=DbConnector.getConnection();
		try {
			Statement statement=connection.createStatement();
			String sql="select * from "+tableName+" where ip=\""+ip+"\"";
			ResultSet rs=statement.executeQuery(sql);
			
			if (!rs.next())
			{
				//valid vote.
			    sql="insert iplist(ip) values("+"\""+ip+"\")";
			    statement.execute(sql);
				ret=true;
				rs.close();
				statement.close();
			}
			
		    
			
			else 
			{
				ret=false;
				rs.close();
				statement.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	return ret;	
	}

}
