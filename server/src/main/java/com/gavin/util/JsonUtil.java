package com.gavin.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @功能 
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月27日
 */
public class JsonUtil {

	/**
	 * @功能 
	 * @作者 Gavin
	 */
	public static SearchFilter JSONObjectToSearchFilter(JSONObject jsonObject){
		SearchFilter searchFilter = null;
		JSONArray array = jsonObject.getJSONArray("rules");
		
		List<SearchRule> rules = new ArrayList<SearchRule>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			SearchRule rule = (SearchRule) JSONObject.toBean(object, SearchRule.class);
			rules.add(rule);
		}
		
		String groupOp = jsonObject.getString("groupOp");
		searchFilter = new SearchFilter();
		searchFilter.setGroupOp(groupOp);
		searchFilter.setRules(rules);
		
		return searchFilter;
	}
}
