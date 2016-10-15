package forTableAnalysis;

public class IDManager {


	/**
	 * Converts and ID from a string to it's byte equivalent.
	 * 
	 * @param s The String to be Converted.
	 * @return The ID corresponding to the given String.
	 * 			-1 if it is convertible
	 */
	public byte StringToInt(String s)
	{
		byte ID = -1;

		if(s.equalsIgnoreCase("boolean")){ ID = 7; }
		else if(s.equalsIgnoreCase("byte")){ ID = 0; }
		else if(s.equalsIgnoreCase("char")){ ID = 1; }
		else if(s.equalsIgnoreCase("double")){ ID = 6; }
		else if(s.equalsIgnoreCase("float")){ ID = 5; }
		else if(s.equalsIgnoreCase("int")){ ID = 3; }
		else if(s.equalsIgnoreCase("long")){ ID = 4; }
		else if(s.equalsIgnoreCase("short")){ ID = 2; }
		else if(s.equalsIgnoreCase("date")){ ID = 8; }

		return ID;
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
}
