package p2MainClasses;

import java.io.RandomAccessFile;

/**
 * This initiates the execution of the application that allows 
 * data analysis on the data extracted from a given file.
 * @author Edgardo Muniz
 *
 */
public class TableAnalyzer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RandomAccessFile file;
		
	}
}
//INSTRUCTIONS:

/* LOOK FOR FILE IN "InputData"
 * 
 * *if exists
 * ** if it is a valid table
 * ***continue
 * **if its not a valid table
 * ***error message not a valid table.
 * 
 * *if not exists 
 * **error message file does not exist.
 */

/* HERE DATA FILE IS VALID
 * 
 * reads the data from file
 * displays table content
 * display another small table
 * **lists of attribute names
 * **attributes data types for each name
 * **column number in the table
 */

/*ASK IF USER WANTS TO ANALYZE ATTRIBUTES.
 * 
 * *if user does want to analyze
 * **requests the attributes to be analyzed
 * **user enters them one by one
 * **"Next attribute index:"
 * **range from [1,n]
 * **end asking if entered<1.
 * 
 * *if not want to analyze 
 * **end process.
 * 
 */

/*FOR EACH VALUE ENTERED UNTILL HERE DO FOLLOWING
 * 
 * 1.
 * *if input is not valid integer.
 * **error message and keep asking
 * 
 * 2.
 * *if it is a valid integer
 * **interpret as end of attribute specification process
 * **process ends and system passes to analyze
 * 
 * 3.
 * *if it is valid and but out of range
 * **error massage and continues asking
 * 
 * 4.
 * *if it is valid and in valid range
 * **try to add to list of attributes
 * **if it is was already in the list
 * ***warning message
 * ***do not add again
 * **it is added to the list
 */

/*LIST IS COMPLETED, START ANALYSIS
 * 
 * if list is empty
 * *notification message
 * *end process successfully with no display
 * 
 * if list is not empty
 * *show table with percentage of occurrence of each tuple
 */

/*ANALYSIS IS CONCLUDED
 * 
 * ask if another analysis
 * *if yes
 * **repeat process with same table 
 * **from "HERE DATA FILE IS VALID" section
 */

/* ANALYSIS TABLE:
 * -----------------------------------------------
 * | a1 | a2 | ... | ak | frequency | percentage |
 * | .. | .. | ... | .. | ......... | .......... |
 * -----------------------------------------------
 * 
 * RAF METADATA
 * -----------------------------------------------------------------------
 * | n | a1 | a2 | ... | an | record(row)1 | ........ | last record(row) |
 * -----------------------------------------------------------------------
 */