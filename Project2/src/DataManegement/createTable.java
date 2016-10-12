package DataManegement;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class createTable {
	short columns;
	ArrayList<Byte> IDs = new ArrayList<>(); 
	byte nameLength;
	char[] columNames = new char[25];
	RandomAccessFile file;

	private void readColumns(RandomAccessFile file) throws IOException
	{		file.seek(0);
	columns = file.readShort();//read num of atts
	}

	private void asignAttributeNames(RandomAccessFile file) throws IOException
	{
		//PRE: method readColumns(file) has been called before.
		file.seek(2);
		for(short i=0; i<columns; i++)//for each att
		{
			byte ID = file.readByte();
			IDs.add(ID);//read data type
			nameLength = file.readByte(); //read att's length 
			for(byte b=0; b<nameLength; b++)//read the att's bytes (string)(char[])
			{
				columNames[b] = file.readChar();//store atts names 
				System.out.print(columNames[b]);
			}
		}
	}

	private void colectData(RandomAccessFile file) throws IOException
	{
		while(file.length() != file.getFilePointer())//while mas data //file length != getFilePointer()
		{
			for(short i=0; i<columns; i++)//for num de atts
			{
				readAndPrintBytes(IDs.get(i), file);//get type and read bytes
				System.out.println("");
			}
		}
	}

	private void readAndPrintBytes(byte ID, RandomAccessFile file) throws IOException {
		switch(ID)
		{
		case 0:
			System.out.print(file.readByte());
			break;
		case 1:
			System.out.print(file.readChar());
			break;
		case 2:
			System.out.print(file.readShort());
			break;
		case 3:
			System.out.print(file.readInt());
			break;
		case 4:
			System.out.print(file.readLong());
			break;
		case 5:
			System.out.print(file.readFloat());
			break;
		case 6:
			System.out.print(file.readDouble());
			break;
		case 7:
			System.out.print(file.readBoolean());
			break;
		case 8:
			System.out.print(file.readByte()+ "/" + file.readByte() +
					"/" + file.readShort());
			break;
		}

	}

	public short getColumns() {
		return columns;
	}

	public byte getID(int index) {
		return IDs.get(index);
	}

	public byte getNameLength() {
		return nameLength;
	}

	public String getColumNames() {
		return columNames.toString();
	}

	public void printTable(RandomAccessFile file) throws IOException {
		// TODO Auto-generated method stub
		readColumns(file);
		asignAttributeNames(file);
		System.out.println("");
		colectData(file);
	}
}