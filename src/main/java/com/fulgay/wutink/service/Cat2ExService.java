package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Experience;

import java.util.List;

public interface Cat2ExService extends BaseService<Cat2Ex> {
    List<Experience> findExperienceByCategoryId(Long id);

}
