package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.Cat2Ex;
import org.springframework.stereotype.Repository;

@Repository
public class Cat2ExDao extends BaseDao<Cat2Ex>{
    public Cat2ExDao() {
        super(Cat2Ex.class);
    }

}
