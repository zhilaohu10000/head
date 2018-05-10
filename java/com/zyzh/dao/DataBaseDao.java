package com.zyzh.dao;

import com.zyzh.entity.DataBase;
import org.apache.ibatis.annotations.Param;

public interface DataBaseDao {
 DataBase selectDataBaseById(@Param("id")String id);
}
