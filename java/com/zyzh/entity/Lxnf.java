package com.zyzh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 离校年份
 */
public class Lxnf implements Serializable{
    private String nf;
    @JSONField(format = "yyyy-MM-dd")
    private Date kqsj;
    @JSONField(format = "yyyy-MM-dd")
    private Date jssj;
    private String xtzt;

    @Override
    public String toString() {
        return "Lxnf{" +
                ", nf='" + nf + '\'' +
                ", kqsj=" + kqsj +
                ", jssj=" + jssj +
                ", xtzt='" + xtzt + '\'' +
                '}';
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public Date getKqsj() {
        return kqsj;
    }

    public void setKqsj(Date kqsj) {
        this.kqsj = kqsj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getXtzt() {
        return xtzt;
    }

    public void setXtzt(String xtzt) {
        this.xtzt = xtzt;
    }

    public Lxnf(String nf, Date kqsj, Date jssj, String xtzt) {


        this.nf = nf;
        this.kqsj = kqsj;
        this.jssj = jssj;
        this.xtzt = xtzt;
    }

    public Lxnf() {

    }
}
