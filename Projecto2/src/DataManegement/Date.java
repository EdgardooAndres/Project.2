package DataManegement;

public class Date {

	private static int NDAYSPERMONTH[] = {31, 28, 31, 30, 31, 30, 
			31, 31, 30, 31, 30, 31}; 

	byte day, month;
	short year;
	public Date(byte day, byte month, short year)
	{
		this.day = day;
		this.month = month;
		this.year = year;

		//check if its valid 

	}

	public static int nDays(int month) { 
		return NDAYSPERMONTH[month]; 
	}
}
