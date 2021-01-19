package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Cat2Ex;

import java.util.List;

public interface Cat2ExService extends BaseService<Cat2Ex> {
    List<Cat2Ex> findCat2ExRelByCategoryId(Long id);

}
