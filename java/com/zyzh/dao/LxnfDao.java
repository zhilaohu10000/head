package com.zyzh.dao;

import com.zyzh.entity.Lxnf;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LxnfDao {
    //添加年份
    void insertYear(@Param("year") Lxnf year);
    //根据年份，修改开启时间，关闭时间，当前状态
    void updateYear(@Param("lxnf")Lxnf lxnf,@Param("year")String year);
    //查询年份
    List<Lxnf> selectAllLxnf();
    //查询年份
    List<Lxnf> select123AllLxnf(@Param("date") Date date);
    //查询所有年份
    List<String>selectAllNf();
    //根据年份查询当前系统状态
    String selectStatus(@Param("nf") String nf);
    //根据当前系统状态查询年份
    String selectNf();
}
