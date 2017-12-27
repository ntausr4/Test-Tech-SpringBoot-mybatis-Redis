package com.errui.domain;

import java.io.Serializable;

/**
 * @author Kevin
 * @date
 * @comment
 */
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;
    private Long provinceId;  // 郵遞編號
    private String cityName; // 城市名稱
    private String description; // 描述

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
