package spring.check;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class MyDatdaCheck{

	private static String val = "0123456789";
	
	public static SimpleDateFormat getDateFormat(String source) { // 用于处理各种日期格式
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source) && checkResource(source,0)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source) && checkResource(source,0)) {
			sdf = new SimpleDateFormat("yyyy/MM/dd");
		} else if(Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source) && checkResource(source,1)){
			sdf = new SimpleDateFormat("yyyyMMdd");
		} else { 
			return null;
		}
		return sdf;
	}

	private static boolean checkResource(String source, int type) {
		int year,month, day;
		if(type == 0) {
			month = (val.indexOf(source.charAt(5))) * 10 + (val.indexOf(source.charAt(6)));
			day = (val.indexOf(source.charAt(8))) * 10 + (val.indexOf(source.charAt(9)));
		} else {
			month = (val.indexOf(source.charAt(4))) * 10 + (val.indexOf(source.charAt(5)));
			day = (val.indexOf(source.charAt(6))) * 10 + (val.indexOf(source.charAt(7)));
		}
		year = val.indexOf(source.charAt(0)) * 1000 + val.indexOf(source.charAt(1)) * 100 + val.indexOf(source.charAt(2)) * 10 + val.indexOf(source.charAt(3));
		if(month > 0 && month < 13 && day > 0 && day <= checkDate(year,month,day)) {
			return true;
		}
		return false;
	}

	private static int checkDate(int year, int month, int day) {
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
			return 31;
		} else if(month==4 || month==6 || month==9 || month==11) {
			return 30;
		} else {
			if(year % 400 == 0 || year % 4 == 0 && year % 100 !=0) 
				return 29;
			else
				return 28;
		}
	}
	
}
