package de.outfit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL {
	public static String ip = "localhost";
	public static String port = "3306";
	public static String user = "root";
	public static String password = "";
	
	public static String dbname = "backend";
	public static String tablename = "clothes";
	
	public static Connection con;
	
	public static ArrayList<clothesobject> connect() {
		con = null;
		ArrayList<clothesobject> array = null;
		
		// Treiber laden
		try {Class.forName("com.mysql.cj.jdbc.Driver").newInstance();}
		catch(Exception ex) {}
		
		// Verbindung aufbauen
		try {
			String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			con = DriverManager.getConnection(url, user, password);
			
			array = data();
			
			con.close();
		} catch (SQLException ex) {}
		return array;
	}
	
	public static ArrayList<clothesobject> data() {
		ArrayList<clothesobject> objectlist = new ArrayList<clothesobject>();
		try {
			// Objekt aus Datenbank auslesen
			String query = "select * FROM " + tablename;
			Statement stmt = con.createStatement();
			ResultSet rset;
			
			rset = stmt.executeQuery(query);
			
			
			
			
			// Objekt zu clothesobject �bergeben
			while(rset.next()) {
				clothesobject object = new clothesobject();
				
				object.createObject(rset.getInt(1), rset.getString(2), rset.getFloat(3), rset.getFloat(4), rset.getString(5), rset.getString(6), rset.getFloat(7), rset.getString(8), rset.getString(9), rset.getBoolean(10), rset.getString(11));
				
				objectlist.add(object);
			}
			
			rset.close();
			stmt.close();
		} catch(SQLException ex) {}
		return objectlist;
	}
}
