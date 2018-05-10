package com.zyzh.entity;

import java.io.Serializable;
import java.util.List;

public class Paramter implements Serializable{
    private String id;
    private String pid;
    private String text;
    private String leaf;
    private boolean expanded;
    private String url;
    private List<Paramter> children;

    public Paramter() {
    }

    public Paramter(String id, String pid, String text, String leaf, boolean expanded, String url, List<Paramter> children) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.leaf = leaf;
        this.expanded = expanded;
        this.url = url;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Paramter> getChildren() {
        return children;
    }

    public void setChildren(List<Paramter> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Paramter{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", leaf='" + leaf + '\'' +
                ", expanded=" + expanded +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }
}
