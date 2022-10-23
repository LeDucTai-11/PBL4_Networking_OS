package server;

public class YearController {

	public static boolean check_leap_year(int year) {
		if (year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0)) {
			return true;
		}
		return false;
	}
}
