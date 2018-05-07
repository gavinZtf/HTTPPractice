package com.gavin.util;

import java.util.List;

/**
 * @功能 
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月27日
 */
public class SearchFilter {

	private String groupOp;
	
	private List<SearchRule> rules;

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<SearchRule> getRules() {
		return rules;
	}

	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}
	
}
