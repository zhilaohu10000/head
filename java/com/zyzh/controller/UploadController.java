package com.zyzh.controller;

import com.zyzh.dao.BysxxDao;
import com.zyzh.entity.Bysxx;
import com.zyzh.entity.Swxx;
import com.zyzh.service.BysxxService;
import com.zyzh.service.SwxxService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private BysxxService bysxxService;
    @Autowired
    private SwxxService swxxService;

    @RequestMapping("/bysxxUpload")
    @SuppressWarnings("resource")
    @ResponseBody
    public ResponseEntity<String> bysxxUpload(MultipartFile upFile, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String json = "", errMsg = "1", cellValue = "";
        //获取当前服务器的webapp路径
        String webapp = session.getServletContext().getRealPath("/");
        File file = new File(webapp, "file");
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取到上传的文件名
        String fileName = upFile.getOriginalFilename();
        //获取上传的文件类型
        String contentType = upFile.getContentType();
        // System.out.println(contentType);
        //获取保存文件的位置
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newName = fileName + uuid + "." + FilenameUtils.getExtension(fileName);
        //进行保存上传的文件
        //设置请求头类型
        HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("text", "html", Charset.forName("UTF-8"));
        headers.setContentType(mt);
        InputStream is = upFile.getInputStream();
        System.out.println(is);
        Workbook hssfBook;
        if (upFile.getOriginalFilename().contains(".xlsx")) {
            hssfBook = new XSSFWorkbook(is);
        } else {
            hssfBook = new HSSFWorkbook(is);
        }
        //获取指定的工作簿
        Sheet sheet = hssfBook.getSheetAt(0);
        //读取标题行
        Row row = sheet.getRow(0);
        //获得标题行的所有列的数据
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            cellValue = cell.getStringCellValue();
            System.out.println(cellValue);
        }
        Integer ii = 2,
                cityAreaNum = 0,
                rowNum = sheet.getLastRowNum();
        if (rowNum == 0) {
            errMsg = "您上传的excel没有数据！";
            json = "{success:false, data:'" + errMsg + "'}";
            return new ResponseEntity<String>(json, headers, HttpStatus.OK);
        }
        int j = 0;
        int mj = 0;
        ArrayList<Object> errorlist;
        ArrayList<Bysxx> addlist;
        ArrayList<Bysxx> updatelist;

        try {
            errorlist = new ArrayList<>();
            addlist = new ArrayList<>();
            updatelist = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //循环余下的所有行，获取数据
            for (int i = 1; i <= rowNum; i++) {
                //获得当前行，判断是否为空，如果为空，则跳出这次循环
                System.out.println(sheet.getLastRowNum());
                Row row1 = sheet.getRow(i);
                System.out.println(i);
                if (row1 == null) {
                    continue;
                }
                Bysxx bysxx = new Bysxx();
                //获得当前行的所有单元格的内容，根据id查询学生信息，获得对象与学生对象进行对比
                int xn = (int) row1.getCell(0).getNumericCellValue();
                bysxx.setXn(xn + "");
                String xh = row1.getCell(1).getStringCellValue();
                bysxx.setXh(xh);
                String xm = row1.getCell(2).getStringCellValue();
                bysxx.setXm(xm);
                String yx = row1.getCell(3).getStringCellValue();
                bysxx.setYx(yx);
                String zy = row1.getCell(4).getStringCellValue();
                bysxx.setZy(zy);
                String bj = row1.getCell(5).getStringCellValue();
                bysxx.setBj(bj);
                String byzlqzt = row1.getCell(6).getStringCellValue();
                bysxx.setByzlqzt(byzlqzt);
                //Date byzlqsj = row1.getCell(7).getDateCellValue();
          /*  if(null!=byzlqsj){
                String format = sdf.format(byzlqsj);
                bysxx.setByzlqsj(byzlqsj);
            }*/
                HashMap<Object, Object> errorMap = new HashMap<>();
                if (null != xh) {
                    Bysxx bysxx1 = bysxxService.queryBysxxByXh(bysxx.getXh());
                    if (null != bysxx1) {
                        /*System.out.println(bysxx1);
                        System.out.println(bysxx);
                        if (bysxx1.equals(bysxx)) {
                            //equals对比存在则如果一致则跳过插入，  记录到重复list集合中
                            errorlist.add(bysxx);
                            System.out.println("=====重复增加了：" + (++mj));
                        } else {*/
                        // 如果不一致，根据id修改学生信息，将学生对象存入修改list集合
                        //批量修改学生信息
                        System.out.println("=====重复增加了：" + (++j));
                        errorMap.put("cw","学生信息重复");
                        errorMap.put("xn",bysxx.getXn());
                        errorMap.put("xh",bysxx.getXh());
                        errorMap.put("xm",bysxx.getXm());
                        errorMap.put("yx",bysxx.getYx());
                        errorMap.put("zy",bysxx.getZy());
                        errorMap.put("bj",bysxx.getBj());
                        errorlist.add(errorMap);
                        System.out.println(errorlist+"重复的数据");
                        // }
                    } else {
                        //如果数据库不存在当前学生信息，则存入到添加list集合
                        //批量插入学生信息
                        addlist.add(bysxx);
                        bysxxService.addBysxx(bysxx);
                        System.out.println(addlist);
                    }
                } else {
                    //学号为空，无效数据
                    errorMap.put("cw","学生信息无效");
                    errorMap.put("xn",bysxx.getXn());
                    errorMap.put("xh",bysxx.getXh());
                    errorMap.put("xm",bysxx.getXm());
                    errorMap.put("yx",bysxx.getYx());
                    errorMap.put("zy",bysxx.getZy());
                    errorMap.put("bj",bysxx.getBj());
                    errorlist.add(errorMap);
                    System.out.println("无效数据增加了一条");
                }
            }
        } catch (Exception e) {
            errMsg = "您上传的excel模板数据有误！";
            json = "{success:false, data:'" + errMsg + "'}";
            return new ResponseEntity<String>(json, headers, HttpStatus.OK);
        }
        //将上传的excel表格保存到项目目录下
     /*   try {
            upFile.transferTo(new File(file, newName));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if (0 < errorlist.size()) {
            errMsg = "2";
            session.setAttribute("errorList", errorlist);
        }
        errMsg = "总的数据：" + rowNum + "条, 成功导入数据：" + addlist.size() +/* "条,可更新的数据：" + updatelist.size() + */"条, 有问题的数据：" + errorlist.size() + "条。";
        json = "{success:true, data:'" + errMsg + "'}";
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }

    @RequestMapping("/bysxxError")
    @ResponseBody
    public List<Object> bysxxError(HttpSession session) {
        List<Object> errorList = (List<Object>)session.getAttribute("errorList");
        System.out.println(errorList+"错误的数据");
        return  errorList;
    }

    @RequestMapping("/swxxUpload")
    @SuppressWarnings("resource")
    @ResponseBody
    public ResponseEntity<String> swxxUpload(MultipartFile upFile1, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String json = "", errMsg = "1", cellValue = "";
        //获取当前服务器的webapp路径
        String webapp = session.getServletContext().getRealPath("/");
        File file = new File(webapp, "file");
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取到上传的文件名
        String fileName = upFile1.getOriginalFilename();
        //获取上传的文件类型
        String contentType = upFile1.getContentType();
        // System.out.println(contentType);
        //获取保存文件的位置
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newName = fileName + uuid + "." + FilenameUtils.getExtension(fileName);
        //进行保存上传的文件
        //设置请求头类型
        HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("text", "html", Charset.forName("UTF-8"));
        headers.setContentType(mt);
        InputStream is = upFile1.getInputStream();
        System.out.println(is);
        Workbook hssfBook;
        if (upFile1.getOriginalFilename().contains(".xlsx")) {
            hssfBook = new XSSFWorkbook(is);
        } else {
            hssfBook = new HSSFWorkbook(is);
        }
        //获取指定的工作簿
        Sheet sheet = hssfBook.getSheetAt(0);
        //读取标题行
        Row row = sheet.getRow(0);
        //获得标题行的所有列的数据
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            cellValue = cell.getStringCellValue();
            System.out.println(cellValue);
        }
        Integer ii = 2,
                cityAreaNum = 0,
                rowNum = sheet.getLastRowNum();
        if (rowNum == 0) {
            errMsg = "您上传的excel没有数据！";
            json = "{success:false, data:'" + errMsg + "'}";
            return new ResponseEntity<String>(json, headers, HttpStatus.OK);
        }
        int j = 0;
        int mj = 0;
        ArrayList<Object> errorlist;
        ArrayList<Swxx> addlist;
        ArrayList<Swxx> updatelist;
        try {
            errorlist = new ArrayList<>();
            addlist = new ArrayList<>();
            updatelist = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //循环余下的所有行，获取数据
            for (int i = 1; i <= rowNum; i++) {
                //获得当前行，判断是否为空，如果为空，则跳出这次循环
                System.out.println(sheet.getLastRowNum());
                Row row1 = sheet.getRow(i);
                System.out.println(i);
                if (row1 == null) {
                    continue;
                }
                Swxx swxx = new Swxx();
                //获得当前行的所有单元格的内容，根据id查询学生信息，获得对象与学生对象进行对比
                // int xn = (int) row1.getCell(0).getNumericCellValue();
                String xh = row1.getCell(0).getStringCellValue();
                swxx.setBysbh(xh);
                String swbm = row1.getCell(1).getStringCellValue();
                swxx.setSwbm(swbm);
                String sw = row1.getCell(2).getStringCellValue();
                swxx.setSw(sw);
                String swblzt = row1.getCell(3).getStringCellValue();
                swxx.setSwblzt(swblzt);
                HashMap<Object, Object> errorMap = new HashMap<>();
              /*  String bj = row1.getCell(5).getStringCellValue();

                String byzlqzt = row1.getCell(6).getStringCellValue();*/

                //Date byzlqsj = row1.getCell(7).getDateCellValue();
          /*  if(null!=byzlqsj){
                String format = sdf.format(byzlqsj);
                bysxx.setByzlqsj(byzlqsj);
            }*/
                System.out.println(swxx + "===============excel中的事务数据");
                if (null != swxx.getBysbh()) {
                    Swxx swxx1 = swxxService.querySwxxByBysbh(xh);
                    System.out.println(swxx1 + " -----");
                    if (null != swxx1) {
                       /* if (swxx1.equals(swxx)) */
                        {
                            //equals对比存在则如果一致则跳过插入，记录到重复list集合中
                            System.out.println("=====重复增加了：" + (++mj));
                            errorMap.put("cw","学生信息重复");
                            errorMap.put("sw",swxx.getSw());
                            errorMap.put("xh",swxx.getBysbh());
                            errorMap.put("swbm",swxx.getSwbm());
                            errorMap.put("swblzt",swxx.getSwblzt());
                            errorlist.add(errorMap);
                            System.out.println(errorlist+"重复的数据");
                        } /*else {
                            // 如果不一致，根据id修改学生信息，将学生对象存入修改list集合99
                            //批量修改学生信息
                            System.out.println("=====修改增加了：" + (++j));
                            updatelist.add(swxx);
                        }*/
                    } else {
                        //如果数据库不存在当前学生信息，则存入到添加list集合
                        //批量插入学生信息
                        addlist.add(swxx);
                        swxxService.addSwxx(swxx);
                        System.out.println(addlist + "插入事务信息");
                    }
                } else {
                    //学号为空，无效数据
                    errorMap.put("cw","学生信息重复");
                    errorMap.put("sw",swxx.getSw());
                    errorMap.put("xh",swxx.getBysbh());
                    errorMap.put("swbm",swxx.getSwbm());
                    errorMap.put("swblzt",swxx.getSwblzt());
                    errorlist.add(errorMap);
                    System.out.println("无效数据增加了一条");
                }
            }
        } catch (Exception e) {
            errMsg = "您上传的excel模板数据有误！";
            json = "{success:false, data:'" + errMsg + "'}";
            return new ResponseEntity<String>(json, headers, HttpStatus.OK);
        }
        //将上传的excel表格保存到项目目录下
       /* try {
            upFile1.transferTo(new File(file, newName));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if (0 < errorlist.size()) {
            errMsg = "2";
            session.setAttribute("errorList", errorlist);
        }
        errMsg = "总的数据：" + rowNum + "条, 成功导入数据：" + addlist.size()/* + "条,可更新的数据：" + updatelist.size()*/ + "条, 有问题的数据：" + errorlist.size() + "条.";
        json = "{success:true, data:'" + errMsg + "'}";
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }
}
