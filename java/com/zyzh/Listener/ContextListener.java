package com.zyzh.Listener;


import com.zyzh.util.DelFileTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;

public class ContextListener implements ServletContextListener {
    public ContextListener() {}
    private java.util.Timer timer = null;
    /**
     * 初始化定时器
     * web 程序运行时候自动加载
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //获得Spring容器
        /*WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
        //从Spring容器中获得SelectDataServlet的实例
        SwxxService swxxService =  ctx.getBean(SwxxService.class);*/

        timer = new java.util.Timer(true);

        arg0.getServletContext().log("定时器已启动");

        // 定时器到指定的时间时,执行某个操作(如某个类,或方法)

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的8:00:00执行，
        calendar.set(year, month, day, 8, 00, 00);
        Date date = calendar.getTime();
        int period = 5*60 * 1000;
        //每天的date时刻执行task，每隔persion 时间重复执行
        timer.schedule(new DelFileTask(arg0.getServletContext()), date, period);
        //在指定的date时刻执行task, 仅执行一次
        //timer.schedule(new DelFileTask(arg0.getServletContext()), date);
        arg0.getServletContext().log("已经添加任务调度表");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
        sce.getServletContext().log("定时器销毁");
    }
}
