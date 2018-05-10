package com.zyzh.dao;

import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Swxx;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BysxxDao {
    //添加毕业生信息
    void insertBys(@Param("bysxx") Bysxx bysxx);

    //根据分类查询毕业生信息
    List<BysSwxx> selectAllBysxx(@Param("bysxx") Bysxx bysxx, @Param("start") Integer start, @Param("end") Integer end);

    // 查询所有毕业生信息
    List<Bysxx> selectAllBys();
    // 查询所有毕业生信息根据院系
    List<Bysxx> selectAllBysxxByYx(@Param("bysxx") Bysxx bysxx, @Param("start") Integer start, @Param("end") Integer end);

    //根据分类查询毕业生信息
    Integer selectBysxxCount(@Param("bysxx") Bysxx bysxx);
    //根据分类查询毕业生信息
    Integer selectAllBysxxByYxCount(@Param("bysxx") Bysxx bysxx);

    //根据学号查询毕业生信息
    Bysxx selectBysxxByXh(@Param("xh") String xh);

    //修改毕业生信息
    void updateBysxx(@Param("bysxx") Bysxx bysxx);

    //修改毕业生信息
    void updateBysxxByz(@Param("xh") String xh, @Param("byzlqzt") String byzlqzt, @Param("date") Date date);

    //修改毕业生信息
    void updateBysxxByzCx(@Param("xh") String xh, @Param("byzlqzt") String byzlqzt);

    //批量导入毕业生信息
    void insertAllBysxx(@Param("list") List<Bysxx> list);

    //查询所有毕业生数据总数
    int selectAllCount(@Param("yx") String yx, @Param("xn") String xn);

    //查询毕业证已领取人数
    int selectAllByzlqCount(@Param("yx") String yx, @Param("xn") String xn);

    //查询毕业证每日领取人数
    int selecByzlqCountByDay(@Param("yx") String yx, @Param("date") Date date);
    //查询所有院系名称
    List<String> selectAllYxMc();

    //查询毕业证办理日期
    Set<Date> selectAllDate();
}
