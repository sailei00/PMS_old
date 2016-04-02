package com.fdmy.util;

import java.lang.reflect.Field;

/*
 * @author wulei
 * 系统工具类，集成一些工具方法
 * 
 */
public class Util {
	/*
	 * 利用反射机制，获取任意POJO对象的public字段和字段的非空值 并将其拼接为查询数据库的条件sql
	 * 
	 * 改方法输出的sql为精确查询sql
	 * 
	 * @author wulei
	 * 
	 * @prarm o :
	 */
	public static String getConditionSql(Object o) {
		if (o==null) return null;
		String con_sql = "";
		Field[] s = o.getClass().getDeclaredFields();
		try {
			Field f, fNext;
			for (int i = 0; i < s.length; i++) {
				f = s[i];
				f.setAccessible(true);
				// 拼接字段名称和值
				if (f.get(o) != null && !"".equals(f.get(o))) {
					con_sql = con_sql + f.getName() + "='" + f.get(o) + "'";
				}
				// 如果还有下一个不为null或""的字段，而且当前con_sql不为""，则追加“And”关键字
				//
				if (i + 1 < s.length) {
					fNext = s[i + 1];
					fNext.setAccessible(true);
					if (!"".equals(con_sql) && fNext.get(o) != null && !"".equals(fNext.get(o))) {
						con_sql = con_sql + " and ";
					}
				}
			}
			// SQL以；结尾，增加where 关键字
			if (!"".equals(con_sql)) {
				con_sql = " where " + con_sql + ";";
			} else {
				con_sql = ";";
			}
		} catch (Exception e) {
			System.out.println("构造拼接sql时出错！Class:" + o.getClass());
		}
		System.out.println(o.getClass() + "的拼接查询sql为：" + con_sql);
		return con_sql;
	}
}
