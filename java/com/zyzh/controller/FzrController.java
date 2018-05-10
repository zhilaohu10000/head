package com.zyzh.controller;

import com.zyzh.entity.Fzr;
import com.zyzh.service.BysxxService;
import com.zyzh.service.FzrService;
import com.zyzh.service.SwxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fzr")
public class FzrController {
    @Autowired
    private FzrService fzrService;
    @Autowired
    private BysxxService bysxxService;
    @Autowired
    private SwxxService swxxService;
    @RequestMapping("/queryFzr")
    @ResponseBody
    public List<Fzr> queryFzr(String flbh) {
        List<String> strings = bysxxService.queryAllYxMc();
        for (String string : strings) {
            System.out.println(string);
            Fzr fzr1 = fzrService.queryBmBySzbm(string);
            if(null==fzr1){
                Fzr fzr = new Fzr();
                fzr.setFlbh("1");
                fzr.setSzbm(string);
                fzrService.addFzr(fzr);
            }
        }
        List<String> string = swxxService.queryAllYxMc();
        for (String str : string) {
            Fzr fzr1 = fzrService.queryBmBySzbm(str);
            if(null==fzr1){
                Fzr fzr = new Fzr();
                fzr.setFlbh("2");
                fzr.setSzbm(str);
                fzrService.addFzr(fzr);
            }
        }
        return fzrService.queryAllFzr(flbh);
    }

    @RequestMapping("/addYxFzr")
    @ResponseBody
    public String addYxFzr(Fzr fzr) {
        //设置flbh为1
        //设置职能部门为院系本科负责人，设置权限为院系研究生负责人
        //根据职能部门查询，存在则修改
        Fzr fzr1 = fzrService.queryBmBySzbm(fzr.getSzbm());
        if (null != fzr1) {
            fzr.setFlbh("1");
            fzr.setZnbmfzr(fzr.getBksfzr());
            fzr.setQx(fzr.getYjsfzr());
            fzrService.modfiyYxFzr(fzr);
        } else {
            //不存在，则新增
            fzr.setFlbh("1");
            fzr.setZnbmfzr(fzr.getBksfzr());
            fzr.setQx(fzr.getYjsfzr());
            //添加用户
            fzrService.addFzr(fzr);
        }
        return "success";
    }

    @RequestMapping("/addZnbmFzr")
    @ResponseBody
    public String addZnbmFzr(Fzr fzr) {
        //设置flbh为2
        //设置职能部门负责人为职能部门负责人，权限为空
        Fzr fzr1 = fzrService.queryBmBySzbm(fzr.getSzbm());
        if (null != fzr1) {
            fzr.setFlbh("2");
            fzrService.modfiyYxFzr(fzr);
        } else {
            fzr.setFlbh("2");
            fzrService.addFzr(fzr);
        }
        return "success";
    }
}
