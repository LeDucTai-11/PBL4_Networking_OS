package server;

public class MyCalendar {
	
	public static String printCalendar(int year){
		String result = "";
	    int days;
	 
	    // get Day_OF_WEEK of 01/01
	    int current = MyDate.DayOfWeekByZeller(1, 1, year);
	 
	    for (int i = 1; i <= 12; i++)
	    {
	        days = MyDate.numberDaysOfMonth(i, year);
	
	        int k = 0;
	        for (k = 0; k < current; k++)
	        	result += "0-";
	 
	        for (int j = 1; j <= days; j++)
	        {
	        	result += j+"-";
	            if (++k > 6)
	            {
	                k = 0;
	            }
	        }
	        current = k;
	        result += "/";
	    }
	    result += year;
	    return result;
	}
	
}
