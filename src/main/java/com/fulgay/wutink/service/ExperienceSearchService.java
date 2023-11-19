package com.fulgay.wutink.service;

import com.fulgay.wutink.dtos.response.experience.ExperienceDto;

import java.util.List;

public interface ExperienceSearchService {
    List<ExperienceDto> searchExperienceByText(String searchText);

}
