package com.myplay.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AllowOriginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse)res;
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");  
	        response.setHeader("Access-Control-Max-Age", "3600");  
	        //response.setHeader("Access-Control-Allow-Headers", "x-requested-with"); 
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
	        response.setHeader("Access-Control-Allow-Credentials","true");//是否允许浏览器携带用户身份信息（cookie）
	        System.out.println("*********************************过滤器被使用**************************");  
	        chain.doFilter(arg0, response);  		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
