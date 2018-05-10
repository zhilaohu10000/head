package com.zyzh.util;

import com.zyzh.entity.DataBase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 
 *
 * @类的描述：
 *       1、对冗余代码的提取
 *       2、把数据库连接相关的参数书写在了配置文件中
 *       3、在静态代码块中读取配置文件，提高运行效率
 *       4、使用ThreadLocal，对Connection进行绑定，保证事务的完整
 * @version：1.0
 */
public class DataBaseJDBCUtil {
	public static ResultSet getResultSet(DataBase dataBase,String swbm){
		Connection conn=null;
		ResultSet rs=null;
		try {
			Class.forName(dataBase.getDriver());
			conn = DriverManager.getConnection(dataBase.getUrl(), dataBase.getUsername(), dataBase.getPassward());
			PreparedStatement pstmt = conn.prepareStatement(dataBase.getSqlxx());//对sql语句的预编译
			if(swbm!=null){
				pstmt.setString(1,swbm);
			}
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
