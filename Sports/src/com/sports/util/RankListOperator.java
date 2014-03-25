package com.sports.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RankListOperator {
	private final static String tableName="ranklist";

	public static boolean addPoint(int id) {
		// TODO Auto-generated method stub
		boolean ret=false;
		Connection connection=DbConnector.getConnection();
		try {
			Statement statement=connection.createStatement();
			String sql="update "+tableName+ " SET point=point+1 where id="+id;
			statement.execute(sql);
			ret=true;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ret=false;
			e.printStackTrace();
		}
		return ret;
	}

}
