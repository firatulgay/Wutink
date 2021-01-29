package com.fulgay.wutink.dao;

import com.fulgay.wutink.dao.BaseDao;
import com.fulgay.wutink.domain.Cat2Ex;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public abstract class Cat2ExDao extends BaseDao<Cat2Ex> {

    public Cat2ExDao() {
        super(Cat2Ex.class);
    }

    public abstract List<Cat2Ex> findCat2ExRelByCategoryId(Long id);
}

