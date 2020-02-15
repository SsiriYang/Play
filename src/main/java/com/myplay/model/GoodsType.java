package com.myplay.model;

public class GoodsType {
    private Integer id;

    private String typeName;

    public GoodsType(Object object, String typename2) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

	public GoodsType(Integer id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}
    
}