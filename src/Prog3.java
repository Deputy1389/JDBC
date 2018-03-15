/*
 * Sean Gallagher 
 * Prog3
 * 
 * My program doesn't include the classpath on lectura that is required
 * In order to compile and run this program it is required to add this to your lectura
 * 
 * export CLASSPATH=/opt/oracle/product/10.2.0/client/jdbc/lib/ojdbc14.jar:${CLASSPATH}
 * 
 * This program signs into my oracle account and connects the to the DB
 * It displays a number of options for queries and to exit
 * Enter the number in the terminal when prompted and the program will proceed to the query
 * If a year is required the user will be prompted to enter a year between 2010-2014
 * 
 */

import java.io.*;
import java.sql.*;                 // For access to the SQL interaction methods
import java.util.Scanner;


public class Prog3 {
	public static Connection dbconn = null;

	public static void main (String [] args){
		final String oracleURL =   // Magic lectura -> aloe access spell
				"jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
		String username = "deputy1389",    // Oracle DBMS username
				password = "a8167";    // Oracle DBMS password
		/*
		if (args.length == 2) {    // get username/password from cmd line args
			username = args[0];
			password = args[1];
		} else {
			System.out.println("\nUsage:  java JDBC <username> <password>\n"
					+ "    where <username> is your Oracle DBMS"
					+ " username,\n    and <password> is your Oracle"
					+ " password (not your system password).\n");
			System.exit(-1);
			}

		 */
		// load the (Oracle) JDBC driver by initializing its base
		// class, 'oracle.jdbc.OracleDriver'.

		try {

			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.err.println("*** ClassNotFoundException:  "
					+ "Error loading Oracle JDBC driver.  \n"
					+ "\tPerhaps the driver is not on the Classpath?");
			System.exit(-1);

		}


		// make and return a database connection to the user's
		// Oracle database



		try {
			dbconn = DriverManager.getConnection
					(oracleURL,username,password);

		} catch (SQLException e) {

			System.err.println("*** SQLException:  "
					+ "Could not open JDBC connection.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

		getInput();

		try {
			dbconn.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	/*
	 * getInput
	 * 
	 * The purpose of this method is to ask the user which query they would like to run and then runs it
	 * 
	 */
	private static void getInput() {
		System.out.println("Created by Sean Gallagher");
		System.out.println("Menu");
		System.out.println("1: Number of high schools in a given year");
		System.out.println("2: Number of charter schools with more failing than passing math scores");
		System.out.println("3: Reading and Writing score rankings by county for 2014");
		System.out.println("4: Find the lowest performing school in the state for a specified year");
		System.out.println("5: Exit");

		Scanner sc = new Scanner(System.in);  
		while(sc.hasNext()){			
			String str = sc.next(); 
			if(str.compareTo("1")==0){
				query1();
			}
			else if(str.compareTo("2")==0){
				query2();
			}
			else if(str.compareTo("3")==0){
				query3();
			}
			else if(str.compareTo("4")==0){
				query4();
			}else{
				System.out.println("Exited\nCreated by Sean Gallagher");
				System.exit(0);
			}

			System.out.println();
			System.out.println("Menu");
			System.out.println("1: Number of high schools in a given year");
			System.out.println("2: Number of charter schools with more failing than passing math scores");
			System.out.println("3: Reading and Writing score rankings by county for 2014");
			System.out.println("4: Find the lowest performing school in the state for a specified year");
			System.out.println("5: Exit");
			System.out.println();
		}


		sc.close();
	}

	/*
	 * query1
	 * 
	 * This method creates the query string and does any formatting required for this specific query
	 * 
	 */
	private static void query1() {
		System.out.println("Enter a year between 2010 and 2014");

		Scanner sc = new Scanner(System.in);  
		while(sc.hasNext()){	
			String year = sc.next(); 
			if(year.compareTo("2010")==0 || year.compareTo("2011")==0 || year.compareTo("2012")==0 || year.compareTo("2013")==0 || year.compareTo("2014")==0){
				String query = "SELECT Count(SchoolName) FROM Year"+year+" where ((SchoolName Like '% High%School%' or SchoolName Like '% High') and SchoolName not like '%Junior %High%' and SchoolName not like '%Jr %High%' and SchoolName not like '%Jr.%High%' and SchoolName not like '%High%Middle%School%')"; 
				System.out.println("Number of highschools for year "+year);
				queryDB(query, 1);	
			}
			else{
				System.out.println("Error: Enter a year between 2010 and 2014");
			}
			break;
		}
		//sc.close();
	}
	/*
	 * query2
	 * 
	 * This method creates the query strings for each year and does any formatting required for this specific query.
	 * This query counts the number of charter schools in each year
	 * 
	 */
	private static void query2() {
		String[] query = new String[5];
		query[0] = "select Count(CharterSchool) from Year2010 where CharterSchool = 'Y' and (MathFallsFarBelowPer + MathApproachesPer) < MathPassingPer";
		query[1] = "select Count(CharterSchool) from Year2011 where CharterSchool = 'Y' and (MathFallsFarBelowPer + MathApproachesPer) < MathPassingPer";
		query[2] = "select Count(CharterSchool) from Year2012 where CharterSchool = 'Y' and (MathFallsFarBelowPer + MathApproachesPer) < MathPassingPer";
		query[3] = "select Count(CharterSchool) from Year2013 where CharterSchool = 'Y' and (MathFallsFarBelowPer + MathApproachesPer) < MathPassingPer";
		query[4] = "select Count(CharterSchool) from Year2014 where CharterSchool = 'Y' and (MathFallsFarBelowPer + MathApproachesPer) < MathPassingPer";

		String[] years = {"2010", "2011", "2012", "2013", "2014"};

		for(int i=0; i<5; i++){
			System.out.println("Year "+years[i]);
			queryDB(query[i], 2);
			System.out.println();
		}
	}

	/*
	 * query3
	 * 
	 * This method creates the query strings for each county and does any formatting required for this specific query.
	 * This query gets the top ten schools from each county where the difference between reading and writing scores
	 * are the greatest.
	 * 
	 */
	private static void query3() {
		String[] strings = new String[15];
		strings[0] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Apache)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[1] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Cochise)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[2] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Coconino)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[3] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Gila)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[4] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Graham)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[5] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Greenlee)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[6] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from LaPaz)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[7] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Maricopa)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[8] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Mohave)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[9] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Navajo)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[10] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Pima)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[11] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Pinal)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[12] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from SantaCruz)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[13] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Yavapai)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";
		strings[14] = "select  * from (select SchoolName, WriPassingPer, ReaPassingPer , ABS(WriPassingPer - ReaPassingPer) as Difference, rank() over (PARTITION BY (CASE WHEN (WriPassingPer IS NOT NULL AND ReaPassingPer is not null) THEN 1 ELSE 2 END) order by ABS(WriPassingPer - ReaPassingPer) desc) as rnk from Yuma)  where rnk <= 10 AND WriPassingPer IS NOT NULL AND ReaPassingPer IS NOT NULL";

		String[] counties = new String[15];
		counties[0] = "Apache";
		counties[1] = "Cochise";
		counties[2] = "Coconino";
		counties[3] = "Gila";
		counties[4] = "Graham";
		counties[5] = "Greenlee";
		counties[6] = "LaPaz";
		counties[7] = "Maricopa";
		counties[8] = "Mohave";
		counties[9] = "Navajo";
		counties[10] = "Pima";
		counties[11] = "Pinal";
		counties[12] = "SantaCruz";
		counties[13] = "Yavapai";
		counties[14] = "Yuma";

		for(int i=0; i<15; i++){
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(counties[i]);
			queryDB(strings[i], 3);
		}

	}

	/*
	 * query4
	 * 
	 * (Custom Query)
	 * 
	 * This method creates the query string for a selected year and does any formatting required for this specific query.
	 * This query finds the school with the greatest sum of approaches and falls far below scores for a year
	 * It basically finds the school with the worst performance of that year
	 * 
	 */
	private static void query4() {
		Scanner sc = new Scanner(System.in);  
		System.out.println("Enter a year between 2010 and 2014");
		while(sc.hasNext()){
			String year = sc.next(); 
			if(year.compareTo("2010")==0 || year.compareTo("2011")==0 || year.compareTo("2012")==0 || year.compareTo("2013")==0 || year.compareTo("2014")==0){
				String query = "select SchoolName, County from Year"+year+" where (WriFallsFarBelowPer + WriApproachesPer + SciFallsFarBelowPer + SciApproachesPer + ReaFallsFarBelowPer + ReaApproachesPer + MathFallsFarBelowPer + MathApproachesPer) = (select Max(WriFallsFarBelowPer + WriApproachesPer + SciFallsFarBelowPer + SciApproachesPer + ReaFallsFarBelowPer + ReaApproachesPer + MathFallsFarBelowPer + MathApproachesPer) from Year"+year+")";
				System.out.println("Worst performing school of "+year);
				queryDB(query, 4);
			}
			else{
				System.out.println("Error: Enter a year between 2010 and 2014");
			}
			break;
		}
		//sc.close();
	}

	/*
	 * queryDB
	 * 
	 * This method creates runs the given query and prints out to the console.
	 * 
	 * Parameters: query is a string that is used to query the database, code is and int that tells which query is running
	 * 
	 */
	public static void queryDB(String query, int code){
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;

		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(query);
			if (answer != null) {
				ResultSetMetaData answermetadata = answer.getMetaData();
				if(code!=3 && code!=4){
					for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						System.out.print(answermetadata.getColumnName(i) + "\t");
					}
				}
				else if(code==4){
					for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						System.out.print(answermetadata.getColumnName(i) + "\t"+ "\t"+ "\t"+ "\t");
					}
				}else{
					System.out.print(answermetadata.getColumnName(5) + "\t");
					for (int i = 1; i <= answermetadata.getColumnCount()-1; i++) {
						if(i == 1)
							System.out.print(answermetadata.getColumnName(i) + "\t"+"\t"+"\t"+"\t");
						else
							System.out.print(answermetadata.getColumnName(i) + "\t");
					}
				}
				System.out.println();

				switch(code){
				case(1):
					while (answer.next()) {
						System.out.println(answer.getInt("Count(SchoolName)")); //Count(SchoolName)
					}
				break;
				case(2):
					while (answer.next()) {
						System.out.println(answer.getInt("count(CharterSchool)"));
					}
				break;
				case(3):
					while (answer.next()) {
						System.out.println(String.format("%-5s %-50s %-15s %-15s %-15s\n",answer.getInt("rnk"), answer.getString("schoolname"), answer.getString("reapassingper"),  answer.getString("wripassingper"),  answer.getInt("difference")));								
					}
				break;
				case(4):
					while (answer.next()) {
						System.out.println(answer.getString("schoolname") + "\t" + "\t" + "\t" + answer.getString("county"));							
					}
				break;
				default: break;
				}
				stmt.close();  
			}
		} catch (SQLException e) {

			System.err.println("*** SQLException:  "
					+ "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}
}
