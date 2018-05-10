package com.zyzh.service;

import com.zyzh.dao.BysxxDao;
import com.zyzh.dao.DataBaseDao;
import com.zyzh.dao.LxnfDao;
import com.zyzh.dao.SwxxDao;
import com.zyzh.entity.*;
import com.zyzh.util.DataBaseJDBCUtil;
import com.zyzh.util.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BysxxServiceImpl implements BysxxService {


    @Autowired
    private BysxxDao bysxxDao;
    @Autowired
    private SwxxDao swxxDao;
    @Autowired
    private LxnfDao lxnfDao;
    @Autowired
    private DataBaseDao dataBaseDao;

    @Override
    //添加单个毕业生信息
    public void addBysxx(Bysxx bysxx) {
        bysxx.setByzlqzt("未办理");
        bysxxDao.insertBys(bysxx);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //根据分类查询毕业生信息分页查询
    public List<BysSwxx> queryBysxx(Bysxx bysxx, Integer start, Integer limit) {
        Integer startPage = start + 1;
        Integer endPage = start + limit;
        System.out.println(startPage + "  结束条数  " + endPage);
        return bysxxDao.selectAllBysxx(bysxx, startPage, endPage);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有毕业生信息
    public List<Bysxx> queryAllBysxx() {
        DataSourceContextHolder.setDataSourceType("mysqldataOne");
        return bysxxDao.selectAllBys();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Bysxx> queryAllBysxxByYx(Bysxx bysxx, Integer start, Integer limit) {
        Integer startPage = start + 1;
        Integer endPage = start + limit;
        return bysxxDao.selectAllBysxxByYx(bysxx, startPage, endPage);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> queryAllYxMc() {
        return bysxxDao.selectAllYxMc();
    }

    @Override
    //查询毕业生的总数量
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryBysxxCount(Bysxx bysxx) {
        return bysxxDao.selectBysxxCount(bysxx);
    }

    //查询毕业生的总数量
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryAllBysxxByYxCount(Bysxx bysxx) {
        return bysxxDao.selectAllBysxxByYxCount(bysxx);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //根据学号查询毕业生信息
    public Bysxx queryBysxxByXh(String xh) {
        return bysxxDao.selectBysxxByXh(xh);
    }

    @Override
    //修改毕业生信息
    public void modfiyBysxx(Bysxx bysxx) {
        bysxxDao.updateBysxx(bysxx);
    }

    @Override
    //撤销修改毕业生信息
    public void modfiyBysxxByzCx(String xh, String byzlqzt) {
        bysxxDao.updateBysxxByzCx(xh, byzlqzt);
    }

    //修改毕业生信息
    @Override
    public void modfiyBysxxByz(String xh, String byzlqzt, Date date) {
        bysxxDao.updateBysxxByz(xh, byzlqzt, date);
    }

    //添加毕业生信息
    @Override
    public void addAllBysxx(List<Bysxx> list) {
        DataSourceContextHolder.setDataSourceType("mysqldataOne");
        bysxxDao.insertAllBysxx(list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<Object, Object> statisticByzxx(String xn, String yx) {
        //根据院系查询所有毕业生总数
        int bysNum = bysxxDao.selectAllCount(yx, xn);
        //查询所有毕业证领取人数
        int byzlqNum = bysxxDao.selectAllByzlqCount(yx, xn);
        //查询未处理事项人数select count(*) from (select count(*) from X_SWXX where SWBLZT='未处理' GROUP BY BYSBH)
        int wclsxNum = swxxDao.selectAllWclsxCount(yx, xn);
        //计算出所有办理完事务可以领取毕业证还没有领取的毕业生人数
        int kylqbyzNum = bysNum - byzlqNum - wclsxNum;
        HashMap<Object, Object> map = new HashMap<>();
        map.put("yx", yx);
        map.put("allNum", bysNum);
        map.put("okNum", byzlqNum);
        map.put("noNum", kylqbyzNum);
        map.put("errorNum", wclsxNum);
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //查询每天办理毕业证的数量
    public List<Object> queryByzlqCountByDay(String yx) {
        ArrayList<Object> list = new ArrayList<>();
        //String xn = lxnfDao.selectNf();
        //查询bysxx表中日期不为空的日期list集合
        Set<Date> dates = bysxxDao.selectAllDate();
        for (Date date : dates) {
            int i1 = bysxxDao.selecByzlqCountByDay(yx, date);
            HashMap<Object, Object> map = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate = simpleDateFormat.format(date);
            map.put("day", stringDate);
            map.put("total", i1);
            list.add(map);
        }
        return list;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Bysxx> queryAllBysxxFromSjzx() {
        List<Bysxx> bysxxList = new ArrayList<>();
        DataBase dataBase = dataBaseDao.selectDataBaseById("1");
        ResultSet rs = DataBaseJDBCUtil.getResultSet(dataBase,null);
        try {
            while(rs.next()){//rs的下一行是否有数据 ：true 有 ，游标下移获得下一行数据  false ：结果集的末尾
                Bysxx bysxx = new Bysxx();
                bysxx.setXh(rs.getString(1));
                bysxx.setXm(rs.getString(2));
                bysxx.setXn(rs.getString(5));
                bysxx.setYx(rs.getString(3));
                bysxx.setBj(rs.getString(6));
                bysxx.setZy(rs.getString(4));
                bysxx.setByzlqzt(rs.getString(7));
                bysxx.setByzlqsj(rs.getDate(8));
                bysxxList.add(bysxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //DataBaseJDBCUtil.close(null,null,rs);
        return bysxxList;
    }


}
