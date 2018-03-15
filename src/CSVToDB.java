/*
 * Sean Gallagher 
 * CSVToDB
 * 
 * This does not need to be ran in order to use Prog3
 * I am simply including it to show you how I inserted into my tables
 * 
 * This program creates 6 SQL files that each fill their table in the database with the information from the csv files
 * The files created fill the following tables:
 * Year2010, Year2011, Year2012, Year2013, Year2014
 * Also into one of 15 counties are set as the table destination for countyInsert
 * 
 * I created insertAll sql files by hand that run these sql files created here
 * 
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVToDB {
	public static void main(String args[]){
		createInsertionSQL("Year2010", "2010.csv");
		createInsertionSQL("Year2011", "2011.csv");
		createInsertionSQL("Year2012", "2012.csv");
		createInsertionSQL("Year2013", "2013.csv");
		createInsertionSQL("Year2014", "2014.csv");
		createInsertionCounty("2014.csv");
	}
	
	private static void createInsertionCounty(String input) {
		//Pima,Maricopa,Mohave,Pinal,Yavapai,Apache,Cochise,
		//Yuma,La Paz,Greenlee,Graham,Navajo,Coconino,Gila,Santa Cruz;
		
		String filename = "countyInsert.sql";
		
		BufferedReader CSVFile = null;
		try {
			CSVFile = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<String[]> rows = new ArrayList<String[]>();
		String dataRow;
		try {
			int i = 0; 
			while ((dataRow = CSVFile.readLine()) != null){
				i++;
				String[] tokens = dataRow.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				rows.add(tokens);
			}
			System.out.println(i+" rows in the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int j = 0; j<rows.size(); j++){
			for(int h=0; h<34; h++){
				if(rows.get(j)[h].compareTo("")==0){
					rows.get(j)[h]=null;
				}
			}
		}
		ArrayList<String> insertQuery = new ArrayList<String>();
		String s = "'";

		for(int j = 0; j<rows.size(); j++){
			rows.get(j)[2]=rows.get(j)[2].replaceAll("\\s+","");
			String q = "insert into "+rows.get(j)[2]+" values (";
			for(int h=0; h<34; h++){
				if(h==1 || h==2 || h==5 || h==8 || h==9){
					if(rows.get(j)[h].contains("'")){
						String x = rows.get(j)[h];
						for (int index = rows.get(j)[h].indexOf("'");index >= 0; index = rows.get(j)[h].indexOf("'", index + 1)){
							x = x.substring(0, index+1) + "'" + x.substring(index+1, x.length());
						}
						rows.get(j)[h] = x;
					}
					q+=s+rows.get(j)[h]+s;
				}else{
					q+=rows.get(j)[h];
				}
				if(h<33){
					q+=",";
				}
			}			
			q +=");";
			insertQuery.add(q);
		}
		
		try {
			CSVFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writeSQLFile(filename, insertQuery);
		
	}

	public static void createInsertionSQL(String table, String input){
		String filename = table+"insert.sql";
		
		BufferedReader CSVFile = null;
		try {
			CSVFile = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<String[]> rows = new ArrayList<String[]>();
		String dataRow;
		try {
			int i = 0; 
			while ((dataRow = CSVFile.readLine()) != null){
				i++;
				String[] tokens = dataRow.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				rows.add(tokens);
			}
			System.out.println(i+" rows in the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int j = 0; j<rows.size(); j++){
			for(int h=0; h<34; h++){
				if(rows.get(j)[h].compareTo("")==0){
					rows.get(j)[h]=null;
				}
			}
		}
		ArrayList<String> insertQuery = new ArrayList<String>();
		String s = "'";

		for(int j = 0; j<rows.size(); j++){
			String q = "insert into "+table+" values (";
			for(int h=0; h<34; h++){
				if(h==1 || h==2 || h==5 || h==8 || h==9){
					if(rows.get(j)[h].contains("'")){
						String x = rows.get(j)[h];
						for (int index = rows.get(j)[h].indexOf("'");index >= 0; index = rows.get(j)[h].indexOf("'", index + 1)){
							
							x = x.substring(0, index+1) + "'" + x.substring(index+1, x.length());
						}
						rows.get(j)[h] = x;
					}
					q+=s+rows.get(j)[h]+s;
				}else{
					q+=rows.get(j)[h];
				}
				if(h<33){
					q+=",";
				}
			}			
			q +=");";
			insertQuery.add(q);
		}
		
		try {
			CSVFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writeSQLFile(filename, insertQuery);
	}
	
	public static void writeSQLFile(String filename, ArrayList<String> rows){
		BufferedWriter SQLFile = null;
		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			SQLFile = new BufferedWriter(fw);
			
			for(int i=0; i<rows.size(); i++){
				SQLFile.write(rows.get(i));
				SQLFile.newLine();
			}
			SQLFile.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





