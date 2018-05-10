package com.zyzh.dao;

import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Swxx;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SwxxDao {
    //导入事务列表
    void insertAllSwxx(@Param("list") List<Swxx> list);

    //添加事务信息
    void insertSwxx(@Param("swxx") Swxx swxx);

    //查询事务信息
    List<BysSwxx> selectSwxx(@Param("swxx") BysSwxx swxx, @Param("start") Integer start, @Param("end") Integer end);

    //根据事务部门查询事务信息
    List<Swxx> selectAllSwxxBySwbm(@Param("swbm") String swbm);

    //根据分类查询毕业生信息
    Integer selectSwxxCount(@Param("swxx") BysSwxx swxx);

    //查询事务信息
    Swxx selectSwxxBySwbm(@Param("swxx") Swxx swxx);

    //修改事务信息
    void updateSwxx(@Param("swxx") Swxx swxx);

    //修改事务信息
    void updateSwxxCX(@Param("swxx") Swxx swxx);

    //统计所有职能部门名称
    List<String> selectAllZnbm();

    //查询总事务条数
    int selectAllCount(@Param("yx") String yx, @Param("xn") String xn, @Param("swbm") String swbm);

    //查询未办理事务条数
    int selectWblswCount(@Param("yx") String yx, @Param("xn") String xn, @Param("swbm") String swbm);

    //查询已办理事务条数
    int selectYblswCount(@Param("yx") String yx, @Param("xn") String xn, @Param("swbm") String swbm);

    //查询离校事务日办理数据
    List<Integer> selectCountByDay();

    //查询有未处理事项的毕业生人数
    int selectAllWclsxCount(@Param("yx") String yx, @Param("xn") String xn);

    //根据学号查询毕业生事务办理信息
    List<Swxx> selectSwxxByXh(@Param("xh") String xh);

    //根据学号查询毕业生事务办理信息
    Swxx selectSwxxByBysbh(@Param("xh") String xh);

    //查询事务信息每日办理人数
    int selectSwxxCountByDay(@Param("swbm") String swbm, @Param("date") Date date);

    //查询所有职能部门的名称
    List<String> selectAllnbmMc();

    //查询毕业证办理日期
    Set<Date> selectAllDate();
}
