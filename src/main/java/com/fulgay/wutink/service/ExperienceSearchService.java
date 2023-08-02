package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;

import java.util.List;

public interface ExperienceSearchService {
    List<ExperienceDto> searchExperienceByText(String searchText);

}
