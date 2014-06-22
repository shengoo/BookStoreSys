package com.cuc.filter;

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

public class CostomerFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpReq=(HttpServletRequest)req;  
        HttpServletResponse httpRes=(HttpServletResponse)res; 
		HttpSession httpSession=httpReq.getSession();  
		if(httpSession.getAttribute("webUser")==null){  
			httpRes.getWriter().println("<SCRIPT LANGUAGE='JavaScript'>alert('ÇëÏÈ×¢²á£¬²¢µÇÂ¼£¡');javascript:history.back(-1);</SCRIPT>");
        }else{  
            chain.doFilter(req, res);  
        }  
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
