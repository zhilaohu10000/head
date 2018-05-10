package com.zyzh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 事务信息表
 */
public class Swxx implements Serializable {
    private String id;
    private String swbm;
    private String sw;
    private String bysbh;
    private String swblzt;
    private String fzrbh;
    @JSONField(format="yyyy-MM-dd")
    private Date swblsj;
    public Swxx() {
    }

    public Swxx(String id, String swbm, String sw, String bysbh, String swblzt, String fzrbh, Date swblsj) {
        this.id = id;
        this.swbm = swbm;
        this.sw = sw;
        this.bysbh = bysbh;
        this.swblzt = swblzt;
        this.fzrbh = fzrbh;
        this.swblsj = swblsj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSwbm() {
        return swbm;
    }

    public void setSwbm(String swbm) {
        this.swbm = swbm;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getBysbh() {
        return bysbh;
    }

    public void setBysbh(String bysbh) {
        this.bysbh = bysbh;
    }

    public String getSwblzt() {
        return swblzt;
    }

    public void setSwblzt(String swblzt) {
        this.swblzt = swblzt;
    }

    public String getFzrbh() {
        return fzrbh;
    }

    public void setFzrbh(String fzrbh) {
        this.fzrbh = fzrbh;
    }

    public Date getSwblsj() {
        return swblsj;
    }

    public void setSwblsj(Date swblsj) {
        this.swblsj = swblsj;
    }

    @Override
    public String toString() {
        return "SwxxDao{" +
                "id='" + id + '\'' +
                ", swbm='" + swbm + '\'' +
                ", sw='" + sw + '\'' +
                ", bysbh='" + bysbh + '\'' +
                ", swblzt='" + swblzt + '\'' +
                ", fzrbh='" + fzrbh + '\'' +
                ", swblsj='" + swblsj + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Swxx)) return false;
        Swxx swxx = (Swxx) o;
        return  Objects.equals(getId(), swxx.getId()) &&
                Objects.equals(getSwbm(), swxx.getSwbm()) &&
                Objects.equals(getSw(), swxx.getSw()) &&
                Objects.equals(getBysbh(), swxx.getBysbh()) &&
                Objects.equals(getSwblzt(), swxx.getSwblzt()) &&
                Objects.equals(getFzrbh(), swxx.getFzrbh()) &&
                Objects.equals(getSwblsj(), swxx.getSwblsj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSwbm(), getSw(), getBysbh(), getSwblzt(), getFzrbh(), getSwblsj());
    }
}
