package com.zyzh.service;

import com.zyzh.dao.BysxxDao;
import com.zyzh.dao.FzrDao;
import com.zyzh.dao.LxnfDao;
import com.zyzh.dao.SwxxDao;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Lxnf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LxnfServiceImpl implements LxnfService {
    @Autowired private LxnfDao lxnfDao;
    @Autowired private SwxxDao swxxDao;
    @Autowired private BysxxDao bysxxDao;
    @Autowired private FzrDao fzrDao;
    @Override
    public void addYear(Lxnf year) {
        lxnfDao.insertYear(year);
    }

    @Override
    public String modfiyYear(String year, String xtzt) {
        //进行逻辑判断，设置开始时间，结束时间
        //根据year查询当前状态，
        //1.如果当前状态为未开启，请求状态为开启则判断毕业生信息是否导入，离校事务是否导入，权限是否设置，满足条件，设置开启时间，设置状态为开启，否则无效
        //2.如果当前状态为已开启，请求状态为结束，设置结束时间，设置状态为结束。请求状态为中止，则设置状态为中止
        //3。当前状态为中止，请求状态为结束，设置结束时间，设置状态为结束。请求状态为开启，则判断毕业生信息是否导入，离校事务是否导入，权限是否设置

        String status = lxnfDao.selectStatus(year);
        if(status.equals("未开启")){
            if(xtzt.equals("已开启")){
                String yx=null;
                Integer fzr = fzrDao.selectAllCount();
                Integer bys = bysxxDao.selectAllCount(yx,year);
                Integer sw = swxxDao.selectAllCount(yx,year,null);
                if(fzr!=null&&bys!=null&&sw!=null){
                    Lxnf lxnf=new Lxnf();
                    lxnf.setNf(year);
                    lxnf.setXtzt(xtzt);
                    lxnf.setKqsj(new Date());
                    lxnfDao.updateYear(lxnf,year);
                    return "离校系统开始";
                }else{
                    return "数据未完全导入";
                }
            }else{
                return "状态错误";
            }
        }
        if(status.equals("已开启")){
            if(xtzt.equals("已中止")){
                    Lxnf lxnf=new Lxnf();
                    lxnf.setNf(year);
                    lxnf.setXtzt(xtzt);
                    lxnfDao.updateYear(lxnf,year);
                    return "离校系统中止";
            }
            if(xtzt.equals("已结束")){
                    Lxnf lxnf=new Lxnf();
                    lxnf.setNf(year);
                    lxnf.setXtzt(xtzt);
                    lxnf.setJssj(new Date());
                    lxnfDao.updateYear(lxnf,year);
                    return "离校系统结束";
            }
            else{
                return "状态错误";
            }
        }
        if(status.equals("已中止")){
            if(xtzt.equals("已开启")){
                    Lxnf lxnf=new Lxnf();
                    lxnf.setNf(year);
                    lxnf.setXtzt(xtzt);
                    lxnfDao.updateYear(lxnf,year);
                    return "离校系统开启";
            }
            if(xtzt.equals("已结束")){
                    Lxnf lxnf=new Lxnf();
                    lxnf.setNf(year);
                    lxnf.setXtzt(xtzt);
                    lxnf.setJssj(new Date());
                    lxnfDao.updateYear(lxnf,year);
                    return "离校系统结束";
            }
            else{
                return "状态错误";
            }
        }
       return "状态错误";
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Lxnf> queryAllLxnf() {
        return lxnfDao.selectAllLxnf();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> queryAllNf() {
        return lxnfDao.selectAllNf();
    }
}
