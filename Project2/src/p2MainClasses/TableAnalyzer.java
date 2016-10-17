package p2MainClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DataManegement.ValidTypes;
import forTableAnalysis.DataAnalyzer;
import forTableAnalysis.Table;
import forTableAnalysis.fileManagement;

/**
 * This initiates the execution of the application that allows 
 * data analysis on the data extracted from a given file.
 * @author Edgardo Muniz
 *
 */
public class TableAnalyzer { 

	private static fileManagement file; //exampleInSpecs (1).pp2
	private static Scanner sc = new Scanner(System.in);
	private static Table table  = new Table(file);;
	private static ArrayList<Integer> attributeIndexes = new ArrayList<>();
	private static DataAnalyzer dataAnalyzer;

	public static void main(String[] args) {

		/*
		 * LOOK FOR FILE IN "InputData"
		 */
		System.out.println("Input File Name:");
		String fileName = sc.nextLine();
		checkFile(fileName);
		table  = new Table(file);
		if(!table.isValidTable()) //if it is not a valid table then exit the program.
		{
			System.out.println("File is not a valid Table.");
			System.out.println("Process Terminated.");
			System.exit(1);
		}
		/*
		 * HERE DATA FILE IS VALID
		 */
		try {
			file.readData();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read the data, error ocurred.");
			System.out.println("Process Terminated.");
			System.exit(1);
			
		}
		table.displayTable();
		System.out.printf("%n%n");
		/*
		 * ASK IF USER WANTS TO ANALYZE ATTRIBUTES.
		 */
		System.out.println("Do you want to analyze any data? (yes or no)");
		String answer = sc.nextLine();
		if(answer.equalsIgnoreCase("no"))
		{
			System.out.println("Process Terminated.");
			System.exit(1);
		}
		boolean done = false;
		table.displaySmallTable();
		while(!done)
		{
			System.out.println("Which attributes do you wish to analyze? (input the number of the column)");
			requestAttributesToAnalyze(); //guardar los que quiere en una lista para trabajarlos luego.
			/*
			 * LIST IS COMPLETED, START ANALYSIS
			 */
			dataAnalyzer = new DataAnalyzer(file,attributeIndexes);
			analyzeData();
			dataAnalyzer.displayAnalysisTable();
			/*
			 * ANALYSIS IS CONCLUDED
			 */
			System.out.println("Want to make another Analisis? (yes or no)");
			if(!sc.nextLine().equalsIgnoreCase("yes"))
			{
				System.out.println("Process Terminated");
				done = true;
			}
		}
	}

	/**
	 * Verifies if the file already exists.
	 * If it does not, exists the program.
	 * 
	 * @param fileName
	 */
	private static void checkFile(String fileName) {
		try{
			file = new fileManagement(new File("InputData", fileName),"r");
		}
		catch(Exception e){
			System.out.println("File not Found");		
			System.out.println("Process Terminated.");
			System.exit(1);
		}
		
	}

	/**
	 * Calls a method for analyzing the data if the user
	 * has specified the columns to analyze.
	 */
	private static void analyzeData() {
		// TODO Auto-generated method stub

		if(attributeIndexes.isEmpty()) // Did not add any number of columns to list.
		{
			System.out.println("You have not selected any attributes to analyze.");
			System.out.println("Process Terminated.");
			System.exit(1);
		}
		/* 
		 * if list is not empty
		 *   show table with percentage of occurrence of each tuple
		 */
		dataAnalyzer.analyzeData(); // proceed to analyze data.
		
	}

	/**
	 * Request the user to input the columns to analyze
	 */
	private static void requestAttributesToAnalyze() {
		/*
		 * FOR EACH VALUE ENTERED UNTILL HERE DO FOLLOWING
		 */
		int limit = file.getIDs().size();
		int counter = 1;
		while(counter<=limit)
		{
			System.out.println("Next attribute index:");
			System.out.println("(Enter a negative integer if you don't want to analyze more columns");
			String index = sc.nextLine();
			
			ValidTypes valid = new ValidTypes();
			//1.
			if(!valid.isValidInt(index)) //not a valid integer
			{
				System.out.println("Not a valid integer.");
			}
			else //it is a valid integer
			{
				int column = Integer.parseInt(index);
				//2.
				if(column < 0)//negative index, end attribute specification process.
					break;
				//3.
				else if(column>limit) //index is already greater than 0.
					//index is out of range.
					System.out.println("Index out of Range.");
				//4.
				else //index is in range
				{
					attributeIndexes.add(Integer.parseInt(index));
					counter++;
				}
			}
		}
	}
	
	/**
	 * Summarized instructions for this project.
	 */
	public void INSTRUCTIONS()
	{
		//INSTRUCTIONS:

		/* LOOK FOR FILE IN "InputData".
		 * 
		 *  if exists
		 *    if it is a valid table
		 *      continue
		 *    if its not a valid table
		 *      error message not a valid table.
		 * 
		 *  if not exists 
		 *    error message file does not exist.
		 */

		/* HERE DATA FILE IS VALID.
		 * 
		 *  reads the data from file
		 *  displays table content
		 *  display another small table
		 *    lists of attribute names
		 *    attributes data types for each name
		 *    column number in the table
		 */

		/*ASK IF USER WANTS TO ANALYZE ATTRIBUTES.
		 * 
		 *  if user does want to analyze
		 *    requests the attributes to be analyzed
		 *      user enters them one by one
		 *      "Next attribute index:"
		 *      range from [1,n]
		 *      end asking if entered<1.
		 * 
		 *  if not want to analyze 
		 *    end process.
		 * 
		 */

		/*FOR EACH VALUE ENTERED UNTILL HERE DO FOLLOWING:
		 * 
		 * 1.
		 *  if input is not valid integer.
		 *    error message and keep asking
		 * 
		 * 2.
		 *  if it is a valid integer
		 *    interpret as end of attribute specification process
		 *      process ends and system passes to analyze
		 * 
		 * 3.
		 *  if it is valid but(and) out of range
		 *    error massage and continues asking
		 * 
		 * 4.
		 *  if it is valid and in valid range
		 *    try to add to list of attributes
		 *    if it is was already in the list
		 *      warning message
		 *      do not add again
		 *    it is added to the list
		 */

		/*LIST IS COMPLETED, START ANALYSIS.
		 * 
		 * if list is empty
		 *   notification message
		 *   end process successfully with no display
		 * 
		 * if list is not empty
		 *   show table with percentage of occurrence of each tuple
		 */

		/*ANALYSIS IS CONCLUDED.
		 * 
		 * ask if another analysis
		 *  if yes
		 *    repeat process with same table 
		 *    from "ASK IF USER WANTS TO ANALYZE ATTRIBUTES." -> "requests the attributes to be analyzed" section
		 */

		/* ANALYSIS TABLE:
		 * -----------------------------------------------
		 * | a1 | a2 | ... | ak | frequency | percentage |
		 * | .. | .. | ... | .. | ......... | .......... |
		 * -----------------------------------------------
		 * 
		 * RAF METADATA:
		 * -----------------------------------------------------------------------
		 * | n | a1 | a2 | ... | an | record(row)1 | ........ | last record(row) |
		 * -----------------------------------------------------------------------
		 * 
		 * ANOTHER SMALL TABLE:
		 * ----------------------------------------------------------------------------------------------------
		 * | a1(data type)(column number) | a1(data type)(column number) | ... | ak(data type)(column number) |
		 * ----------------------------------------------------------------------------------------------------
		 */
	}
}