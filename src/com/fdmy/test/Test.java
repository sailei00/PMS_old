package com.fdmy.test;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {
//		Date d = Calendar.getInstance().getTime();
//		long dd = d.getTime() + 86400000;
//		System.out.println(new Date(dd));
		
		double d = 200.866 - 0.111;
		System.out.println(d);
		System.out.println(String.format("%.2f", d));
		System.out.println(Math.rint(d));
		
		DecimalFormat df=new DecimalFormat("0.00");
	    System.out.println(Double.parseDouble(df.format(d)));
	}
	
	
	

}
