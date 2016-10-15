package forTableAnalysis;

import java.util.ArrayList;

public class Table {
	private fileManagement file;

	public Table(fileManagement file)
	{
		this.file = file;
	}
	public boolean isValidTable() {
		// TODO Auto-generated method stub
		return true;
	}

	public void displayTable() {  //TABLE 1
		//PRE: the file is a valid table.
		displaySmallTable();
		
		ArrayList<Object> data = file.getDataList();
		while(data.size()!=0)
		{
			for (int i = 0; i<file.getColumns() ; i++) 
			{
				System.out.printf(file.getDataList().get(i).toString() + "\t");
				data.remove(i);
			}

			System.out.printf("%n");
		}
	}

	public void displaySmallTable() { //ANOTHER SMALL TABLE
		ArrayList<String> names = file.getColumNames();
		ArrayList<String> types = file.getStringIDs();

		for (int i = 0; i<file.getColumns() ; i++) 
			System.out.printf(names.get(i) + "(" + types.get(i) + ")" + 
					"(" + (i+1) +")" +"\t");
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
