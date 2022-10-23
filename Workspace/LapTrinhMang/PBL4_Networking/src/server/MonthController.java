package server;

public class MonthController {
	public static String getNameOfMonth(int month) {
		String months[] = {"January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
               };

		return (months[month-1]);
	}
	
	/*
		Công thức Zeller
	 */
}
