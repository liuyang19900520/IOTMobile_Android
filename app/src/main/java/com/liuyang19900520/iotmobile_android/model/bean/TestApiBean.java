package com.liuyang19900520.iotmobile_android.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import lombok.Data;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author liuya
 */
@Data
@Entity
public class TestApiBean {

    @Id
    private Long id;
    private String category;
    private String apiName;
    private String apiUrl;
    private String params;

    @Property(nameInDb = "created_at")
    @NotNull
    private Date createdAt;

    @Property(nameInDb = "updated_at")
    @NotNull
    private Date updatedAt;

    @Generated(hash = 1147156832)
    public TestApiBean(Long id, String category, String apiName, String apiUrl,
            String params, @NotNull Date createdAt, @NotNull Date updatedAt) {
        this.id = id;
        this.category = category;
        this.apiName = apiName;
        this.apiUrl = apiUrl;
        this.params = params;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 327367868)
    public TestApiBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getApiName() {
        return this.apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return this.apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = new Date();
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = new Date();
    }


}
