package com.zyzh.entity;

import java.io.Serializable;
import java.util.Objects;

public class DataBase implements Serializable{
    private String id;
    private String driver;
    private String url;
    private String username;
    private String passward;
    private String sqlxx;

    public DataBase() {
    }

    public DataBase(String id, String driver, String url, String username, String passward, String sqlxx) {
        this.id = id;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.passward = passward;
        this.sqlxx = sqlxx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public String getSqlxx() {
        return sqlxx;
    }

    public void setSqlxx(String sqlxx) {
        this.sqlxx = sqlxx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataBase)) return false;
        DataBase dataBase = (DataBase) o;
        return Objects.equals(getId(), dataBase.getId()) &&
                Objects.equals(getDriver(), dataBase.getDriver()) &&
                Objects.equals(getUrl(), dataBase.getUrl()) &&
                Objects.equals(getUsername(), dataBase.getUsername()) &&
                Objects.equals(getPassward(), dataBase.getPassward()) &&
                Objects.equals(getSqlxx(), dataBase.getSqlxx());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDriver(), getUrl(), getUsername(), getPassward(), getSqlxx());
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "id='" + id + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", passward='" + passward + '\'' +
                ", sql='" + sqlxx + '\'' +
                '}';
    }
}
