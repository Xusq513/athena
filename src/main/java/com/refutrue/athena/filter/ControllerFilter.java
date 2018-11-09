package com.refutrue.athena.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerFilter implements Filter{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		String requestUrl = httpServletRequest.getRequestURL().toString();
		int responseStatus = httpServletResponse.getStatus();
		Map<String,Object> params = new HashMap<>();
		Enumeration<?> enumeration = httpServletRequest.getParameterNames();
		while(enumeration.hasMoreElements()) {
			Object o = enumeration.nextElement();
			params.put(o.toString(), httpServletRequest.getParameter(o.toString()));
		}
		logger.info("-------请求地址【" + requestUrl + "】请求参数【" + params + "】响应码【" + responseStatus + "】");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
