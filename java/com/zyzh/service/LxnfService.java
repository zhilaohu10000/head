package com.zyzh.service;

import com.zyzh.entity.Lxnf;

import java.util.List;
import java.util.Map;

public interface LxnfService {
    //添加年份
    void addYear(Lxnf year);
    //根据年份，修改开启时间，关闭时间，当前状态
    String modfiyYear(String year, String xtzt);
    //查询年份
    List<Lxnf> queryAllLxnf();
    //查询所有年份
    List<String>queryAllNf();
}
