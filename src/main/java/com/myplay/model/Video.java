package com.myplay.model;

public class Video {
    private Integer id;

    private String title;

    private String photourl;

    private String videourl;

    private Integer collectioncount;

    private Integer userid;

    private Integer categoryid;

    private Integer status;

    private String createtime;

    private Integer showcount;

    private String vediodetail;

    private Float average;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl == null ? null : photourl.trim();
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl == null ? null : videourl.trim();
    }

    public Integer getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(Integer collectioncount) {
        this.collectioncount = collectioncount;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getShowcount() {
        return showcount;
    }

    public void setShowcount(Integer showcount) {
        this.showcount = showcount;
    }

    public String getVediodetail() {
        return vediodetail;
    }

    public void setVediodetail(String vediodetail) {
        this.vediodetail = vediodetail == null ? null : vediodetail.trim();
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }
}