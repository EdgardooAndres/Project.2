package DataManegement;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Write {
	RandomAccessFile file;
	public void Write(E data)
	{
		try{
			file = new RandomAccessFile(new File("inputData", "Data"), "rw");
			long FileSize = file.length();
			file.seek(FileSize);
			file.write(data);;
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

	}

}
