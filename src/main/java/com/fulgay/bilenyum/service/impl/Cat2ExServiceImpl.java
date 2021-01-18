package com.fulgay.bilenyum.service.impl;

import com.fulgay.bilenyum.dao.Cat2ExDao;
import com.fulgay.bilenyum.domain.Cat2Ex;
import com.fulgay.bilenyum.service.Cat2ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Cat2ExServiceImpl implements Cat2ExService {
    @Autowired
    private Cat2ExDao cat2ExDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(Cat2Ex cat2Ex) {
        try {
            return cat2ExDao.save(cat2Ex);
        } catch (UnexpectedRollbackException e) {
            return null;
        }
    }

    @Override
    public List<Cat2Ex> findAll() {

        return cat2ExDao.findAll();
    }

    @Override
    public Cat2Ex findById(Long id) {
        return cat2ExDao.findById(id);
    }

    @Override
    public void update(Cat2Ex obj) {

    }

    @Override
    public List<Cat2Ex> findCat2ExRelByCategoryId(Long id) {
        return cat2ExDao.findCat2ExRelByCategoryId(id);
    }
}
