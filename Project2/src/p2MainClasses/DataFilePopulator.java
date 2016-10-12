package p2MainClasses;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import DataManegement.Attribute;
import DataManegement.ValidTypes;
import DataManegement.createTable;

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

	//instance variables.
	private static ArrayList<Attribute> attributes = new ArrayList<>(); //this a set to store 'attributeAndType' keySet, which are the attribute names.
	public static final String[] TYPES = {"byte", "char", "short", "int", 
			"long", "float", "double", "boolean", "date"}; //acceptable data types.
	private static RandomAccessFile file; //file to write to and read from.
	private static short attsNum;
	private static byte ID;


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		//ask file name to know if it exists
		System.out.println("Input File Name:");
		String name = sc.nextLine();
		CreateFile(name);

		if(!fileExisted())//if file has nothing then its new and run normally,
		{
			file.seek(0);
			addAttributesAndTypes();//ask attributes and data types
		}
		else//if file has something then its old and just add data
		{
			file.seek(0);
			attsNum = file.readShort();
			for(int i=0; i<attsNum; i++)
				attributes.add(new Attribute(file));
		}

		addData();//ask for data

		sc.close();

		//read from file

		//print in table form.
		printTable();
	}

	private static boolean fileExisted() {
		try {
			if(file.length()==0)
				return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void CreateFile(String name)
	{
		//check if it exists
		try{
			file = new RandomAccessFile(new File("InputData", name), "rw");
		}catch(Exception e){
			System.out.println("File Path not found.");
		}
		//if not create a file.
	}

	public static void addAttributesAndTypes()
	{
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		while(!done)
		{
			//ask attribute name
			System.out.println("Input name of Attribute:");
			String name = sc.nextLine();

			//add data type
			System.out.println("input it's Type:");
			String type = sc.nextLine();
			//check if type is valid.
			if(isValidType(type))
			{
				//save in system.
				attributes.add(new Attribute(name, getID(type)));
			}
			else
				System.out.println("Not a valid type. Try again");

			System.out.println("");
			System.out.println("want to add more attributes? Enter YES or NO.");
			String decision = sc.nextLine();
			if(decision.equalsIgnoreCase("NO"))
				done = true;
		}
		attsNum = (short) attributes.size();
		//write them to file.
		try {
			writeAttributesToFile();
		} catch (IOException e) {
			System.out.println("error writing attributes to file");
		}
	}

	private static int getID(String type) {
		if(type.equalsIgnoreCase("boolean")){
			ID = 7;
		}
		else if(type.equalsIgnoreCase("byte")){
			ID = 0;
		}
		else if(type.equalsIgnoreCase("char")){
			ID = 1;
		}
		else if(type.equalsIgnoreCase("double")){
			ID = 6;
		}
		else if(type.equalsIgnoreCase("float")){
			ID = 5;
		}
		else if(type.equalsIgnoreCase("int")){
			ID = 3;
		}
		else if(type.equalsIgnoreCase("long")){
			ID = 4;
		}
		else if(type.equalsIgnoreCase("short")){
			ID = 2;
		}
		else if(type.equalsIgnoreCase("date"))
			ID = 8;

		return ID;
	}

	private static boolean isValidType(String type) {
		boolean valid = false;

		for (String types : TYPES) {
			if(type.equalsIgnoreCase(types))
				valid = true;
		}
		if(!valid){
			System.out.println("Invalid type. Try one of this:");
			for (String types : TYPES) {
				System.out.print(types + ", ");
			}
		}
		return valid;
	}

	public static void addData()
	{
		//add data in same order as attributes were inputed.
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		System.out.printf("input the data for each of the attributes.%n%n");
		while(!done){
			for(Attribute attType : attributes)
			{
				int id = attType.gettIndex();
				String type = IDtoString(id);
				System.out.println(attType.getName() + "(" + type + ")"   );

				if(!type.equalsIgnoreCase("date"))
				{
					String data = sc.nextLine();

					boolean compatible = writeData(type, data);
					while(!compatible)
					{
						System.out.println("--Wrong Type--");
						System.out.println(attType.getName() + "(" + type + ")"   );
						data = sc.nextLine();
						compatible = writeData(type, data);
					}
					//find what it is. and convert data to its type. and write data to file.
					//writeData(type, data);

				}
				else 
				{
					System.out.println("PLEASE USE NUMBRES");
					System.out.println("dd");
					byte day = sc.nextByte();
					System.out.println("mm");
					byte month = sc.nextByte();
					System.out.println("yyyy");
					short year = sc.nextShort();

					boolean compatible = isValidDate(day,month,year);
					while(!compatible)
					{
						System.out.println("--Invalid Date--");
						System.out.println(attType + "(" + type + ")"   );
						System.out.println("dd");
						day = sc.nextByte();
						System.out.println("mm");
						month = sc.nextByte();
						System.out.println("yyyy");
						year = sc.nextShort();
						compatible = isValidDate(day,month,year);
					}
					//write date to file.
					writeDateToFile(day,month,year);
				}
			}
			System.out.println("");
			System.out.println("want to add more data? Enter YES or NO.");
			String decision = sc.nextLine();
			if(decision.equalsIgnoreCase("NO"))
				done = true;
		}
	}

	private static void writeDateToFile(byte day, byte month, short year) {
		try {	
			file.writeByte(month);
			file.writeByte(day);
			file.writeShort(year);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean writeData(String type, String data) {
		try {
			switch (type) {
			case "byte":
				byte byteData = Byte.parseByte(data);
				file.writeByte(byteData);
				return true;
			case "char":
				char charData = type.charAt(0);
				file.writeChar(charData);
				return true;
			case "short":
				short shortData = Short.parseShort(data);
				file.writeShort(shortData);
				return true;
			case "int":
				int intData = Integer.parseInt(data);
				file.writeInt(intData);
				return true;
			case "long":
				long longData = Long.parseLong(data);
				file.writeLong(longData);
				return true;
			case "float":
				float floatData = Float.parseFloat(data);
				file.writeFloat(floatData);
				return true;
			case "double":
				double doubleData = Double.parseDouble(data);
				file.writeDouble(doubleData);
				return true;
			case "boolean":
				boolean booleanData = Boolean.parseBoolean(data);
				file.writeBoolean(booleanData);
				return true;
			}
		} catch (Exception e) {
			System.out.println("something with writing to the file");
			return false;
		}
		return false;
	}

	private static String IDtoString(int ID) {
		if(ID == 7){
			return "boolean";
		}
		else if(ID == 0){
			return "byte";
		}
		else if(ID == 1){
			return "char";
		}
		else if(ID == 6){
			return "double";
		}
		else if(ID == 5){
			return "float";
		}
		else if(ID == 3){
			return "int";
		}
		else if(ID == 4){
			return "long";
		}
		else if(ID == 2){
			return "short";
		}
		else if(ID == 8)
			return "date";
		return null;
	}

	private static boolean isValidDate(byte day, byte month, short year) {
		// TODO Auto-generated method stub
		ValidTypes vt = new ValidTypes();
		if(vt.isValidDate(month, day, year))
			return true;
		return false;
	}

	private static boolean isCompatibleType(String data) {
		// TODO Auto-generated method stub
		ValidTypes vt = new ValidTypes();
		if(vt.isValidBoolean(data) || vt.isValidByte(data) ||  vt.isValidChar(data) ||
				vt.isValidDouble(data) || vt.isValidFloat(data) || vt.isValidInt(data) ||
				vt.isValidLong(data) || vt.isValidShort(data))
			return true;

		return false;
	}

	public static void writeAttributesToFile() throws IOException
	{
		file.writeShort(attributes.size());
		for (int i=0; i<attributes.size(); i++) {
			attributes.get(i).writeToFile(file);
		}

	}

	public static void printTable() throws IOException
	{
		createTable table = new createTable();
		table.printTable(file);
	}

}