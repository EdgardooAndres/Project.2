package p2MainClasses;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * This class initiates the execution of the application that 
 * allows the user to create a new table and save it in a corresponding
 *  data file. It also allows to add new data into an existing data file 
 *  that complies with the discussed specification.
 * 
 * @author Edgardo Muniz
 *
 */
public class DataFilePopulator {

	static Scanner sc = new Scanner(System.in);
	static Hashtable<String,String> attributes = new Hashtable<>();
	static Set<String> attributeName;
	private static int DAYSPERMONTH[] = {31, 28, 31, 30, 31, 30, 
			31, 31, 30, 31, 30, 31};
	final static String[] TYPES = {"byte", "char", "character", "short", "int", "integer", 
			"long", "float", "double", "boolean", "date"};
	static RandomAccessFile file;
	static boolean done = false;

	public static void inputAtrributes(){

		try {
			while (!done) {
				System.out.println("Next Attribute Name:");
				String name = sc.next();
				System.out.println("Attribute Data Type:");
				String Type = sc.next();
				boolean isType = false;
				for (String types : TYPES)
					if (Type.equalsIgnoreCase(types)) {
						isType = true;
					}
				if (isType)
					attributes.put(name, Type);
				else
					System.out.println("invalid data type, input again.age");
				System.out.println("");
				System.out.println("want to add more attributes? 1 = yes, 0 = no");
				int more = sc.nextInt();
				if (more == 0)
					done = true;
			} 
		} catch (Exception e) {
			System.out.println("invalid input");
		}
	}
	public static void addData(){
		done = false;
		attributeName = attributes.keySet();
		try {
			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
			while (!done) {
				System.out.println("add data");
				for (String name : attributeName) {
					System.out.println(name + "(" + attributes.get(name) + ")");
					switch (attributes.get(name)) {
					case "int":
							file.write(sc.nextInt());
						break;
					case "char":
						file.writeChar(sc.next().charAt(0));
							break;
					case "byte":
						file.write(sc.nextByte());
						break;
					case "short":
						file.write(sc.nextShort());
						break;
					case "long":
						file.writeLong(sc.nextLong());
						break;
					case "float":
						file.writeFloat(sc.nextFloat());
						break;
					case "double":
						file.writeDouble(sc.nextDouble());
						break;
					case "boolean":
						file.writeBoolean(sc.nextBoolean());
						break;
					case "date":
						System.out.println("input (mm/dd/yyyy)");
						byte month = sc.nextByte();
						byte day = sc.nextByte();
						short year = sc.nextShort();
						if(isDateValid(day,month,year))
							writeDate(day, month, year);
							
							break;
					}
				}
				System.out.println("want to add more data? 1 = yes, o = no");
				int more = sc.nextInt();
				if(more == 0)
					done=true;
			} 
			file.close();
		} catch (Exception e) {
			System.out.println("Invalid Type, try again.");
		}
	}

	private static void writeDate(byte day, byte month, short year) {
		try {
			file.writeByte(month);
			file.writeByte(day);
			file.writeShort(year);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean isDateValid(byte day, byte month, short year) {
		if(isYearValid(year))
			if(isMonthValid(month))
				if (isDayValid(day, month))
					return true;

		return false;
	}
	private static boolean isYearValid(short year) {
		if(year>0)
			return true;
		System.out.println("the year is not valid.");
		return false;

	}
	private static boolean isMonthValid(byte month) {
		if(month>0 && month<=12)
			return true;
		System.out.println("the month is not a valid month.");
		return false;
	}
	private static boolean isDayValid(byte day, byte month) {
		// TODO Auto-generated method stub
		if(month == 2)
		{
			if(month%4 == 0)
			{
				if(day>0 && day<=29)
					return true;
			}
		}
		else if(day>0 && day<=29)
			return true;

		return false;
	}
	
	
	public static void main(String[] args) {

		inputAtrributes();
		addData();
//		String name = "edgardo", surname = "eggie";
//		int age = 21;
		//file = new RandomAccessFile(new File("inputData", "Data"), "rw");
//		try{
//			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
//			long FileSize = file.length();
//			file.seek(FileSize);
//			file.writeUTF(name);;
//			for(int i=0; i<20-name.length(); i++)
//			{
//				file.writeByte(20);
//			}
//
//			file.writeUTF(surname);
//			for(int i=0; i<20-surname.length(); i++)
//			{
//				file.writeByte(20);
//			}
//
//			file.writeInt(age);
//
//			file.close();
//		}
//		catch(IOException e)
//		{
//			e.getStackTrace();
//		}


		//----------------------------------------------------------------------

//		try{
//
//			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
//			long FileSize = file.length();
//			file.seek(0);
//			long NUMRecords = FileSize/44;
//			for(int j=0; j<NUMRecords; j++)
//			{
//				name = file.readUTF();
//				for(int i =0; i<20-name.length(); i++)
//				{
//					file.readByte();
//				}
//
//				surname = file.readUTF();
//				for(int i =0; i<20-surname.length(); i++)
//				{
//					file.readByte();
//				}
//
//				age = file.readInt();
//				System.out.println(name +" "+ surname +" "+ age);
//			}

//			file.close();

				//		} catch(IOException e)
				//		{
				//			System.out.println(e.getStackTrace());
				//		}

		//----------------------------------------------------------

		//create a string to search for

		//add an if statement at end of inside for loop
		//that asks if the string searched = a 'name' in the file.

		//print the data you want to print after that string you have found.


		//---------------------------------------------------------------

		//seek(RECORD+j+44)


	}
}
