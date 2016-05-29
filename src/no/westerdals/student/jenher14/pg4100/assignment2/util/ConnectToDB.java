/**
 * Herman Jensen
 * PG4100
 * Innlevering 2
 */

package no.westerdals.student.jenher14.pg4100.assignment2.util;

import java.sql.*;

public class ConnectToDB implements AutoCloseable
{
	private Connection con;
	
	public ConnectToDB (String server, String database, String user, String password) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, user, password);
	}
	
	public void close () throws SQLException
	{
		con.close();
	}
	
	public Connection getConnection ()
	{
		return con;
	}
}
