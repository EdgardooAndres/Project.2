package p2MainClasses;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

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

	public void inputAtrributes(){

		String[] TYPES = {"byte", "char", "short", "int", "long", "float", "double",
				"boolean", "date"};

		//voy a necesitar scanner
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		while(!done)
		{
			System.out.println("Next Attribute Name:");
			String attributeName = sc.nextLine();
			System.out.println("Attribute Data Type:");
			String attributeType = sc.next();
			for( String types : TYPES)
				if(!attributeType.equalsIgnoreCase(types))
				{
					System.out.println("not acceptable data type. reenter.");
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
