package com.fdmy.util;

public class SystemContext {
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageNo = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> total = new ThreadLocal<Integer>();

	public static int getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(int pageSize) {
		SystemContext.pageSize.set(pageSize);
	}

	public static int getPageNo() {
		return pageNo.get();
	}

	public static void setPageNo(int pageNo) {
		SystemContext.pageNo.set(pageNo);
	}

	public static int getOffset() {
		return offset.get();
	}

	public static void setOffset(int pageIndex) {
		SystemContext.offset.set(pageIndex);
	}

	public static int getTotal() {
		return total.get();
	}

	public static void setTotal(int total) {
		SystemContext.total.set(total);
	}

	public static void removePageSize() {
		pageSize.remove();
	}

	public static void removePageNo() {
		pageNo.remove();
	}

	public static void removeOffset() {
		offset.remove();
	}

	public static void removeTotal() {
		total.remove();
	}

	public static void removeAll() {
		pageSize.remove();
		pageNo.remove();
		offset.remove();
		total.remove();
	}
}
