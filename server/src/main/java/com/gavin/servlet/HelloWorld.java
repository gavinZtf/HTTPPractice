package com.gavin.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gavin.util.JsonUtil;
import com.gavin.util.SearchFilter;

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
		logger.debug("开始获取传过来的Json参数");
		
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				sb = sb.append(line);
			}
		} catch (Exception e) {
			throw new IOException("parse request error");
		}
		
		JSONObject json = null;
		try {
			json = JSONObject.fromObject(sb.toString());
			logger.debug("收到的json数据如下==》" + json);
		} catch (Exception e) {
			throw new IOException("Error parsing JSON request string");
		}
		
//		json
		SearchFilter jsonObjectToSearchFilter = JsonUtil.JSONObjectToSearchFilter(json);
		logger.debug("收到的SearchFilter数据如下======》" + json);
		
		// Work with the data using methods like...
		// int someInt = jsonObject.getInt("intParamName");
		// String someString = jsonObject.getString("stringParamName");
		// JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
		// JSONArray arr = jsonObject.getJSONArray("arrayParamName");
		// etc...
		
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
