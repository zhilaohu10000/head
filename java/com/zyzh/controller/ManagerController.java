package com.zyzh.controller;

import com.zyzh.entity.Fzr;
import com.zyzh.entity.Paramter;
import com.zyzh.service.FzrService;
import com.zyzh.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/login")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private FzrService fzrService;

    //查询用户信息
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        Fzr fzr = null;
        fzr = fzrService.queryBmByName(username);
        session.setAttribute("login", fzr);
        return "redirect:/init.html";
    }
    //查询用户信息
    @RequestMapping("/querySession")
    @ResponseBody
    public String querySession(HttpSession session) {
        Fzr fzr = null;
        fzr = (Fzr) session.getAttribute("login");
        if(fzr!=null){
            return fzr.getZnbmfzr();
        }
        return "未登录";

    }

    //登录功能
    @RequestMapping("/change")
    @ResponseBody
    public List<Paramter> change(String manager, HttpSession session) {
        Fzr fzr = null;
        String manager1 = null;
        //根据部门负责人查询所在部门
        Fzr fzrs = fzrService.QuerySzbm("3");
        if (manager == null) {
            fzr = (Fzr) session.getAttribute("login");
            if(fzr==null){
                manager1 = fzrs.getSzbm();
            }else{
                manager1 = fzr.getSzbm();
            }
        } else {
            fzr = (Fzr) session.getAttribute("login");
            if (fzr == null) {
                fzr = fzrService.queryBmByName(manager);
                //根据事务部门查找事务负责人信息
                session.setAttribute("login", fzr);
            } else {
                if (fzr.getId().equals("3")) {
                    fzr = fzrService.queryBmByName(manager);
                } else {

                }
            }
            manager1 = fzr.getSzbm();
            //根据部门负责人查询所在部门信息
        }
        List<Paramter> list = new ArrayList<Paramter>();
        //离校事务管理员schoolManager
        if (fzrs.getSzbm().equals(manager1)) {
            List<Paramter> list1 = new ArrayList<Paramter>();
            List<Paramter> list2 = new ArrayList<Paramter>();
            Paramter p1 = new Paramter("2", "1", "基础信息", "0", true, "", list1);
            Paramter p2 = new Paramter("5", "2", "参数设置", "1", true, "parameterlist", null);
            Paramter p3 = new Paramter("11", "2", "毕业生信息", "1", true, "graduatelist", null);
            Paramter p4 = new Paramter("12", "2", "未处理事务", "1", true, "schoolAffairslist", null);
            Paramter p41 = new Paramter("6", "2", "处理权限", "1", true, "managerView", null);
            //Paramter p42 = new Paramter("7", "2", "微信端", "1", true, "schoolAffairsManager", null);
            Paramter p5 = new Paramter("3", "1", "查询统计", "0", true, "", list2);
            Paramter p6 = new Paramter("13", "3", "离校事务办理查询", "1", true, "schoolAffairsQuerylist", null);
            Paramter p7 = new Paramter("14", "3", "毕业证领取查询", "1", true, "graduateQuerylist", null);
            Paramter p8 = new Paramter("15", "3", "毕业证领取状况", "1", true, "graduatestatuslist", null);
            Paramter p9 = new Paramter("16", "3", "离校事务办理状况", "1", true, "schoolAffairsstatuslist", null);
            Paramter p10 = new Paramter("17", "3", "毕业证领取状况统计", "1", true, "graduatechart", null);
            list.add(p1);
            list1.add(p2);
            list1.add(p3);
            list1.add(p4);
            list1.add(p41);
            //list1.add(p42);
            list.add(p5);
            list2.add(p6);
            list2.add(p7);
            list2.add(p8);
            list2.add(p9);
            list2.add(p10);
        }
        //院系管理员
        if (fzr != null) {


            if ("1".equals(fzr.getFlbh())) {
                System.out.println("dep==========================================");
                //根据姓名查询用户权限
                List<Paramter> list1 = new ArrayList<Paramter>();
                List<Paramter> list2 = new ArrayList<Paramter>();
                Paramter p1 = new Paramter("2", "1", "毕业证办理", "0", true, "", list1);
                Paramter p2 = new Paramter("5", "2", "毕业证办理", "1", true, "schoolAffairsDepartment", null);
                Paramter p5 = new Paramter("3", "1", "查询统计", "0", true, "", list2);
                Paramter p7 = new Paramter("14", "3", "毕业证办理查询", "1", true, "graduateQuerylistYx", null);
                Paramter p8 = new Paramter("15", "3", "毕业证领取状况", "1", true, "graduatestatuslist", null);
                Paramter p10 = new Paramter("17", "3", "毕业证领取状况统计", "1", true, "graduatechart", null);
                list.add(p1);
                list1.add(p2);
                list.add(p5);
                list2.add(p7);
                list2.add(p8);
                list2.add(p10);
            }
            //职能部门管理员
            if ("2".equals(fzr.getFlbh())) {
                List<Paramter> list1 = new ArrayList<Paramter>();
                List<Paramter> list2 = new ArrayList<Paramter>();
                Paramter p1 = new Paramter("2", "1", "事务办理", "0", true, "", list1);
                Paramter p2 = new Paramter("5", "2", "离校事务办理", "1", true, "schoolAffairsManagerQuery", null);
                Paramter p5 = new Paramter("3", "1", "查询统计", "0", true, "", list2);
                Paramter p6 = new Paramter("13", "3", "离校事务办理查询", "1", true, "schoolAffairsQuerylist", null);
                Paramter p9 = new Paramter("16", "3", "离校事务办理状况", "1", true, "schoolAffairsstatuslist", null);
                Paramter p10 = new Paramter("17", "3", "离校事务日办理状况", "1", true, "SchoolAffairsChart", null);
                list.add(p1);
                list1.add(p2);
                list.add(p5);
                list2.add(p6);
                list2.add(p9);
                list2.add(p10);
            }
        }
        return list;
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session) {
        session.removeAttribute("login");
         Fzr fzr = (Fzr) session.getAttribute("login");
        System.out.println(fzr+"====是不是为空");
        return "success";
    }
}
