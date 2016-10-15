package forTableAnalysis;

import java.io.IOException;
import java.util.ArrayList;

public class Table {
	private fileManagement file;

	public boolean isValidTable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void displayTable() {  //TABLE 1
		//PRE: the file is a valid table.
		displaySmallTable();
		try {
			while(file.hasNext())
			{
				for (int i = 0; i<file.getColumns() ; i++) 
					System.out.printf(file.getDataList().get(i).toString() + "%t");

				System.out.printf("%n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException on method call file.hasNext()");
		}
	}

	public void displaySmallTable() { //ANOTHER SMALL TABLE
		ArrayList<String> names = file.getColumNames();
		ArrayList<String> types = file.getStringIDs();

		for (int i = 0; i<file.getColumns() ; i++) 
			System.out.printf(names.get(i) + "(" + types.get(i) + ")" + 
					"(" + i +")" +"%t");
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
