package com.gavin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.sf.json.JSONObject;

/**
 * @功能 
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月25日
 */
public class HelloWorld extends HttpServlet {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 7646768850773720090L;
	
	private String message;
	
	private static Logger logger = LogManager.getLogger(HelloWorld.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取json的数据
		String srcStmCode = req.getParameter("SRC_STM_CODE");
		
		SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = spdf.format(new Date());
		
		logger.info(now + "======>" + srcStmCode);
		//JSON对象
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("password", "123").accumulate("name", "www");
		resp.setContentType("application/json");
		resp.getWriter().write(jsonObject.toString());
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		message = "Hello World";
	}
	
}
