package DataManegement;

import DataManegement.Date;

public class ValidTypes {
	public  boolean isValidInt(String s) { 
		try { 
			Integer.parseInt(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidBoolean(String s) {
		try {
			Boolean.parseBoolean(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidLong(String s) {
		try {
			Long.parseLong(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidShort(String s) {
		try {
			Short.parseShort(s);
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidChar(String s) {
		return s.length() == 1; 
	}

	public  boolean isValidByte(String s) {
		try {
			Byte.parseByte(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidFloat(String s) {
		try {
			Float.parseFloat(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	public  boolean isValidDouble(String s) {
		try {
			Double.parseDouble(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}


	public  boolean isValidDate(byte month, byte day, short year) { 
		if (year < 0)
			return false;
		if (month < 1 || month > 12) 
			return false; 

		int maxDays; 
		if (month == 2 && year % 4 == 0)   // month February on a leap year....
			maxDays = 29; 
		else 
			maxDays = Date.nDays(month-1); 
		if (day < 1 || day > maxDays)
			return false; 		

		// if it reaches here, then the date is valid as per the specs given
		return true;
	}
}
