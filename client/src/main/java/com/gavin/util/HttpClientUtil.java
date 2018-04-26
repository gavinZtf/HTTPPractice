package com.gavin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @功能  发送HTTP请求的工具类
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月25日
 */
public class HttpClientUtil {

	/**
	 * @功能 HttpClient的请求get
	 * @作者 Gavin
	 */
	public static String doGet(String url, String charSet) throws Exception{
		// 生成httpClient对象，并设置请求的参数
		HttpClient httpClient = new HttpClient();
		// 设置http的连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		// 生成getMethod对象，并且设置请求的参数
		GetMethod getMethod = new GetMethod(url);
		//设置getmethond的请求时间为5秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		String response = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			//判断返回的状态码
			if(statusCode != HttpStatus.SC_OK){
				System.out.println("请求出错：========》" + getMethod.getStatusLine());
			}
			
			//处理Http响应的内容
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers) {
				System.out.println(h.getName() + "---------" + h.getValue());
			}
			//读取http响应的内容
			byte[] responseBody = getMethod.getResponseBody();
			response = new String(responseBody, charSet);
			System.out.println("---------response:" + response);
			
			// 读取为 InputStream，在网页内容数据量大时候推荐使用
			// InputStream response = getMethod.getResponseBodyAsStream();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
		    // 发生网络异常
		    System.out.println("发生网络异常!");
		    e.printStackTrace();
		} finally {
			// 释放连接 
		    getMethod.releaseConnection();
		}
		
		return response;
	}
	
	/**
	 * @功能 post请求
	 * @作者 Gavin
	 */
	@SuppressWarnings({ "deprecation", "resource", "unused" })
	public static JSONObject doPost(String url, JSONObject json){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
		        response = JSONObject.fromObject(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * @功能  测试
	 * @作者 Gavin
	 */
	public static void main(String[] args) {
		String url = "http://localhost:8080/server/HelloWorld";
		JSONObject params = new JSONObject();
		params.put("SRC_STM_CODE", "wsf");
		params.put("TENANT_ID", "123");
		params.put("NM", "张三");
		params.put("BRTH_DT", "1983-01-20");
		params.put("GND_CODE", "1");
		JSONArray params2 = new JSONArray();
		JSONObject param3 = new JSONObject();
		param3.put("DOC_TP_CODE", "10100");
		param3.put("DOC_NBR", "100200198301202210");
		param3.put("DOC_CUST_NM", "test");
		params2.add(param3);
		params.put("DOCS", params2);
		String ret = doPost(url, params).toString();
		System.out.println(ret);
	}
}
