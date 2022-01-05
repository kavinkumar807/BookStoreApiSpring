package com.codesimple.bookstore.entity;

import com.codesimple.bookstore.common.Constant;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String emailId;
    private Integer phoneNumber;
    private String userType = Constant.USER_TYPE.NORMAL;
    private String password;
    private Boolean isActive = true;
    private Integer loginCount=0;
    private String ssoType;
    private Timestamp loginAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User(){ }

    public User(Long id, String name, String gender, String emailId, Integer phoneNumber, String userType, String password, Boolean isActive, Integer loginCount, String ssoType, Timestamp loginAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.password = password;
        this.isActive = isActive;
        this.loginCount = loginCount;
        this.ssoType = ssoType;
        this.loginAt = loginAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getSsoType() {
        return ssoType;
    }

    public void setSsoType(String ssoType) {
        this.ssoType = ssoType;
    }

    public Timestamp getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Timestamp loginAt) {
        this.loginAt = loginAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onSave(){
        //created at and updated at
        Date date=new Date();
        Timestamp timestampDate = new Timestamp(date.getTime());
        this.createdAt = timestampDate;
        this.updatedAt = timestampDate;
    }
    @PostPersist
    public void onUpdate(){
        Date date=new Date();
        Timestamp timestampDate = new Timestamp(date.getTime());
        this.updatedAt = timestampDate;
    }
}
