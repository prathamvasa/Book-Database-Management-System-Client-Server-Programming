package PVasaPackage;

//Importing all the important packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//DROPPING ALL THE TABLES AFTER EXECUTING ONCE
public class PVasaDrop
{
public static void main(String args[])
{
		

final String CONNECTION_PATH_URL = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g"; 
final String USERNAME = "pvasa";
final String PASSWORD = "Noodlesmaggie9";

try
{
//Establishing connection with the Database
Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("Driver loaded SUCCESSFULLY");
}
catch(ClassNotFoundException e)
{
System.out.println(e);
}
		
try
{
// SEtup connection with the database.
Connection conn = DriverManager.getConnection (CONNECTION_PATH_URL,USERNAME, PASSWORD);
			
System.out.println("Connection to database established");
			
try
{
Statement stmt = conn.createStatement();
		
stmt.executeUpdate(" DROP TABLE authorISBN");
stmt.executeUpdate(" DROP TABLE titles");
stmt.executeUpdate(" DROP TABLE publishers");
stmt.executeUpdate(" DROP TABLE authors");
stmt.executeUpdate(" DROP sequence is1");
stmt.executeUpdate(" DROP sequence is2");
System.out.println("All Tables dropped successfully");
stmt.close();
conn.close();
			    
}
catch(SQLException e)
{
System.out.println(e);
e.printStackTrace();
}
finally
{
conn.close();
}
			
}
		
catch(Exception e)
{
System.out.println(e);
e.printStackTrace();
}
		
}
}