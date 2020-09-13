package com.web.assignment.appointment.mapping;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userId;
    private String cholesterol;
    private String triglycerides;
    private String cholesterol_hdl;
    private String cholesterol_non_hdl;
    private String cholesterol_ldl;
    private String cholesterol_vldl;
    private String chol_hdl;
    private String ldl_hdl;
    private String sugar;
    private String pressure;
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private Date lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(String triglycerides) {
        this.triglycerides = triglycerides;
    }

    public String getCholesterol_hdl() {
        return cholesterol_hdl;
    }

    public void setCholesterol_hdl(String cholesterol_hdl) {
        this.cholesterol_hdl = cholesterol_hdl;
    }

    public String getCholesterol_non_hdl() {
        return cholesterol_non_hdl;
    }

    public void setCholesterol_non_hdl(String cholesterol_non_hdl) {
        this.cholesterol_non_hdl = cholesterol_non_hdl;
    }

    public String getCholesterol_ldl() {
        return cholesterol_ldl;
    }

    public void setCholesterol_ldl(String cholesterol_ldl) {
        this.cholesterol_ldl = cholesterol_ldl;
    }

    public String getCholesterol_vldl() {
        return cholesterol_vldl;
    }

    public void setCholesterol_vldl(String cholesterol_vldl) {
        this.cholesterol_vldl = cholesterol_vldl;
    }

    public String getChol_hdl() {
        return chol_hdl;
    }

    public void setChol_hdl(String chol_hdl) {
        this.chol_hdl = chol_hdl;
    }

    public String getLdl_hdl() {
        return ldl_hdl;
    }

    public void setLdl_hdl(String ldl_hdl) {
        this.ldl_hdl = ldl_hdl;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
