package com.myplay.model;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminPhone;

    private String adminPhoto;

    private String adminAutograph;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public String getAdminPhoto() {
        return adminPhoto;
    }

    public void setAdminPhoto(String adminPhoto) {
        this.adminPhoto = adminPhoto == null ? null : adminPhoto.trim();
    }

    public String getAdminAutograph() {
        return adminAutograph;
    }

    public void setAdminAutograph(String adminAutograph) {
        this.adminAutograph = adminAutograph == null ? null : adminAutograph.trim();
    }
}