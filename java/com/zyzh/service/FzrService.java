package com.zyzh.service;

import com.zyzh.entity.Fzr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FzrService {
    //添加负责人
    void addFzr(Fzr fzr);
    //查询所有院系负责人
    List<Fzr> queryAllFzr(String flbh);
    //查询各一级部门名称
    Fzr QuerySzbm(String id);
    //查询所有职能部门负责人
    //List<Fzr> selectAllZnbmFzr();
    //修改所有院系负责人
    void modfiyYxFzr(Fzr fzr);
    //根据姓名查询所属部门
    Fzr queryBmByName(String name);
    //根据姓名查询所属部门
    Fzr queryBmBySzbm(String name);
    //查询所有院系信息
    List<String> queryAllYx();
}
