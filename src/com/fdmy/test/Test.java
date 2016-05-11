package com.fdmy.test;

import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date d = Calendar.getInstance().getTime();
		long dd = d.getTime() + 86400000;
		System.out.println(new Date(dd));
	}
	
	
	

}
