package com.zyzh.controller;

import com.zyzh.entity.Lxnf;
import com.zyzh.service.LxnfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lxnf")
public class LxnfController {
    @Autowired
    private LxnfService lxnfService;
    @RequestMapping("/query")
    //查询所有年份
    @ResponseBody
    public List<Lxnf> queryNf(){
        return lxnfService.queryAllLxnf();
    }
    //添加年份信息
    @RequestMapping("/addNf")
    @ResponseBody
    public String addNf(String year){
        Lxnf lxnf=new Lxnf();
        lxnf.setNf(year);
        lxnf.setXtzt("未开启");
        lxnfService.addYear(lxnf);
        return "success";
    }
    //修改年份信息
    @RequestMapping("/updateNf")
    @ResponseBody
    public String updateNf(String year,String xtzt){
        System.out.println(year+"====="+xtzt);
        //进行逻辑判断如果毕业生信息，事务信息，权限设置完成才可以开启离校系统
        String str = lxnfService.modfiyYear(year, xtzt);
        return str;
    }
    //查询年份信息
    @RequestMapping("/queryStringNf")
    @ResponseBody
    public List<String> queryStringNf(){
        return lxnfService.queryAllNf();
    }

    //查询年度信息
    @RequestMapping("/queryNd")
    @ResponseBody
    public  ArrayList<Object> queryNd(){
        ArrayList<Object> list = new ArrayList<>();
        List<String> nd = lxnfService.queryAllNf();
        for (String s : nd) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("code",s);
            map.put("name",s);
            list.add(map);
        }
        return list;
    }


}
