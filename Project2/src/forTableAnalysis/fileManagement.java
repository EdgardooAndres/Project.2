package forTableAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import DataManegement.Date;

/**
 * 
 * @author Edgardo Muniz
 *
 */
public class fileManagement extends RandomAccessFile{

	public fileManagement(File file, String mode) throws FileNotFoundException {
		super(file, mode);
	}
	//Instance Variables.
	private short columns;
	private ArrayList<Integer> IDs = new ArrayList<>(); 
	private byte nameLength;
	private ArrayList<String> columNames = new ArrayList<>();
	private ArrayList<Object> dataList = new ArrayList<>();

	/**
	 * Public method to read all the data from a valid table in a
	 * Random Access File.
	 * 
	 * @throws IOException if there is a problem with the file.
	 */
	public void readData() throws IOException {
		//read number of columns
		readColumns();
		//read type of data for column and read column name
		asignAttributeNames();
		//read data
		readRecords();		
		//everything is saved into instance variables.
	}

	/**
	 * Reads number of columns there are in the table.
	 * @throws IOException
	 */
	private void readColumns() throws IOException
	{		 seek(0);
	columns =  readShort();//read num of atts
	}

	/**
	 * Reads the names of the columns.
	 * 
	 * @throws IOException
	 */
	private void asignAttributeNames() throws IOException
	{
		//PRE: method readColumns() has been called before.
		seek(2);
		char[] columCharNames = new char[25];
		for(short i=0; i<columns; i++)//for each attribute
		{
			byte ID =  readByte();
			IDs.add((int) ID);//read data type
			nameLength =  readByte(); //read attribute's length 
			for(byte b=0; b<nameLength; b++)//read the attribute's bytes (string)(char[])
			{
				if(columCharNames.length<=b) //if word is too long then increase array size;
				{
					risizeColumCharNames(columCharNames);
				}
				columCharNames[b] =  readChar();//store attributes chars. 
			}
			columNames.add(String.valueOf(columCharNames));//String.valueOf(columCharNames) new String(columCharNames)
		}
	}

	/**
	 * Doubles the size of a character array.
	 * 
	 * @param arr a character array.
	 * @return an array with double the length and same data.
	 */
	private char[] risizeColumCharNames(char[] arr)
	{
		char[] temp = new char[2*arr.length];
		System.arraycopy(arr, 0, temp, 0, arr.length);
		arr = temp;
		return arr;
	}

	/**
	 * Reads the data from each column of the table.
	 * 
	 * @throws IOException
	 */
	private void readRecords() throws IOException {
		while( length() !=  getFilePointer())//while there is more bytes in the document.
		{
			for(short i=0; i<columns; i++)//for the number of attributes
			{
				readAndSaveBytes(IDs.get(i));//get type and read bytes
			}
		}		
	}

	/**
	 * Reads the amount of bytes from a Random Access File according to the 
	 * data type specified for each column.
	 * 
	 * @param ID
	 * @throws IOException
	 */
	private void readAndSaveBytes(int ID) throws IOException {
		switch(ID)
		{
		case 0:
			dataList.add(readByte());
			break;
		case 1:
			dataList.add(readChar());
			break;
		case 2:
			dataList.add(readShort());
			break;
		case 3:
			dataList.add(readInt());
			break;
		case 4:
			dataList.add(readLong());
			break;
		case 5:
			dataList.add(readFloat());
			break;
		case 6:
			dataList.add(readDouble());
			break;
		case 7:
			dataList.add(readBoolean());
			break;
		case 8:
			dataList.add(readDate());
			break;
		}

	}

	/**
	 * Reads a date as a type.
	 * 
	 * @return a date
	 * @throws IOException if there is a problem reading the date.
	 */
	private Date readDate() throws IOException
	{
		return new Date( readByte(),  readByte(),  readShort());
	}

	/**
	 * @return the columns
	 */
	public short getColumns() {
		return columns;
	}

	/**
	 * @return the IDs as Integers
	 */
	public ArrayList<Integer> getIDs() {
		return IDs;
	}

	/**
	 * @return the IDs as Strings
	 */
	public ArrayList<String> getStringIDs() {
		ArrayList<String> list = new ArrayList<>();
		for(int c : IDs)
		{
			list.add(IntToString(c));
		}
		return list;
	}
	/**
	 * Converts and ID from an Integer to it's String equivalent.
	 * 
	 * @param c The byte ID to be converted.
	 * @return The String equivalent of the integer ID.
	 * 			null if it is convertible
	 */
	public String IntToString(int c)
	{
		String s = null;

		if((c == 7)){ s = "boolean"; }
		else if(c == 0){ s = "byte"; }
		else if(c == 1){ s = "char"; }
		else if(c == 6){ s = "double"; }
		else if(c == 5){ s = "float"; }
		else if(c == 3){ s = "int"; }
		else if(c == 4){ s = "long"; }
		else if(c == 2){ s = "short"; }
		else if(c == 8){ s = "date"; }

		return s;
	}

	/**
	 * @return the nameLength
	 */
	public byte getNameLength() {
		return nameLength;
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


}
