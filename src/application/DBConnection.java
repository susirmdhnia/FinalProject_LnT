package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBConnection {
	public Connection connection;
	public Statement statement;

	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptpudding57db", "root", "");
			statement = connection.createStatement();
			System.out.println("Create Connection Successful");
		} catch (Exception e) {
			System.out.println("Failed to Create Connection");
			e.printStackTrace();
		}
	}
	
	
	public void migrateTables() {
		createMenuTable();
	}
	
	
	public void createMenuTable() {
		String query = "CREATE TABLE IF NOT EXISTS menu("
				+ "kodeMenu CHAR(6) PRIMARY KEY CHECK(kodeMenu LIKE 'PD-[0-9][0-9][0-9]'),"
				+ "namaMenu VARCHAR(50) NOT NULL,"
				+ "hargaMenu INT NOT NULL,"
				+ "stokMenu INT NOT NULL)";
		try {
			exec(query);
		} catch (Exception e) {
            System.err.println("Failed to create menu table:");
			e.printStackTrace();
		}
	}
	
	
	public void exec(String query) {
		try {
			statement.execute(query);
		} catch (Exception e) {
			 System.err.println("Failed to execute query:");
			e.printStackTrace();
		}
	}
	
	
	 public void closeConnection() {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	            System.out.println("Database Connection Closed");
	        } catch (Exception e) {
	        	System.err.println("Failed to close connection:");
	            e.printStackTrace();
	        }
	    }


	public Connection getConnection() {
		return connection;
	}


	public boolean deleteMenu(String kodeMenu) {
		String query = "DELETE FROM menu WHERE kodeMenu = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, kodeMenu);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
