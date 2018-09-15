package com.candidjava.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class UtilClasses {
	public String padQuotes(String str) {
		if(str!=null) {
			return str;
		}
		return "";
	}
	
	public static Date convertToDate(String date)  {
		
		SimpleDateFormat sdf=null;
		Date birthDate =null;
		try {
		 sdf = new SimpleDateFormat("yyyy-MM-dd");
		 birthDate = (Date) sdf.parse(date);
		}catch(Exception e) {
			System.out.println(e);
		}
		return birthDate;
	}
	
}
