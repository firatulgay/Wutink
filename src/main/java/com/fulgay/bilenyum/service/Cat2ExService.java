package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.domain.Cat2Ex;

import java.util.List;

public interface Cat2ExService extends BaseService<Cat2Ex> {
    List<Cat2Ex> findCat2ExRelByCategoryId(Long id);

}
