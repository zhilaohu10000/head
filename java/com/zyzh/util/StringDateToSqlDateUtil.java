package com.zyzh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @author ：yanxj@zparkhr.com.cn
 * @time :2017-11-28 下午3:13:56
 * @类的描述：把给定的字符串日期转换为sqlDate，方便数据库操作
 * @version：1.0
 */
public class StringDateToSqlDateUtil {
	/**
	 * 封装一个函数 ：把给定的字符串日期转换为sqlDate
	 * 返回值：java.sql.Date
	 * 参数表：String，给定的字符串日期
	 */
	public static java.sql.Date getSqlDate(String strDate){
		//String---utilDate
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		java.util.Date utilDate;
		try {
			utilDate = sdf.parse(strDate);
			//utilDate---sqlDate
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
}
