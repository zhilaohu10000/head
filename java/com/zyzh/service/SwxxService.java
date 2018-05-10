package com.zyzh.service;

import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Swxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SwxxService {
    //导入事务列表
    void addAllSwxx(List<Swxx> list);

    //添加事务信息
    void addSwxx(Swxx swxx);

    //查询事务信息
    List<BysSwxx> querySwxx(BysSwxx swxx, Integer start, Integer limit);

    //根据事务部门查询事务信息
    List<Swxx> queryAllSwxxBySwbm(String swbm);

    //查询事务信息
    Integer querySwxxCount(BysSwxx swxx);

    //修改事务信息
    void modifySwxx(Swxx swxx);

    //修改事务信息
    void modifySwxxCX(Swxx swxx);

    //查询总事务条数
    int queryAllCount();

    //查询未办理事务条数
    int queryWblswCount();

    //查询已办理事务条数
    int queryYblswCount();

    //查询离校事务日办理数据
    List<Integer> queryCountByDay();

    //事务办理信息统计
    Map<Object, Object> statisticSwblTj(String xn, String yx, String swbm);

    //根据学号查询毕业生事务办理信息
    List<Swxx> querySwxxByXh(String xh);

    //根据学号查询毕业生事务
    Swxx querySwxxByBysbh(String xh);

    //查询事务信息每日办理人数
    List<Object> querySwxxCountByDay(String swbm);

    //查询事务信息
    Swxx querySwxxBySwbm(Swxx swxx);

    //查询所有职能部门名称
    List<String> queryAllYxMc();
    //根据事务部门在数据中心查询事务信息
    List<Swxx> queryAllSwxxBySwbmFromSjzx(String swbm);
}
