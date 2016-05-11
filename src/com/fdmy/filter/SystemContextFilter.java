package com.fdmy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.fdmy.util.SystemContext;

public class SystemContextFilter implements Filter {
	int pageSize = 20;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		int offset = 0;
		try {
			offset = Integer.parseInt(req.getParameter("pager.offset"));
		} catch (NumberFormatException e) {
		}
		try {
			SystemContext.setPageNo(offset / pageSize + 1);
			SystemContext.setOffset(offset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SystemContext.removeAll();
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			pageSize = 20;
		} 
	}

}
