package com.zyzh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * 毕业生离校事务信息
 */
public class BysSwxx {
    private String xh;
    private String xm;
    private String yx;
    private String zy;
    private String xn;
    private String bj;
    private String byzlqzt;
    @JSONField(format = "yyyy-MM-dd")
    private Date byzlqsj;
    private String swbm;
    private String sw;
    private String bysbh;
    private String swblzt;
    private String fzrbh;
    @JSONField(format = "yyyy-MM-dd")
    private Date swblsj;

    public BysSwxx() {
    }

    public BysSwxx(String xh, String xm, String yx, String zy, String xn, String bj, String byzlqzt, Date byzlqsj, String swbm, String sw, String bysbh, String swblzt, String fzrbh, Date swblsj) {

        this.xh = xh;
        this.xm = xm;
        this.yx = yx;
        this.zy = zy;
        this.xn = xn;
        this.bj = bj;
        this.byzlqzt = byzlqzt;
        this.byzlqsj = byzlqsj;
        this.swbm = swbm;
        this.sw = sw;
        this.bysbh = bysbh;
        this.swblzt = swblzt;
        this.fzrbh = fzrbh;
        this.swblsj = swblsj;
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
        return "BysSwxx{" +
                "xh='" + xh + '\'' +
                ", xm='" + xm + '\'' +
                ", yx='" + yx + '\'' +
                ", zy='" + zy + '\'' +
                ", xn='" + xn + '\'' +
                ", bj='" + bj + '\'' +
                ", byzlqzt='" + byzlqzt + '\'' +
                ", byzlqsj=" + byzlqsj +
                ", swbm='" + swbm + '\'' +
                ", sw='" + sw + '\'' +
                ", bysbh='" + bysbh + '\'' +
                ", swblzt='" + swblzt + '\'' +
                ", fzrbh='" + fzrbh + '\'' +
                ", swblsj=" + swblsj +
                '}';
    }
}
