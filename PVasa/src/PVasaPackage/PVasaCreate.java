package PVasaPackage;

//Importing all the important packages required
import java.io.*;
import java.sql.*;

public class PVasaCreate

{

private static final String CONNECTION_PATH_URL = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g";
private static final String USERNAME = "pvasa";
private static final String PASSWORD = "Noodlesmaggie9";

	
public static void main(String args[]) 
{
try 
{
// Load the JDBC Driver
Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("Loaded JDBC server");
} 
catch (ClassNotFoundException e) 
{
System.out.println("Cant load driver." + e);
}

try 
{
// Provide the user details to connect to JDBC
Connection conn = DriverManager.getConnection(CONNECTION_PATH_URL,USERNAME, PASSWORD);

System.out.println("Connection is established!");

try
{
Statement statement = conn.createStatement();

//The below function will create all the tables
createAllTables(statement);

// Sequences are used for AUTO INCREMENT
String is1 = "CREATE SEQUENCE is1 START WITH 1 INCREMENT BY 1";
String is2 = "CREATE SEQUENCE is2 START WITH 1 INCREMENT BY 1";

String sN = "is1.nextval";
String pS = "is2.nextval";

statement.executeUpdate(is1);
statement.executeUpdate(is2);

// The below function will populate all the database tables

populateTables(statement,sN, pS);

System.out.println("------------SELECT ONE OPTION-------------");
boolean loop = true;

while (loop)
{
try 
{
//The MENU of all the available operations
System.out.println("1 Display all the authors");
System.out.println("2 Display all the publishers");
System.out.println("3 Update/Modify the information for a specific author");
System.out.println("4 Addition of a new author");
System.out.println("5 Addition of a new publisher");
System.out.println("6 Update/Modify the information for a specific publisher");
System.out.println("7 Display the books by a specific publisher");
System.out.println("8 Add a new title by an author.");
System.out.println("9 Exit as on user choice");
System.out.print("Please provide your choice ");
						
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

String choiceString = br.readLine();
						
int choice = Integer.parseInt(choiceString);

switch (choice)
{
case 1:
// The function to display all the authors
showAllAuthors(statement);
break;

case 2:
// The function to display all the publishers
showAllPublishers(statement);
break;

case 3:
// The function to Update/Modify the author information
editAuthorInformation(statement);
break;

case 4:
// The function to add new author
addAuthorInformation(statement, sN);
break;

case 5:
// The function to add new publisher 
							
addPublisherInformation(statement,pS);
break;

case 6:
// The function to Update/Modify the publisher information
editPublisherInformation(statement);
break;

case 7:
// The function to display all the books of a specific publisher
showAllBooks(statement);
break;

case 8:
// The function to add a new title for an author
addTitleToAuthor(statement, pS);
break;

case 9:
System.out.println("EXITING THE OPERATIONS");
loop = false;

}
} 
catch (NumberFormatException ne)
{
System.out.println(ne.getMessage()+" PLEASE TRY AGAIN");
System.exit(0);
}

}

statement.close();
				
} 
catch (Exception e)
{
System.out.println(e);
e.printStackTrace();
} 
finally 
{
conn.close();
}
} 
catch (Exception e)
{
System.out.println(e);
e.printStackTrace();
}
}

//The function to create all the tables
private final static void createAllTables(Statement statement) 
{
try 
{

// Creation of authors table
String authorsTable = "CREATE TABLE authors (authorID INTEGER not null primary key, firstName CHAR(20) not null, lastName CHAR(20) not null)";

int retVal = statement.executeUpdate(authorsTable);
if (retVal == 0)
System.out.println("authors table created SUCCESSFULLY");

// Creation of publishers table
String publishersTable = "CREATE TABLE publishers ( publisherID INTEGER not null primary key , publisherName CHAR(100) not null)";

retVal = statement.executeUpdate(publishersTable);
if (retVal == 0)
System.out.println("publishers table created SUCCESSFULLY");

// Creation of titles table
String titlesTable = "CREATE TABLE titles ("
                    + "isbn CHAR(10) not null primary key,"
					+ "title VARCHAR(500) not null,"
					+ "editionNumber INTEGER not null,"
					+ "copyright CHAR(4) not null,"
					+ "publisherID INTEGER not null,"
					+ "price NUMBER(8, -2) not null,"
					+ "FOREIGN KEY(publisherID) REFERENCES publishers(publisherID) ON DELETE CASCADE)";

retVal = statement.executeUpdate(titlesTable);
if (retVal == 0)
System.out.println("titles table created SUCCESSFULLY");

// Creation of authorISBN table.
String authorISBNTable = "CREATE TABLE authorISBN ("
				    + "authorID INTEGER not null,"
					+ "isbn CHAR(10) not null,"
					+ "FOREIGN KEY(authorID) REFERENCES authors(authorID) ON DELETE CASCADE,"
					+ "FOREIGN KEY(isbn) REFERENCES titles(isbn) ON DELETE CASCADE)";

retVal = statement.executeUpdate(authorISBNTable);
if (retVal == 0)
System.out.println("AuthorISBN table created SUCCESSFULLY\n");

} 
catch (SQLException e)
{
System.out.println(e);
e.printStackTrace();
}
}

//This function populates all the tables
private final static void populateTables(Statement statement,String sequence, String publisherSequence) 
{
try
{

// insert 15 records in authors table
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'PRATHAM','VASA')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'HARSH','PANDYA')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ADIT','VIRA')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'HURTZ','GRANT')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'KELLY','RICH')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'AHMED','EZZAT')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'FRED','WILSON')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'VISHESH','KAMDAR')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'TOMMY','JACKSON')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ANDREW','MCHALL')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ALIASAGAR','NADELLA')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'BILL','CLINTON')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'SURAJ','PANDEY')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'FIONA','BALLU')");
statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ANNABELLE','ZIG')");

// insert 15 records inside the publishers table
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ABI BUILDING')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence
							+ ",'AMERICAN TECHNICAL PUBLISHERS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'MIT PRESS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ADVANCED ENGINEERING')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'DATA TRACE PUBLISHING')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'TECHMAX')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'TECHNICAL PUBLICATIONS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'EASY SOLUTIONS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'CONSTRUCTION TRADE PRESS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ENFLEX')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ASIAN BOOKS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'GAMER PUBLICATIONS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'TIMES NOW PUBLICATIONS')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ERNST AND SOHN')");
statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ABC PUBLICATIONS')");

// insert 15 records in the titles table

statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('0020GHSFD9','CLIENT SERVER PROGRAMMING', 1,'2007',2,80.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('4000RTQ8O9','JAVA BASICS', 1,'2009',3,90.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('0900GHSFD9','ADVANCED JAVA', 1,'2003',2,90.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('2000ZAQUD9','MOBILE COMPUTING', 3,'2005',3,220.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('300120SFD9','OBJECTIVE C', 2,'2010',3,510.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('B67543HJGU','COMPUTER NETWORKS', 3,'2005',15,710.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('5000GIOSF9','POLITICAL SCIENCE', 1,'2013',4,98.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('0000GHSFH8','DBMS', 1,'2005',1,150.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('600090HSFD','ADVANCED DBMS', 2,'2008',5,110.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('90076HJGJK','GENDER AND ENGINEERING', 1,'2009',8,70.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('7000GHS897','FRUGAL LABS ', 2,'2010',6,90.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('8000GKIUY0','ASTRONOMICAL SCIENCE', 2,'2011',7,610.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('1000GHSFD9','SECURE CODING IN C', 2,'2006',2,160.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
					+ " VALUES ('A067098JGF','SUDDEN FICTION', 2,'2009',10,120.00)");
			
statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price)"
				    + " VALUES ('0000GHSFD9','JAVA PROGRAMMING', 2,'2005',1,140.00)");

// insert 15 records in the authorISBN table

statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (2,'0000GHSFD9')");
		
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (6,'90076HJGJK')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (15,'0020GHSFD9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (3,'1000GHSFD9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (5,'A067098JGF')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (12,'300120SFD9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (9,'0000GHSFH8')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (7,'4000RTQ8O9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (8,'5000GIOSF9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (1,'600090HSFD')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (10,'7000GHS897')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (11,'8000GKIUY0')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (4,'0900GHSFD9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (13,'2000ZAQUD9')");
			
statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (14,'B67543HJGU')");

       
}
catch(SQLException e)
{
System.out.println(e);
e.printStackTrace();
}
}

//QUERY1 DISPLAY ALL THE AUTHORS AND ORDER ALPHABETICALLY IN ASCENDING ORDER
private final static void showAllAuthors(Statement stmt)
{
try 
{
ResultSet resultSet = stmt.executeQuery("SELECT * FROM authors ORDER BY lastName ASC,firstName ASC");
System.out.println("DISPLAYING ALL THE AUTHORS");
System.out.println("--------------------------");
System.out.println("LAST NAME\t\tFIRST NAME\t\tAUTHOR-ID");

while (resultSet.next())
{
System.out.println(resultSet.getString("lastName") + "\t"+ resultSet.getString("firstName") + "\t"
+ resultSet.getInt("authorID"));
}
System.out.println();

} 
catch (SQLException e)
{
System.out.println(e);
e.printStackTrace();
}
}


//QUERY2 DISPLAYING ALL THE PUBLISHERS
private final static void showAllPublishers(Statement stmt)
{
try
{
ResultSet resultSet = stmt.executeQuery("SELECT * FROM publishers");
System.out.println("DISPLAYING ALL THE PUBLISHERS");
System.out.println("--------------------------");
System.out.println("PUBLISHER-ID\t\tPUBLISHER NAME");

while (resultSet.next()) 
{
System.out.println(resultSet.getInt("publisherID") + "\t\t"
+ resultSet.getString("publisherName"));

}
System.out.println();
} 
catch (SQLException e) 
{
System.out.println(e);
e.printStackTrace();
}
}

//QUERY3 EDIT THE AUTHOR INFORMATION
private final static void editAuthorInformation(Statement stmt)
{
boolean isFailure = true;
try 
{
System.out.println("Enter the author ID: ");
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String id = null;
try 
{
id = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}

System.out.println("Enter first name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String fname = null;

try 
{
fname = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}
System.out.println("Enter last name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String lname = null;

try 
{
lname = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an error");
System.exit(1);
}

//ACTUAL MODIFICATION
String updateAuthor = "UPDATE authors SET firstName = '" + fname
+ "', lastName = '" + lname + "' WHERE authorID = " + id;
int updated = stmt.executeUpdate(updateAuthor);

if (updated > 0) 
{
System.out.println("Author information updated successfully");
System.out.println();
} 
else 
{
System.out.println("The requested Author is not found");
System.out.println();
isFailure = true;
}
if (isFailure == false) 
{
System.out.println("After modification the authors table is:");
System.out.println();
showAllAuthors(stmt);
System.out.println();
}
} 
catch (SQLException e)
{
System.out.println(e);
e.printStackTrace();
}
}

//QUERY4 ADDITION OF A NEW AUTHOR
private final static void addAuthorInformation(Statement stmt,
String sequenceName)
{
		

try 
{
System.out.println("Enter the details of the author to be added to the table: ");

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter first name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String fname = null;

try 
{
fname = br.readLine();

} 
catch (IOException e) 
{
System.out.println("Error!");
System.exit(1);
}

System.out.println("Enter last name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String lname = null;

try 
{
lname = br.readLine();
} 
catch (IOException e) 
{
System.out.println("Error!");
System.exit(1);
}

// ACTUAL INSERTION
String insertAuthor = "INSERT INTO authors(authorID, firstName, LastName) values ("
+ sequenceName + ", '" + fname + "','" + lname + "')";
			
int updated = stmt.executeUpdate(insertAuthor);

if (updated == 1) 
{
System.out.println("Author information added successfully");
System.out.println();
}
else 
{
System.out.println("Falied to add information.");
System.out.println();

}

System.out.println("THE AUTHOR TABLE AFTER INSERT");
System.out.println();
showAllAuthors(stmt);
System.out.println();

} 
catch (SQLException e) 
{
System.out.println(e);
e.printStackTrace();
}
}


//QUERY5 ADDING A NEW PUBLISHER	
private final static void addPublisherInformation(Statement stmt,String publisherSequence)
{
try 
{

System.out.println("Enter the details of the publisher to be added to the table: ");

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter publisher name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String publisherName = null;
try 
{
publisherName = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}

//ACTUAL INSERTION
String insertPublisher = "INSERT INTO publishers(publisherID, publisherName) VALUES ("
+ publisherSequence + ", '" + publisherName + "')";
int updated = stmt.executeUpdate(insertPublisher);

if (updated == 1) 
{
System.out.println("New publisher added SUCCESSFULLY");
System.out.println();
} 
else 
{
System.out.println("ADDITION FAILED");
System.out.println();
}

System.out.println("AFTER INSERTION");
System.out.println();
showAllPublishers(stmt);
System.out.println();
} 
catch (SQLException e) 
{
System.out.println(e);
e.printStackTrace();
}
}


//QUERY6 EDITING THE PUBLISHER INFORMATION	
private final static void editPublisherInformation(Statement st)
{
boolean failed = false;
try 
{

System.out.println("Enter the publisher ID whose information needs to be modified: ");
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String id = null;
try 
{
id = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}

System.out.println("Enter publisher name: ");
br = new BufferedReader(new InputStreamReader(System.in));
String publisherName = null;

try 
{
publisherName = br.readLine();

} 
catch (IOException e) 
{
System.out.println("Error!");
System.exit(1);
}

String updatePublisher = "UPDATE publishers SET publisherName = '"+ publisherName + "' WHERE publisherID = " + id;

int updated = st.executeUpdate(updatePublisher);

if (updated > 0) 
{
System.out.println("Publisher information updated successfully");
System.out.println();
} 
else 
{
System.out.println("PUBLISHER not found UPDATE FAILED");
System.out.println();
failed = true;
}
			
if (failed == false)
{
System.out.println("AFTER UPDATE");
System.out.println();
showAllPublishers(st);
System.out.println();
}
} 
catch (SQLException e) 
{
System.out.println(e);
e.printStackTrace();
}
}


//QUERY7 DISPLAY ALL THE BOOKS BY A SPECIFIC PUBLISHER
private final static void showAllBooks(Statement stmt)
{
try
{

System.out.println("Please, Enter the publisher ID,To display all books.");
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String id = null;
try 
{
id = br.readLine();

} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}

String displayBooks = "SELECT title,isbn,copyright FROM titles WHERE publisherID = '"+ id + "' ORDER BY title ASC";
ResultSet rs = stmt.executeQuery(displayBooks);

System.out.println("BOOKS LISTED BY PUBLISHER");
System.out.println("-----------------------------------------------");
System.out.println("TITLE    \t\t  ISBN    \t\t    YEAR");

while (rs.next()) 
{
System.out.println(rs.getString("title") + "\t"+ rs.getString("isbn") + "\t" + rs.getInt("copyright"));

}
System.out.println();
} 
catch (SQLException e) 
{
System.out.println(e);
e.printStackTrace();
}
}


// QUERY8 ADD A NEW TITLE FOR AN AUTHOR	
private final static void addTitleToAuthor
(Statement st,String publisherSequence)
{
boolean failed = false;
try
{
                   
System.out.println("Enter the ID of the author to add a new title");
                   
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String id = null;
try 
{
id = br.readLine();
                       
} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}
                   
                   
String check = "SELECT * FROM authors WHERE authorID = '" + id + "' ";
ResultSet rs = st.executeQuery(check);
                   
if(rs.next())
{
System.out.println("Enter the  new title");
                       
br = new BufferedReader(new InputStreamReader(System.in));
String title = null;
try 
{
title = br.readLine();
                           
} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}
                       
System.out.println("Enter the  isbn");
                       
br = new BufferedReader(new InputStreamReader(System.in));
String isbn = null;
try 
{
isbn = br.readLine();
                           
} 
catch (IOException e) 
{
System.out.println("There is an Error");
System.exit(1);
}
                       
                       
System.out.println("Enter the edition number");
                       
br = new BufferedReader(new InputStreamReader(System.in));
String number = null;
try
{
number = br.readLine();
} 
catch (IOException e) 
                       
{
System.out.println("There is an Error");
System.exit(1);
}
                       
System.out.println("Enter the copyright year");
                       
br = new BufferedReader(new InputStreamReader(System.in));
String copyright = null;
try
{
copyright = br.readLine();
} 
catch (IOException e)
{
System.out.println("There is an Error");
System.exit(1);
}
                       
                       
System.out.println("Enter the publisher ID");
br = new BufferedReader(new InputStreamReader(System.in));
String publisher = null;
try
{
publisher = br.readLine();
} 
catch (IOException e)
{
System.out.println("There is an Error");
System.exit(1);
}
                       
System.out.println("Enter the price");
br = new BufferedReader(new InputStreamReader(System.in));
String price = null;
try
{
price = br.readLine();
} 
catch (IOException e)
{
System.out.println("There is an Error");
System.exit(1);
}
                       
try
{
String insertTitle = "INSERT INTO titles(isbn, title, editionNumber, copyright, publisherID, price)"
+ " values ('"+isbn + "', '"+ title + "', " +number+",' " +copyright+"'," +publisher+ ","+price+")";
int updated = st.executeUpdate(insertTitle);

if(updated == 1)
{
System.out.println("New Title  added SUCCESSFULLY");
System.out.println();
}
else
{
System.out.println("New TITLE cannot be added");
System.out.println();
}
}
catch(SQLException e)
{
// Error if the author does not exist.
if(e.getErrorCode() == 1)
{
                                                                                         
System.out.println(" ISBN already present.");
addTitleToAuthor(st,publisherSequence);
                                                                                         
}
// Error if the publisher id does not exist in the publisher table.
if(e.getErrorCode() == 2291)
{
                                                                                         
System.out.println(" Publisher id not present.Enter the publisher details first");
System.out.println();
failed = true;
}
}                                                                      
                                                                                                            
if(failed == false)
{
// Insert record in the authorISBN table.
String insertIsbn = "INSERT INTO authorISBN(authorID, isbn) values ("+id + ", '" + isbn + "')";
int updated = st.executeUpdate(insertIsbn);
                                                                                                                
if(updated == 1)
{
System.out.println("Title isbn information added successfully in the authorISBN table");
System.out.println();
}
}
}
                                                                                                                                       
                                                                                                                                       
else
{
System.out.println(" Author does not exist");
addTitleToAuthor(st,publisherSequence);
}
                                                                                                                                       
}
catch(SQLException e)
{
System.out.println("There was a problem with the entry please check and try again.");
return;
}
}
}



