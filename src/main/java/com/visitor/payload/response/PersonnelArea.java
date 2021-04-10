package com.visitor.payload.response;

public class PersonnelArea {
    private Integer id;
    private String areaCode;
    private String areaName;
    private boolean isDefault;
    private String parentAreaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(String parentAreaId) {
        this.parentAreaId = parentAreaId;
    }
}
