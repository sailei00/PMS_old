package com.fdmy.test;

public class Test {

	public static void main(String[] args) {
		// Date d = Calendar.getInstance().getTime();
		// long dd = d.getTime() + 86400000;
		// System.out.println(new Date(dd));

		// double d = 200.866 - 0.111;
		// System.out.println(d);
		// System.out.println(String.format("%.2f", d));
		// System.out.println(Math.rint(d));
		//
		// DecimalFormat df=new DecimalFormat("0.00");
		// System.out.println(Double.parseDouble(df.format(d)));

		int a = 4;
		switch (a) {
		case 0:
			System.out.println("转换成功");
		case 1:
			System.out.println("传入的文件，找不到");
		case 2:
			System.out.println("传入的文件，打开失败");
		case 3:
			System.out.println("转换过程异常失败");
		case 4:
			System.out.println("传入的文件有密码");
		case 5:
			System.out.println("targetFileName的后缀名错误");
		}
	}

}
