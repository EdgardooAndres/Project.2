P2_801134568 version: 1.0 10\16\2016

This program accepts a table with tuples as a Random Access File for analyzing
the frequency and percentage of each tuple combination selected by user.

 Checks if the file name from user input exists and if it is a valid table.
 Prints the table from the file.
 Asks if the user wants to analyze the table.
 If he/she does then user will have to input the column number
 	that is beside each column name from the table "ANOTHER SMALL TABLE".
 The program then continues to the analysis of the data and prints
 	the table "ANALYSIS TABLE".
 This process will be repeated until the user does not want to analyze
 	any more data from the table.
 
 		 ANALYSIS TABLE:
		 -----------------------------------------------
		 | a1 | a2 | ... | ak | frequency | percentage |
		 | .. | .. | ... | .. | ......... | .......... |
		 -----------------------------------------------
		 
		 RAF METADATA:
		 -----------------------------------------------------------------------
		 | n | a1 | a2 | ... | an | record(row)1 | ........ | last record(row) |
		 -----------------------------------------------------------------------
		 
		 ANOTHER SMALL TABLE:
		 ----------------------------------------------------------------------------------------------------
		 | a1(data type)(column number) | a1(data type)(column number) | ... | ak(data type)(column number) |
		 ---------------------------------------------------------------------------------------------------- 
 
 
 This project currently runs only with a runnable jar file or in eclipse JRE 1.8 in different
environments and platforms. Run the .jar file through the command prompt with this instructions:
	1.Open command prompt.
	2.Go to the directory where runnable jar is available
	3.Type "java -jar Name.jar"
	
Unfortunately the only person who contributed to this first version is:
	EdgardooAndres
		https://github.com/EdgardooAndres
		edgardo.muniz@upr.edu
Keep in mind that this is a school assignment and not made for other purposes. The contributor 
email is for school purposes only and will be accepted very filtered information.
