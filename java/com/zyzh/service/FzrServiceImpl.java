package com.zyzh.service;

import com.zyzh.dao.FzrDao;
import com.zyzh.entity.Fzr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FzrServiceImpl implements FzrService {
    @Autowired
    private FzrDao fzrDao;
    @Override
    public void addFzr(Fzr fzr) {
        fzrDao.insertFzr(fzr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Fzr> queryAllFzr(String flbh) {
        return fzrDao.selectAllFzr(flbh);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public  Fzr QuerySzbm(String id) {
        return fzrDao.selectSzbm(id);
    }

    @Override
    public void modfiyYxFzr(Fzr fzr) {
        fzrDao.updateYxFzr(fzr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Fzr queryBmByName(String name) {
        return  fzrDao.selectBmByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Fzr queryBmBySzbm(String name) {
        return fzrDao.selectBmBySzbm(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> queryAllYx() {
        return fzrDao.selectAllYx();
    }
}
