package datalyaer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DbAdapter {
	final static String tableName = "WrodInfo";
	final static String connectionString = "jdbc:sqlite:mokvoc.db";
	public static void createDB () {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(connectionString);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE " + tableName + 
					"(WORD 			 TEXT    PRIMARY KEY     NOT NULL," +
					" PRON           TEXT, " + 
					" MEANING        TEXT, " + 
					" SYNON          TEXT, " + 
					" OPPOSIT        TEXT, " +
					" SAMPLE         TEXT, " +
					" DATE           INTEGER)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public static void insertOp(String word, String pron, String meaning, String synon, String opposit, String sample, long date) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(connectionString);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO " + tableName + " (WORD,PRON,MEANING,SYNON,OPPOSIT,SAMPLE,DATE) " + 
					"VALUES (" + word + "," + pron + "," + meaning + "," + synon + "," + opposit + "," + sample + "," + date + ");";

			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static ArrayList<HashMap<String, String>> selectOp() {
		Connection c = null;
		Statement stmt = null;
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>(); 
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(connectionString);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM " + tableName + ";" );
			
			while ( rs.next() ) {
				HashMap<String, String> row = new HashMap<String, String>();
				row.put("WORD", rs.getString("WORD"));
				row.put("PRON", rs.getString("PRON"));
				row.put("MEANING", rs.getString("MEANING"));
				row.put("SYNON", rs.getString("SYNON"));
				row.put("OPPOSIT", rs.getString("OPPOSIT"));
				row.put("SAMPLE", rs.getString("SAMPLE"));
				row.put("DATE", rs.getString("DATE"));
				result.add(row);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return result;
	}
	
	public static void updateOp(String word, String pron, String meaning, String synon, String opposit, String sample, long date) {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(connectionString);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();

	      String sql = "UPDATE "+ tableName + " set PRON = " + pron + ", MEANING = " + meaning + ", SYNON = " + synon +
	      		"OPPOSIT = " + opposit + ", SAMPLE = " + sample + "DATE = " + date + " WHERE WORD = " + word + ";";
	      stmt.executeUpdate(sql);
	      c.commit();

	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public static void deleteOp(String word) {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(connectionString);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from " + tableName + " where WORD = " + word + ";";
	      stmt.executeUpdate(sql);
	      c.commit();

	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
}
