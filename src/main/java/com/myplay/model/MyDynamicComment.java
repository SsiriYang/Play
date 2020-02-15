package com.myplay.model;

public class MyDynamicComment {
	private Integer id;
	private Integer did;
	private Integer from_uid;
	private String content;
	private String name;
	private String photourl;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFrom_uid() {
		return from_uid;
	}
	public void setFrom_uid(Integer from_uid) {
		this.from_uid = from_uid;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	
	
}
