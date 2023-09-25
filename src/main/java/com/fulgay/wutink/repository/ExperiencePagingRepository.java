package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExperiencePagingRepository extends PagingAndSortingRepository <Experience,Long> {
}
