package com.zyzh.service;

import com.zyzh.dao.DataBaseDao;
import com.zyzh.dao.MysqlDao;
import com.zyzh.entity.DataBase;
import com.zyzh.util.DataBaseJDBCUtil;
import com.zyzh.util.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MysqlServiceImpl implements MysqlService {
    @Autowired
    private MysqlDao mysqlDao;
    @Autowired
    private DataBaseDao dataBaseDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int queryAllCount() {
        System.out.println("============mysql数据库==============");
        //DataSourceContextHolder.setDataSourceType("mysqldataTwo");

        DataBase dataBase = dataBaseDao.selectDataBaseById("2");
        ResultSet rs = DataBaseJDBCUtil.getResultSet(dataBase,null);
        try {
            while(rs.next()){//rs的下一行是否有数据 ：true 有 ，游标下移获得下一行数据  false ：结果集的末尾
                System.out.print(rs.getString(1)+" , ");
                System.out.println("=============================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //处理结果集
        //int i = mysqlDao.selectAllCount();
        return 0;
    }
}
