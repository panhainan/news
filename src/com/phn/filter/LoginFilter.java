package com.phn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-29
 */
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request  = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		String adminName = (String) session.getAttribute("adminName");
		System.out.println(adminName);
		if(null==adminName){
			HttpServletResponse response = (HttpServletResponse)resp;
			System.out.println("拦截成功！");
			request.getRequestDispatcher("/WEB-INF/back/login.jsp").forward(request,
					response);
		}else{
			System.out.println("通过！");
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
