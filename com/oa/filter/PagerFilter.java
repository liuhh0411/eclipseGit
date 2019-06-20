package com.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.oa.SystemContext;

public class PagerFilter implements Filter {

	public void destroy() {
		

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		
		SystemContext.setOffset(this.getOffset(request));
		SystemContext.setPagesize(this.getPagesize(request));
		
		try {
			arg2.doFilter(arg0, arg1);
		} finally {
			//Çå¿Õ
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
		

	}
	
	protected int getOffset(HttpServletRequest request) {
		int offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
			System.out.println("filter:pager.offset:" + offset);
		} catch (NumberFormatException e) {
			
			//e.printStackTrace();
		}
		
		return offset;
	}
	
	protected int getPagesize(HttpServletRequest request) {
		return 5;
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
