package server;

public class MyDate {
	
	public static int numberDaysOfMonth(int month,int year) {
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month ==12) {
			return 31;
		}else if (month == 2) {
			if (MyYear.check_leap_year(year))
	            return 29;
	        else
	            return 28;
		}else {
			return 30;
		}
	}
	
	public String getNameDayOfWeek(int dayOfWeek) {
		String days[] = {"Sunday","Monsday", "Tuesday", "Wednesday",
                "Thusday", "Friday", "Saturday",
                };

		return (days[dayOfWeek]);
	}
	public static int DayOfWeekByZeller(int d,int m,int y) {
		y -= (14-m)/12;
	    m += 12*((14-m)/12)-2;
	    return (d + y + y/4 - y/100 + y/400 + (31*m)/12)%7;
	}
	
	
}
