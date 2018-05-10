package com.zyzh.controller;

import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Fzr;
import com.zyzh.entity.Swxx;
import com.zyzh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bysxx")
public class BysxxController {
    @Autowired
    private BysxxService bysxxService;
    @Autowired
    private FzrService fzrService;
    @Autowired
    private SwxxService swxxService;
    @Autowired
    private MysqlService mysqlService;

    //根据院系查询毕业生信息
    @RequestMapping("/queryBysxxByYx")
    @ResponseBody
    public Map<Object,Object> queryAllBysxxByYx(Bysxx bysxx, HttpSession session,Integer start,Integer limit) {
        System.out.println(start+"===================="+limit);
        //设置为计算机系管理员权限
        Fzr fzr = (Fzr) session.getAttribute("login");
        if (bysxx.getByzlqsj() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(bysxx.getByzlqsj());
            System.out.println(date);
            Date date2 = null;
            try {
                date2 = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bysxx.setByzlqsj(date2);
        }
        //根据name查询所属部门
        Fzr fzr1 = fzrService.QuerySzbm("3");
        if(null!=fzr){
            if (!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())) {
                bysxx.setYx(fzr.getSzbm());
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        List<Bysxx> bysxxes = bysxxService.queryAllBysxxByYx(bysxx,start,limit);
        //查询数据总条数
        Integer total = bysxxService.queryAllBysxxByYxCount(bysxx);
        System.out.println(bysxxes+"分页查询------------------"+start+"------------"+total);
        map.put("root",bysxxes);
        map.put("total",total);
        return map;
    }
   //查询毕业生信息
    @RequestMapping("/queryBysxx")
    @ResponseBody
    public Map<Object,Object> queryAllBysxx(Bysxx bysxx, HttpSession session,Integer start,Integer limit) {
        System.out.println(start+"===================="+limit);
        //设置为计算机系管理员权限
        Fzr fzr = (Fzr) session.getAttribute("login");
        if (bysxx.getByzlqsj() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(bysxx.getByzlqsj());
            System.out.println(date);
            Date date2 = null;
            try {
                date2 = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bysxx.setByzlqsj(date2);
        }
        //根据name查询所属部门
        Fzr fzr1 = fzrService.QuerySzbm("3");
        if(null!=fzr){
            if (!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())) {
                bysxx.setYx(fzr.getSzbm());
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        List<BysSwxx> bysxxes = bysxxService.queryBysxx(bysxx,start,limit);
        //查询数据总条数
        Integer total = bysxxService.queryBysxxCount(bysxx);
        System.out.println(bysxxes+"分页查询------------------"+start+"------------"+total);
        map.put("root",bysxxes);
        map.put("total",total);
        return map;
    }

    //添加毕业生信息
    @RequestMapping("/addBysxx")
    @ResponseBody
    public String addBysxx(Bysxx bysxx) {
        System.out.println(bysxx);
        bysxxService.addBysxx(bysxx);
        return "success";
    }

    //修改毕业生信息
    @RequestMapping("/madfiyBysxx")
    @ResponseBody
    public String madfiyBysxx(Bysxx bysxx) {
        bysxxService.modfiyBysxx(bysxx);
        return "success";
    }
    //修改毕业证领取信息
    @RequestMapping("/madfiyBysxxByByz")
    @ResponseBody
    public String madfiyBysxxByByz(String xh) {
        //根据学生学号查询学生未办理的事务，如果存在，则返回未办理信息。如果没有，则返回办理成功的信息
        if(xh!=null){
            List<Swxx> swxxes = swxxService.querySwxxByXh(xh);
            if(swxxes.size()!=0){
                System.out.println(swxxes);
                return "还有未办理事项";
            }else{
                //如果为已办理，则设置为未办理，设置当前时间为null
                Bysxx bysxx = bysxxService.queryBysxxByXh(xh);
                if("已办理".equals(bysxx.getByzlqzt())){
                    bysxxService.modfiyBysxxByzCx(xh,"未办理");
                    return "撤销成功";
                }else {
                    //查询当前用户的毕业证领取状态，如果为未办理则更改为办理
                    bysxxService.modfiyBysxxByz(xh, "已办理", new Date());
                    return "领取成功";
                }
            }
        }
        return "还有未办理事项";
    }

    //统计毕业证领取情况
    @RequestMapping("/byzTj")
    @ResponseBody
    public List<Object> bysxxTj(String xn, String yx, HttpSession session) {
        ArrayList<Object> list = new ArrayList<>();
        Fzr fzr = (Fzr) session.getAttribute("login");
        if(null!=fzr) {


            Fzr fzr1 = fzrService.QuerySzbm("3");
            if (!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())) {
                //根据name查询所属部门
                Map<Object, Object> map = bysxxService.statisticByzxx(xn, fzr.getSzbm());
                list.add(map);
                System.out.println(xn + fzr.getSzbm());
            } else {
                if (yx != null) {
                    //根据院系查询毕业证统计信息
                    Map<Object, Object> map = bysxxService.statisticByzxx(xn, yx);
                    list.add(map);
                } else {
                    //调用查询毕业证统计信息
                    //查询所有院系名称
                    List<String> strings = fzrService.queryAllYx();
                    for (String str : strings) {
                        Map<Object, Object> map = bysxxService.statisticByzxx(xn, str);
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }
    //查询每天毕业证统计信息
    @RequestMapping("/byzMtTj")
    @ResponseBody
    public List<Object>queryByzMtTj(String yx,HttpSession session){
        Fzr fzr = (Fzr) session.getAttribute("login");
        //判断是否未离校事务管理员，如果不是则按照院系查询毕业证统计情况
        Fzr fzr1 = fzrService.QuerySzbm("3");
        if(null!=fzr){
            if(!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())){
                yx=fzr.getSzbm();
            }
        }
        List<Object> list = bysxxService.queryByzlqCountByDay(yx);
        return list;

    }

    //毕业生信息自动导入功能
    @RequestMapping("/autoUpload")
    @ResponseBody
    public String autoUpload() {
        //ArrayList<Bysxx> sjzxList = new ArrayList<>();
        System.out.println("从数据中心抽取相关毕业生数据");
        System.out.println("从本地查询相关毕业生信息");
        String msg;
        //从数据中心抽取相关毕业生数据sjzxList
        List<Bysxx> sjzxList = bysxxService.queryAllBysxxFromSjzx();
        System.out.println(sjzxList+"从数据中心抽取毕业生信息");
        Bysxx bysxx = new Bysxx();
        bysxx.setXh("123123123");
        bysxx.setBj("dasdqwdw");
        bysxx.setByzlqzt("未办理");
        bysxx.setYx("asdsadsa");
        bysxx.setZy("asasdadsadsa");
        bysxx.setXm("权威");
        bysxx.setXn("2018");
        sjzxList.add(bysxx);
        int i = mysqlService.queryAllCount();
        System.out.println("==========="+i+"===========================00000===================0000===========");
        if (sjzxList!=null&&!sjzxList.isEmpty()) {
            //从本地查询相关毕业生信息bdList
            List<Bysxx> bdList = bysxxService.queryAllBysxx();
            //对比list1与list2中的重复数据，将对比结果中不重复的数据插入到毕业生信息表
            sjzxList.removeAll(bdList);//此处指的是将与bdList重复的数据删除
            System.out.println(sjzxList);
            if(sjzxList!=null&&!sjzxList.isEmpty()){
                //然后将sjzxList新增到毕业生信息表
                bysxxService.addAllBysxx(sjzxList);
                msg = "成功添加了：" + sjzxList.size() + "条数据。";
            }else {
                msg = "未导入有效数据";
            }
        } else {
            msg = "未导入有效数据";
        }
        return msg;
    }

    //查询所有毕业生的院系部门
    @RequestMapping("/queryAllYxMc")
    @ResponseBody
    public List<Object>queryAllYxMc(){
        ArrayList<Object> list = new ArrayList<>();
        List<String> strings = bysxxService.queryAllYxMc();
        for (String s : strings) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("code",s);
            map.put("name",s);
            list.add(map);
        }
        return list;
    }

}
