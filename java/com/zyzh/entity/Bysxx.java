package com.zyzh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 毕业生信息
 */
public class Bysxx implements Serializable {
    private String xh;
    private String xm;
    private String yx;
    private String zy;
    private String xn;
    private String bj;
    private String byzlqzt;
    @JSONField(format = "yyyy-MM-dd")
    private Date byzlqsj;

    public Bysxx() {
    }

    public Bysxx(String xh, String xm, String yx, String zy, String xn, String bj, String byzlqzt, Date byzlqsj) {
        this.xh = xh;
        this.xm = xm;
        this.yx = yx;
        this.zy = zy;
        this.xn = xn;
        this.bj = bj;
        this.byzlqzt = byzlqzt;
        this.byzlqsj = byzlqsj;

    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getByzlqzt() {
        return byzlqzt;
    }

    public void setByzlqzt(String byzlqzt) {
        this.byzlqzt = byzlqzt;
    }

    public Date getByzlqsj() {
        return byzlqsj;
    }

    public void setByzlqsj(Date byzlqsj) {
        this.byzlqsj = byzlqsj;
    }

    @Override
    public String toString() {
        return "Bysxx{" +
                "xh='" + xh + '\'' +
                ", xm='" + xm + '\'' +
                ", yx='" + yx + '\'' +
                ", zy='" + zy + '\'' +
                ", xn='" + xn + '\'' +
                ", bj='" + bj + '\'' +
                ", byzlqzt='" + byzlqzt + '\'' +
                ", byzlqsj=" + byzlqsj +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bysxx)) return false;
        Bysxx bysxx = (Bysxx) o;
        return Objects.equals(getXh(), bysxx.getXh()) &&
                Objects.equals(getXm(), bysxx.getXm()) &&
                Objects.equals(getYx(), bysxx.getYx()) &&
                Objects.equals(getZy(), bysxx.getZy()) &&
                Objects.equals(getXn(), bysxx.getXn()) &&
                Objects.equals(getBj(), bysxx.getBj()) &&
                Objects.equals(getByzlqzt(), bysxx.getByzlqzt()) &&
                Objects.equals(getByzlqsj(), bysxx.getByzlqsj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getXh(), getXm(), getYx(), getZy(), getXn(), getBj(), getByzlqzt(), getByzlqsj());
    }
}


