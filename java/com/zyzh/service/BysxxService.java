package com.zyzh.service;

import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BysxxService {
    //添加毕业生信息
    void addBysxx(Bysxx bysxx);
    //根据分类查询毕业生信息
    List<BysSwxx> queryBysxx(Bysxx bysxx,Integer start,Integer limit);
    //查询所有毕业生信息
    List<Bysxx> queryAllBysxx();
    // 查询所有毕业生信息根据院系
    List<Bysxx> queryAllBysxxByYx(Bysxx bysxx,Integer start,Integer limit);
    //查询所有院系名称信息
    List<String> queryAllYxMc();
    //根据分类查询毕业生信息
    Integer queryBysxxCount(Bysxx bysxx);
    //根据分类查询毕业生信息
    Integer queryAllBysxxByYxCount(Bysxx bysxx);
    //根据学号查询毕业生信息
    Bysxx queryBysxxByXh(String xh);
    //修改毕业生信息
    void modfiyBysxx(Bysxx bysxx);
    //修改毕业生信息
    void modfiyBysxxByzCx(String xh,String byzlqzt);
    //修改毕业生信息
    void modfiyBysxxByz(String xh,String byzlqzt,Date date);
    //批量导入毕业生信息
    void addAllBysxx(List<Bysxx> list);
    //毕业证信息统计
    Map<Object,Object> statisticByzxx(String xn, String yx);
    //查询毕业证每日领取人数
    List<Object> queryByzlqCountByDay(String yx);
    //在数据中心抽取毕业生信息
    List<Bysxx> queryAllBysxxFromSjzx();

}
