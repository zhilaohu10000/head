package com.zyzh.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 
 * @author ：yanxj@zparkhr.com.cn
 * @time :2017-12-1 上午11:05:07
 * @类的描述：
 *       1、对冗余代码的提取
 *       2、把数据库连接相关的参数书写在了配置文件中
 *       3、在静态代码块中读取配置文件，提高运行效率
 *       4、使用ThreadLocal，对Connection进行绑定，保证事务的完整
 * @version：1.0
 */
public class JDBCUtil {
	private static Properties pro = new Properties();
	private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();//创建ThreadLocal
	static{
		InputStream is = JDBCUtil.class.getResourceAsStream("/conf/db.properties");
		try {
			pro.load(is);
			Class.forName(pro.getProperty("oracle.driverClassName"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConn(){
		
		
		Connection conn = tl.get();//从ThreadLocal中获得Connection
		if(conn==null){//第一次调用
			try {
				conn = DriverManager.getConnection(pro.getProperty("oracle.url"), pro.getProperty("oracle.username"), pro.getProperty("oracle.password"));
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
				tl.remove();//*******************一定要移除
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
