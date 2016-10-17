package forTableAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author Edgardo Muniz
 *
 */
public class DataAnalyzer {

	private fileManagement file;
	private ArrayList<String> columNames = new ArrayList<>();
	private ArrayList<Object> dataList = new ArrayList<>();
	private HashMap<ArrayList<Object>,Integer> freqList = new HashMap<>();
	private ArrayList<Integer> attributeIndexes;

	public DataAnalyzer(fileManagement file, ArrayList<Integer> attributeIndexes)
	{
		this.file = file;
		this.attributeIndexes = attributeIndexes;
	}
	public DataAnalyzer(fileManagement file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}

	/**
	 * Public method for analyzing Data.
	 */
	public void analyzeData() {
		// TODO Auto-generated method stub

		//select column names to be displayed.
		selectColumNames();

		// add data of each column
		addDataToAnalyze();

		//calculate freq.
		calculateFreq();

		//percentage = freq x 10;
	}

	/**
	 * 	Filters the columns the user selected to analyze from 
	 * all the columns of a table.
	 */
	private void selectColumNames()
	{
		for (Integer integer : attributeIndexes)
		{
			columNames.add(file.getColumNames().get(integer));
		}
	}

	/**
	 * 	Filters the data of the columns the user selected to
	 * analyze from all the data of the table.
	 */
	private void addDataToAnalyze()
	{
		for(int i=0; i<file.getDataList().size(); i+=file.getColumns())
			for (int j = 0; j < file.getColumns(); j++) 
				for (Integer integer : attributeIndexes)
					if(integer == (j%file.getColumns()))
					{
						dataList.add(file.getDataList().get(i+j));
					}
	}

	/**
	 * Calculates the frequency of each tuple combination of
	 * the data from the columns that the user selected to analyze.
	 */
	private void calculateFreq()
	{
		for(int i=0; i<dataList.size(); i+= attributeIndexes.size())
		{
			ArrayList<Object> list = new ArrayList<>();
			for (int j = 0; j < attributeIndexes.size(); j++)
			{
				list.add(dataList.get(i+j));
			}
			if(freqList.containsKey(list))
				freqList.put(list, freqList.get(list) + 1);
			else 
				freqList.put(list, 1);
		}
	}

	/**
	 * @return the file
	 */
	public fileManagement getFile() {
		return file;
	}

	/**
	 * @return the columNames
	 */
	public ArrayList<String> getColumNames() {
		return columNames;
	}

	/**
	 * @return the dataList
	 */
	public ArrayList<Object> getDataList() {
		return dataList;
	}

	/**
	 * @return the freqList
	 */
	public HashMap<ArrayList<Object>, Integer> getFreqList() {
		return freqList;
	}

	/**
	 * @return the attributeIndexes
	 */
	public ArrayList<Integer> getAttributeIndexes() {
		return attributeIndexes;
	}

	/**
	 * Displays a table with the columns and data that the user selected
	 * to analyze plus the frequency of each tuple combination
	 * and the percentage that each tuple have for that particular
	 * analysis table.	
	 */
	public void displayAnalysisTable()
	{
		for (int i = 0; i<columNames.size() ; i++) 
			System.out.print(columNames.get(i) + "\t");
		System.out.println("Frequency" + "\t" + "Percentage");
		//		for(int i = 0; i<analyzed.getColumNames().size() ; i++)
		//			System.out.print(analyzed.get);
		Set<ArrayList<Object>> list=freqList.keySet();
		for(ArrayList<Object> datas : list) 
		{				
			for(int i=0; i<attributeIndexes.size(); i++)
				System.out.print(datas.get(i) + "\t");
			System.out.print(freqList.get(datas) 
					+ " \t" + (freqList.get(datas)*10));
			System.out.println("");
		}

	}
}
