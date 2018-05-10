package com.zyzh.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zyzh.dao.FzrDao;
import com.zyzh.entity.BysSwxx;
import com.zyzh.entity.Fzr;
import com.zyzh.entity.Swxx;
import com.zyzh.service.FzrService;
import com.zyzh.service.MysqlService;
import com.zyzh.service.SwxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/swxx")
public class SwxxController {
    @Autowired
    private SwxxService swxxService;
    @Autowired
    private FzrService fzrService;
    @Autowired
    private MysqlService mysqlService;

    //分类查询事务信息
    @RequestMapping("/querySwxx")
    @ResponseBody
    public Map<Object, Object> querySwxx(BysSwxx swxx, HttpSession session, Integer start, Integer limit) {
        System.out.println(swxx + "123123123123----------" + start + "------------" + limit);
        if (swxx.getSwblsj() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(swxx.getSwblsj());
            System.out.println(date);
            Date date2 = null;
            try {
                date2 = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            swxx.setSwblsj(date2);
        }
        if (swxx.getXh() == null || swxx.getXh() == "") {
            //设置为教务处管理员权限
            Fzr fzr = (Fzr) session.getAttribute("login");
            //根据name查询所属部门
            Fzr fzr1 = fzrService.QuerySzbm("3");
            if(null!=fzr){
                if (!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())) {
                    swxx.setSwbm(fzr.getSzbm());
                }
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        List<BysSwxx> bysSwxxes = swxxService.querySwxx(swxx, start, limit);
        //查询数据总条数
        Integer total = swxxService.querySwxxCount(swxx);
        System.out.println(bysSwxxes + "分页查询------------------" + start + "------------" + total);
        map.put("root", bysSwxxes);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/addSwxx")
    @ResponseBody
    public String addSwxx(Swxx swxx) {
        //根据学号查询插入事务信息表
        System.out.println(swxx);
        swxxService.addSwxx(swxx);
        return "success";
    }

    @RequestMapping("/modfiySwxx")
    @ResponseBody
    public String modfiySwxx(Swxx swxx, HttpSession session) {
        //根据学号修改事务信息表
        Fzr fzr = (Fzr) session.getAttribute("login");
        if (!"1".equals(fzr.getFlbh())) {
            swxxService.modifySwxx(swxx);
            return "办理成功";
        }
        return "请去职能部门办理";
    }

    //离校事务的办理
    @RequestMapping("/modifySwxxCX")
    @ResponseBody
    public String modifySwxxCX(Swxx swxx, HttpSession session) {
        //根据当前登录用户，获得当前事务部门名称
        Fzr fzr = (Fzr) session.getAttribute("login");
        //根据学生id，设置当前事务办理状态，如果状态为未处理，则设置为已处理，设置当前办理时间。
        String szbm = fzr.getSzbm();
        swxx.setSwbm(szbm);
        //根据职能部门和当前学生id查询事务办理情况
        Swxx swxx1 = swxxService.querySwxxBySwbm(swxx);
        if ("未处理".equals(swxx1.getSwblzt())) {
            swxx.setSwblzt("未处理");
            swxxService.modifySwxx(swxx);
            return "办理成功";
        } else {
            //如果当前状态为已处理，则设置为未处理，删除处理使劲按
            swxx.setSwblzt("未处理");
            swxx.setSwblsj(null);
            swxxService.modifySwxxCX(swxx);
            return "撤销成功";
        }
    }

    //统计事务办理情况
    @RequestMapping("/swblTj")
    @ResponseBody
    public List<Object> swblTj(String xn, String yx, String swbm, HttpSession session) {

        ArrayList<Object> list = new ArrayList<>();
        Fzr fzr = (Fzr) session.getAttribute("login");
        if(null!=fzr) {
            if (fzr.getFlbh().equals("1")) {
                Map<Object, Object> map = swxxService.statisticSwblTj(xn, fzr.getSzbm(), swbm);
                list.add(map);
            }
            if (fzr.getFlbh().equals("2")) {
                //根据name查询所属部门
                Map<Object, Object> map = swxxService.statisticSwblTj(xn, yx, fzr.getSzbm());
                list.add(map);
            } else {
                if (yx != null) {
                    //根据搜索信息查询事务办理统计信息
                    Map<Object, Object> map = swxxService.statisticSwblTj(xn, yx, swbm);
                    list.add(map);
                } else {
                    //调用查询毕业证统计信息
                    //查询所有职能部门名称
                    List<String> strings = fzrService.queryAllYx();
                    for (String str : strings) {
                        Map<Object, Object> map = swxxService.statisticSwblTj(xn, str, swbm);
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    //查询每天事务信息统计信息
    @RequestMapping("/swxxMtTj")
    @ResponseBody
    public List<Object> queryswxxMtTj(String szbm, HttpSession session) {
        Fzr fzr = (Fzr) session.getAttribute("login");
        //判断是否未离校事务管理员，如果不是则按照院系查询毕业证统计情况
        Fzr fzr1 = fzrService.QuerySzbm("3");
        if(null!=fzr){
            if (!fzr1.getZnbmfzr().equals(fzr.getZnbmfzr())) {
                szbm = fzr.getSzbm();
            }
        }
        List<Object> list = swxxService.querySwxxCountByDay(szbm);
        return list;

    }

    //自动导入事务信息
    @RequestMapping("/autoUploadSwxx")
    @ResponseBody
    public String autoUploadSwxx(HttpServletRequest request) {
        String[] list = request.getParameterValues("list");
        String msg = null;
        int a[] = new int[3];
        int i = 0;
        //根据选择的事务部门进行事务数据的导入
        if (list != null) {
            for (String s : list) {
                //从数据中心导入相关职能部门的数据存入sjzxList
                List<Swxx> sjzxList = swxxService.queryAllSwxxBySwbmFromSjzx(s);
                //List<Swxx> sjzxList = new ArrayList<>();
                //查询相关事务部门的数据存入bdList
                List<Swxx> swxxes = swxxService.queryAllSwxxBySwbm(s);
                if (sjzxList != null && !sjzxList.isEmpty()) {
                    //数据对比，将重复的数据筛选出来，导入到事务信息表中
                    sjzxList.removeAll(swxxes);
                    if (sjzxList != null && !sjzxList.isEmpty()) {
                        System.out.println(sjzxList);
                        //添加到事务表中
                        swxxService.addAllSwxx(sjzxList);
                        a[i++] = sjzxList.size();
                    } else {
                        msg = "未导入有效数据";
                    }
                } else {
                    msg = "未导入有效数据";
                }
            }
            int num = 0;
            System.out.println(i + "============================");
            for (int j = 0; j < i; j++) {
                num += a[j];
            }
            msg = "成功添加了：" + num + "条数据。";
        } else {
            msg = "导入出错";
        }
        return msg;
    }

    //事务信息导入之后开启事务自动办理的功能
    public void autoManagerSwxx() {
        //查询本地离校事务信息，如何存在，则开启
        //设置定时器，时间是五分钟
        //在数据中心中查询所有的事务信息存放在listOne中，将listOne存放在session中
        int i = mysqlService.queryAllCount();
        //五分钟后，再次在数据中心查询所有的事务信息存放在listTwo中，将listTwo存放在session中
        int j = mysqlService.queryAllCount();
        //listOne与listTwo进行取余，将余下的事务数据在本数据库中更改状态未已办理，增加办理时间，和办理详情
    }

    //查询所有职能部门名称
    @RequestMapping("/queryAllZnbmMc")
    @ResponseBody
    public List<Object>queryAllZnbmMc(){
        ArrayList<Object> list = new ArrayList<>();
        List<String> strings = swxxService.queryAllYxMc();
        for (String s : strings) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("code",s);
            map.put("name",s);
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/querySwxxById")
    @ResponseBody
    public String querySwxxById(Swxx swxx,HttpSession session){
        Fzr fzr = (Fzr) session.getAttribute("login");
        //根据学生id，设置当前事务办理状态
        String szbm = fzr.getSzbm();
        swxx.setSwbm(szbm);
        //根据职能部门和当前学生id查询事务办理情况
        Swxx swxx1 = swxxService.querySwxxBySwbm(swxx);
        return swxx1.getSwblzt();
    }
}
