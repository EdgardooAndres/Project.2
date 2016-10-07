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

	public static void inputAtrributes(){

		final String[] TYPES = {"byte", "char", "short", "int", "long", "float", "double",
				"boolean", "date"};

		Scanner sc = new Scanner(System.in);
		Hashtable<String,String> attributes = new Hashtable<>();
		Set<String> attributeName = attributes.keySet();
		boolean done = false;

		while(!done)
		{
			System.out.println("Next Attribute Name:");
			String name = sc.next();
			System.out.println("Attribute Data Type:");
			String Type = sc.next();
			boolean isType = false;
			for( String types : TYPES)
				if(Type.equalsIgnoreCase(types))
				{
					isType=true;
				}
			if(isType)
			attributes.put(name, Type);
			else System.out.println("invalid data type, input again.age");
			System.out.println("want to add more attributes? 0= yes, 1 =no");
			int more = sc.nextInt();
			if(more == 1)
				done=true;
		}
		done = false;
		while(!done)
		{
			System.out.println("add data");
			for (String name : attributeName) {
				System.out.println(name + "(" + attributes.get(name) +")" );
				switch(attributes.get(name))
				{
				case "int":
					sc.nextInt();
					break;
				case "char":
					if(sc.next().length() != 1)
						//char = leido.getChar(0)
					break;
				case "byte":
					sc.nextByte();
					break;
				case "short":
					sc.nextShort();
					break;
				case "long":
					sc.nextLong();
					break;
				case "float":
					sc.nextFloat();
					break;
				case "double":
					sc.nextDouble();
					break;
				case "boolean":
					sc.nextBoolean();
					break;
				case "date":
					System.out.println("input (mm/dd/yyyy)");
					String month = sc.next();
					int day = sc.nextInt();
					int year = sc.nextInt();
					//usar date del prof.
					break;
				default:
					System.out.println("wrong data type, begin reading process");
				}
				
				//write to file.
			}

			System.out.println("want to add more attributes? 0= yes, 1 =no");
			int more = sc.nextInt();
			if(more == 1)
				done=true;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RandomAccessFile file;
//		inputAtrributes();
		String name = "edgardo", surname = "eggie";
		int age = 21;

		try{
			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
			long FileSize = file.length();
			file.seek(FileSize);
			file.writeUTF(name);;
			for(int i=0; i<20-name.length(); i++)
			{
				file.writeByte(20);
			}

			file.writeUTF(surname);
			for(int i=0; i<20-surname.length(); i++)
			{
				file.writeByte(20);
			}

			file.writeInt(age);

			file.close();
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}


		//----------------------------------------------------------------------

		name = null;
		surname = null;
		age = 0;

		try{

			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
			long FileSize = file.length();
			file.seek(0);
			long NUMRecords = FileSize/44;
			for(int j=0; j<NUMRecords; j++)
			{
				name = file.readUTF();
				for(int i =0; i<20-name.length(); i++)
				{
					file.readByte();
				}

				surname = file.readUTF();
				for(int i =0; i<20-surname.length(); i++)
				{
					file.readByte();
				}

				age = file.readInt();
				System.out.println(name +" "+ surname +" "+ age);
			}

			file.close();

		} catch(IOException e)
		{
			e.getStackTrace();
		}

		//----------------------------------------------------------

		//create a string to search for

		//add an if statement at end of inside for loop
		//that asks if the string searched = a 'name' in the file.

		//print the data you want to print after that string you have found.


		//---------------------------------------------------------------

		//seek(RECORD+j+44)


	}
}
