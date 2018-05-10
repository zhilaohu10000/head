package com.zyzh.service;

import com.zyzh.dao.DataBaseDao;
import com.zyzh.dao.FzrDao;
import com.zyzh.dao.LxnfDao;
import com.zyzh.dao.SwxxDao;
import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.DataBase;
import com.zyzh.entity.Swxx;
import com.zyzh.util.DataBaseJDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("swxxService")
@Transactional(propagation = Propagation.REQUIRED)
public class SwxxServiceImpl implements SwxxService {
    @Autowired
    private SwxxDao swxxDao;
    @Autowired
    private FzrDao fzrDao;
    @Autowired
    private LxnfDao lxnfDao;
    @Autowired
    private DataBaseDao dataBaseDao;

    @Override
    public void addAllSwxx(List<Swxx> list) {
        swxxDao.insertAllSwxx(list);
    }

    @Override
    public void addSwxx(Swxx swxx) {
        //根据职能部门查看职能部门负责人编号
        //设置负责人编号
        swxx.setFzrbh(fzrDao.selectZnbmFzrMc(swxx.getSwbm()));
        swxx.setSwblzt("未处理");
        swxxDao.insertSwxx(swxx);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //分类查询事务信息，分页
    public List<BysSwxx> querySwxx(BysSwxx swxx, Integer start, Integer limit) {
        Integer startPage = start + 1;
        Integer endPage = start + limit;
        return swxxDao.selectSwxx(swxx, startPage, endPage);
    }

    //根据事务部门查询所有事务信息
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Swxx> queryAllSwxxBySwbm(String swbm) {
        return swxxDao.selectAllSwxxBySwbm(swbm);
    }

    @Override
    //根据分类查询事务信息数量
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer querySwxxCount(BysSwxx swxx) {
        return swxxDao.selectSwxxCount(swxx);
    }

    @Override
    //修改事务信息
    public void modifySwxx(Swxx swxx) {
        if ("未处理".equals(swxx.getSwblzt())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date();
            Date date = null;
            try {
                date = simpleDateFormat.parse(simpleDateFormat.format(date1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            swxx.setSwblzt("已处理");
            swxx.setSwblsj(date);
            swxxDao.updateSwxx(swxx);
        }
    }

    @Override
    //撤销修改事务信息
    public void modifySwxxCX(Swxx swxx) {
        swxx.setSwblzt("未处理");
        swxxDao.updateSwxxCX(swxx);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int queryAllCount() {
        return 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int queryWblswCount() {
        return 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int queryYblswCount() {
        return 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Integer> queryCountByDay() {
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //统计事务信息办理人数
    public Map<Object, Object> statisticSwblTj(String xn, String yx, String swbm) {
        if (xn == null) {
            xn = lxnfDao.selectNf();
        }
        //根据院系查询所有事务信息总数
        int bysNum = swxxDao.selectAllCount(yx, xn, swbm);
        //查询所有事务信息已办理人数
        int byzlqNum = swxxDao.selectYblswCount(yx, xn, swbm);
        //查询未办理事项人数select count(*) from (select count(*) from X_SWXX where SWBLZT='未处理' GROUP BY BYSBH)
        //int wclsxNum = swxxDao.selectWblswCount(yx,xn,swbm);
        int wclsxNum = bysNum - byzlqNum;
        HashMap<Object, Object> map = new HashMap<>();
        map.put("yx", yx);
        map.put("xn", xn);
        map.put("swbm", swbm);
        map.put("allNum", bysNum);
        map.put("okNum", byzlqNum);
        // map.put("noNum",kylqbyzNum);
        map.put("errorNum", wclsxNum);
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //根据学号查询事务信息
    public List<Swxx> querySwxxByXh(String xh) {
        return swxxDao.selectSwxxByXh(xh);
    }

    //根据毕业生编号查询事务信息
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Swxx querySwxxByBysbh(String xh) {
        return swxxDao.selectSwxxByBysbh(xh);
    }

    //查询事务信息每天办理人数
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Object> querySwxxCountByDay(String swbm) {
        ArrayList<Object> list = new ArrayList<>();

        //String xn = lxnfDao.selectNf();
        Set<Date> dates = swxxDao.selectAllDate();
        for (Date date : dates) {
            int i1 = swxxDao.selectSwxxCountByDay(swbm, date);
            HashMap<Object, Object> map = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate = simpleDateFormat.format(date);
            map.put("day", stringDate);
            map.put("data1", i1);
            list.add(map);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //根据分类查询事务部门事务信息
    public Swxx querySwxxBySwbm(Swxx swxx) {
        return swxxDao.selectSwxxBySwbm(swxx);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> queryAllYxMc() {
        return swxxDao.selectAllZnbm();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Swxx> queryAllSwxxBySwbmFromSjzx(String swbm) {
        List<Swxx> swxxList = new ArrayList<>();
        DataBase dataBase = dataBaseDao.selectDataBaseById("3");
        ResultSet rs = DataBaseJDBCUtil.getResultSet(dataBase,swbm);
        try {
            while(rs.next()){//rs的下一行是否有数据 ：true 有 ，游标下移获得下一行数据  false ：结果集的末尾
                Swxx swxx = new Swxx();
                swxx.setSwbm(rs.getString(1));
                swxx.setSw(rs.getString(2));
                swxx.setSwblzt(rs.getString(3));
                swxx.setSwblsj(rs.getDate(4));
                swxx.setBysbh(rs.getString(5));
                swxxList.add(swxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return swxxList;
    }
}
