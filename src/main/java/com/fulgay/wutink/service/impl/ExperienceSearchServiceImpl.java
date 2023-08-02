package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.converter.ExperienceDtoConverter;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import com.fulgay.wutink.repository.ExperienceRepository;
import com.fulgay.wutink.service.ExperienceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceSearchServiceImpl implements ExperienceSearchService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceDtoConverter experienceDtoConverter;

    @Override
    public List<ExperienceDto> searchExperienceByText(String searchText) {
        List<Experience> experienceList = experienceRepository.findByHeaderOrDescriptionContainingIgnoreCase(searchText, searchText);
        return experienceDtoConverter.convertToList(experienceList);
    }
}
