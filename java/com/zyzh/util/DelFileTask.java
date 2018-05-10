package com.zyzh.util;

import com.zyzh.dao.MysqlDao;
import com.zyzh.dao.SwxxDao;
import com.zyzh.entity.Swxx;
import com.zyzh.service.SwxxService;
import com.zyzh.util.JDBCUtil;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.security.util.Resources_sv;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DelFileTask extends TimerTask {
    private HashMap<String, List<Swxx>> map = new HashMap<>();
    private static boolean isRunning = false;
    private ServletContext context = null;
    public DelFileTask(ServletContext context) {
        this.context = context;
    }
    @Override
    public void run() {
        if (!isRunning) {
            context.log("开始执行指定任务");
            /**
             * 自己的业务代码
             * 查询本地离校事务信息，如何存在，则开启
             设置定时器，时间是五分钟
             在数据中心中查询所有的事务信息存放在listOne中，将listOne存放在session中
             int i = mysqlService.queryAllCount();
             五分钟后，再次在数据中心查询所有的事务信息存放在listTwo中，将listTwo存放在session中
             int j = mysqlService.queryAllCount();
             listOne与listTwo进行取余，将余下的事务数据在本数据库中更改状态未已办理，增加办理时间，和办理详情
             */
            System.out.println("开始执行定时器的功能");
            PreparedStatement pstm = null;
            ResultSet rs = null;
            Connection conn = null;
            List<Swxx> swxxList = new ArrayList<>();
            try {
                conn = JDBCUtil.getConn();
                String sql = "select swbm,sw,swblzt,swblsj,bysbh from X_SWXX where swblzt='未办理'";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Swxx swxx = new Swxx();
                    swxx.setSwbm(rs.getString(1));
                    swxx.setSw(rs.getString(2));
                    swxx.setSwblzt(rs.getString(3));
                    swxx.setSwblsj(rs.getDate(4));
                    swxx.setBysbh(rs.getString(5));
                    swxxList.add(swxx);
                }
                if (map.isEmpty() || map == null) {
                    map.put("one", swxxList);
                    System.out.println("==========第一次=============");
                } else {
                    List<Swxx> one = map.get("one");
                    //两个集合取余，将余下的数据修改事务办理状态
                    one.removeAll(swxxList);
                    //将one的数据状态修改为已办理
                    Connection conn2 = JDBCUtil.getConn();
                    String sql2 = " update X_SWXX set swblzt=?,swblsj=? where bysbh=? and swbm=?";
                    PreparedStatement pstm2 = null;
                    for (Swxx swxx1 : one) {
                        pstm2 = conn2.prepareStatement(sql2);
                        pstm2.setString(1, "已办理");
                        pstm2.setDate(2, new Date(new java.util.Date().getTime()));
                        pstm2.setString(3, swxx1.getBysbh());
                        pstm2.setString(4, swxx1.getSwbm());
                        pstm2.executeUpdate();
                    }
                    JDBCUtil.close(conn2, pstm2, null);
                    System.out.println(one + "事务数据消失掉的数据");
                    map.put("one", swxxList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtil.close(conn, pstm, rs);
            isRunning = false;
            context.log("指定任务执行结束");
        } else {
            context.log("上一次任务执行还未结束");
        }
    }
}
