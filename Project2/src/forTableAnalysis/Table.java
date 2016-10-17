package forTableAnalysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Edgardo Muniz
 *
 */
public class Table {
	private fileManagement file;
	private DataAnalyzer analyzed;
/**
 * Constructor.
 * 
 * @param file the file that holds the table information.
 */
	public Table(fileManagement file)
	{
		this.file = file;
	}
	
	/**
	 * @return true if the file contains a valid table.
	 */
	public boolean isValidTable() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Displays the table from the file.
	 */
	public void displayTable() {  //TABLE 1
		//PRE: the file is a valid table.
		displaySmallTable();

		Iterator<Object> it = file.getDataList().iterator();
		while(it.hasNext())
		{
			for (int i = 0; i<file.getColumns() ; i++) 
			{
				System.out.printf(it.next().toString() + "\t");
			}
			System.out.printf("%n");
		}
	}

	/**
	 * Displays the information from the columns in the table.
	 */
	public void displaySmallTable() { //ANOTHER SMALL TABLE
		ArrayList<String> names = file.getColumNames();
		ArrayList<String> types = file.getStringIDs();

		for (int i = 0; i<file.getColumns() ; i++) 
			System.out.printf(names.get(i) + "(" + types.get(i) + ")" + 
					"(" + (i) +")" +"\t");
		System.out.printf("%n");
	}

	/**
	 * Displays the analysis table as specified by user.
	 * @param dataAnalyzer object from classs used to analyze the data.
	 */
	public void displayAnalysisTable(DataAnalyzer dataAnalyzer)
	{
		analyzed = new DataAnalyzer(file);
		for (int i = 0; i<analyzed.getColumNames().size() ; i++) 
			System.out.print(analyzed.getColumNames().get(i) + "\t");
		System.out.println("Frequency" + "\t" + "Percentage");
		//		for(int i = 0; i<analyzed.getColumNames().size() ; i++)
		//			System.out.print(analyzed.get);
		Set<ArrayList<Object>> list=analyzed.getFreqList().keySet();
		for(ArrayList<Object> datas : list) 
		{				
			for(int i=0; i<analyzed.getAttributeIndexes().size(); i++)
				System.out.print(datas.get(i) + "\t");
			System.out.print(analyzed.getFreqList().get(datas) 
					+ " \t" + (analyzed.getFreqList().get(datas)*10));
		}
		System.out.println("");
	}
}
/* ANALYSIS TABLE:
 * -----------------------------------------------
 * | a1 | a2 | ... | aK | frequency | percentage |
 * | .. | .. | ... | .. | ......... | .......... |
 * -----------------------------------------------
 * 
 * Table 1:
 * --------------------------------------------------------------------
 * | Attribute1 (type) | Attribute2 (type) | .... | AttributeK (Type) |
 * |      Data 1       |      Data 2       | .... |      Data K       |
 * |      .... .       |      .... .       | .... |      ... .        |
 * --------------------------------------------------------------------
 * 
 * ANOTHER SMALL TABLE:
 * ----------------------------------------------------------------------------------------------------
 * | a1(data type)(column number) | a1(data type)(column number) | ... | aK(data type)(column number) |
 * ----------------------------------------------------------------------------------------------------
 */
