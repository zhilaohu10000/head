package com.zyzh.entity;

import java.io.Serializable;

/**
 * 负责人
 */
public class Fzr implements Serializable{
    private String id;
    private String szbm;
    private String bksfzr;
    private String yjsfzr;
    private String znbmfzr;
    private String qx;
    private String flbh;

    public Fzr() {
    }

    public Fzr(String id, String szbm, String bksfzr, String yjsfzr, String znbmfzr, String qx, String flbh) {
        this.id = id;
        this.szbm = szbm;
        this.bksfzr = bksfzr;
        this.yjsfzr = yjsfzr;
        this.znbmfzr = znbmfzr;
        this.qx = qx;
        this.flbh = flbh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSzbm() {
        return szbm;
    }

    public void setSzbm(String szbm) {
        this.szbm = szbm;
    }

    public String getBksfzr() {
        return bksfzr;
    }

    public void setBksfzr(String bksfzr) {
        this.bksfzr = bksfzr;
    }

    public String getYjsfzr() {
        return yjsfzr;
    }

    public void setYjsfzr(String yjsfzr) {
        this.yjsfzr = yjsfzr;
    }

    public String getZnbmfzr() {
        return znbmfzr;
    }

    public void setZnbmfzr(String znbmfzr) {
        this.znbmfzr = znbmfzr;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getFlbh() {
        return flbh;
    }

    public void setFlbh(String flbh) {
        this.flbh = flbh;
    }

    @Override
    public String toString() {
        return "Fzr{" +
                "id='" + id + '\'' +
                ", szbm='" + szbm + '\'' +
                ", bksfzr='" + bksfzr + '\'' +
                ", yjsfzr='" + yjsfzr + '\'' +
                ", znbmfzr='" + znbmfzr + '\'' +
                ", qx='" + qx + '\'' +
                ", flbh='" + flbh + '\'' +
                '}';
    }
}
