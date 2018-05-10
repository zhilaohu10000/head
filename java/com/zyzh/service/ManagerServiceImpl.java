package com.zyzh.service;

import com.zyzh.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
   @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int queryCount() {
       int i = managerDao.selectCount();
       System.out.println(i);
       return i;
    }
}
