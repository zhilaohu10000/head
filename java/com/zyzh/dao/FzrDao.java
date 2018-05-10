package com.zyzh.dao;

import com.zyzh.entity.Fzr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FzrDao {
    //添加负责人
    void insertFzr(@Param("fzr")Fzr fzr);
    //查询所有院系负责人
    List<Fzr> selectAllFzr(@Param("flbh") String flbh);
    //查询各一级部门名称
    Fzr selectSzbm(@Param("id")String id);
    //查询所有职能部门负责人
    //List<Fzr> selectAllZnbmFzr();
    //修改所有院系负责人
    void updateYxFzr(@Param("fzr")Fzr fzr);
    //修改所有职能部门负责人
   // void updateZnbmFzr(Fzr fzr);
    //根据职能部门名称查询负责人id
    String selectZnbmFzrMc(@Param("szbm") String szbm);
    //查询所有负责人总数量
    int selectAllCount();
    //根据姓名查询所属部门
    Fzr selectBmByName(@Param("name")String name);
    //根据姓名查询所属部门
    Fzr selectBmBySzbm(@Param("name")String name);
    //查询所有院系信息
    List<String> selectAllYx();
}
