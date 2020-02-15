package com.myplay.model;

import java.util.HashMap;
import java.util.Map;

public class AdminData {
	
	//状态码
	private Integer code;
	//提示信息
	private String msg;
	//交互数据
	private Map<String ,Object> data = new HashMap<String ,Object>();
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
	//执行成功方法
	public static AdminData success(){
		AdminData message = new AdminData();
		message.setCode(100);
		message.setMsg("执行成功");
		return message;
	}
	//执行失败方法
	public static AdminData fail(){
		AdminData message = new AdminData();
		message.setCode(110);
		message.setMsg("执行失败");
		return message;
	}
	//添加jeson数据方法
	public AdminData add(String key,Object value){
		this.getData().put(key, value);
		return this;
	}
	
	
}
