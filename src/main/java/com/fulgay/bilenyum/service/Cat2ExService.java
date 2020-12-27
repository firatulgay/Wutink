package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.Cat2ExDao;
import com.fulgay.bilenyum.domain.Cat2Ex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Cat2ExService {
    @Autowired
    Cat2ExDao cat2ExDao;

    @Transactional(rollbackFor = Exception.class)
    public void save(Cat2Ex cat2Ex) {
        try {
            cat2ExDao.save(cat2Ex);
        } catch (UnexpectedRollbackException e) {
        }
    }
}
