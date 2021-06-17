package kr.ac.kopo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public DateUtils() {
	}

	/**
	 * 현재시간 받아오는 메소드
	 */
	public static String getCurrentTime() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date(System.currentTimeMillis());
		String result = simple.format(date).toString();

		return result;
	}
	
	/**
	 * YYYYMMDDhhmmss 형태의 입력된 날짜를 YYYY-MM-DD hh:mm:ss 형식으로 변환하여 리턴
	 * @param datetime DB에서 가져온 날짜 형식
	 * @return input datetime 형식으로 변환된 날짜값
	 */
	public static String traceDatetime(String datetime){
		String year = "";
		String month = "";
		String day = "";
		String hour = "";
		String minute = "";
		String second = "";
		
		if (datetime == null || datetime.equals("")) {
			return null;
		} else {
		year = datetime.substring(0, 4);
		month = datetime.substring(4, 6);
		day = datetime.substring(6, 8);
		hour = datetime.substring(8, 10);
		minute = datetime.substring(10, 12);
		second = datetime.substring(12, 14);
		datetime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		}
		return datetime;
	}

	
	public static String addchangeDays(String src, int inputDay) {
		
		String inputDate= "";	
		inputDate = src.substring(0, 8);
		
		
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		try {
			cal.setTime(simple.parse(inputDate));
		} catch (ParseException ex) {
		}
		cal.add(GregorianCalendar.DATE, inputDay);
		String result = simple.format(cal.getTime()).toString();

		result = result + "000000";
		
		return result;
	}



	public static String addMonth(String srcMonth, int value){
		
		String text = null;
		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

			Calendar cal = Calendar.getInstance();
			Date date = format.parse(srcMonth);
			
			cal.setTime(date);

			cal.add(Calendar.MONTH, value);

			text = format.format(cal.getTime());
						
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return text;

        
	}
	
	public static boolean addOneMonth(String srcMonth)  {
		
		try {
			Date today;
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
	        Date time = new Date();
	        String time1 = format1.format(time);
	        String time2 = srcMonth.substring(0, 10);
	        
			today = format1.parse(time1);
			Date last_day = format1.parse(time2);
			
			long caldate = today.getTime() - last_day.getTime();
		    long caldatedays = caldate / ( 24*60*60*1000);
		        
		    if (caldatedays > 30) return true;
			return false; 
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		return false;
        //DB 데이터는 이전 정보들만 저장 -> abs 절대값 처리 안해도 계산가능
        
      

        
	}
	
	/**
	 * YYYYMMDDhhmmss 형태의 입력된 날짜를 YYYY-MM-DDThh:mm 형식으로 변환하여 리턴
	 * @param datetime DB에서 가져온 날짜 형식
	 * @return input datetime 형식으로 변환된 날짜값
	 */

	public static String restoreDatetime(String datetime){
		String year = "";
		String month = "";
		String day = "";
		String hour = "";
		String minute = "";
		
		if (datetime == null || datetime.equals("")) {
			return null;
		} else {
		year = datetime.substring(0, 4);
		month = datetime.substring(4, 6);
		day = datetime.substring(6, 8);
		hour = datetime.substring(8, 10);
		minute = datetime.substring(10, 12);
		datetime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
		}
		return datetime;
	}

	
	
}
