package com.gavin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * @功能 
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月25日
 */
public class CharacterEncodingFilter implements Filter {
	
	private String encode = "UTF-8"; // 默认UTF-8编码  

	/**
	 * @功能 
	 * @作者 Gavin
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @功能 
	 * @作者 Gavin
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) resp;  
        // 设置request编码  
        request.setCharacterEncoding(encode);  
        // 设置相应信息  
        response.setContentType("text/html;charset=" + encode);  
        response.setCharacterEncoding(encode);  
        chain.doFilter(new CharacterEncodingRequest(request), response);
	}

	/**
	 * @功能 
	 * @作者 Gavin
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encoding = filterConfig.getInitParameter("encode");  
        if (encoding != null) {  
            encode = encoding;  
        }  
	}
	
	/** 
	 * 对Get方式传递的请求参数进行编码 
	 */  
	class CharacterEncodingRequest extends HttpServletRequestWrapper { 
		
	    private HttpServletRequest request = null;  
	  
	    public CharacterEncodingRequest(HttpServletRequest request) {  
	        super(request);  
	        this.request = request;  
	    }  
	  
	    /** 
	     * 对参数重新编码 
	     */  
	    @Override  
	    public String getParameter(String name) {  
	        String value = super.getParameter(name);  
	        if(value == null)  
	              return null;  
	         String method = request.getMethod();  
	        if ("get".equalsIgnoreCase(method)) {  
	            try {  
	                value = new String(value.getBytes("ISO8859-1"), request.getCharacterEncoding());  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return value;  
	    }  
	  
	}  

}
