package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.Cat2ExDao;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.service.Cat2ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cat2ExServiceImpl implements Cat2ExService {
    @Autowired
    private Cat2ExDao cat2ExDao;

    @Override
    public Long save(Cat2Ex cat2Ex) {
        return cat2ExDao.save(cat2Ex);
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
