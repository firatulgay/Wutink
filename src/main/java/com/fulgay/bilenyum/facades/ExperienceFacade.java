package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.converter.ExperienceConverter;
import com.fulgay.bilenyum.converter.ExperienceDtoConverter;
import com.fulgay.bilenyum.domain.Cat2Ex;
import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.service.Cat2ExService;
import com.fulgay.bilenyum.service.CategoryService;
import com.fulgay.bilenyum.service.ExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//import com.fulgay.bilenyum.populators.ExperienceDtoPopulator;
//import com.fulgay.bilenyum.populators.ExperiencePopulator;

@Component
public class ExperienceFacade {

    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    Cat2ExFacade cat2ExFacade;

    @Autowired
    Cat2ExService cat2ExService;

    @Autowired
    private ExperienceDtoConverter experienceDtoConverter;

    @Autowired
    private ExperienceConverter experienceConverter;

    private GlobalMessages globalMessage;

    @Transactional(rollbackFor = Exception.class)
    public ExperienceDto save(ExperienceDto experienceDto) {

        try {
            Experience experience = experienceConverter.convert(experienceDto);
            experienceService.save(experience);

            CategoryDto categoryDto = experienceDto.getCategoryDto();
            Category category = categoryService.findCategoryById(categoryDto.getId());

            cat2ExFacade.saveCat2ExRel(experience, category);
            setSuccessGlobalMessage(experienceDto);

            experienceDto = experienceDtoConverter.convert(experience);

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_COULDNT_SAVE.getDisplayValue());
            experienceDto.setGlobalMessage(globalMessage);
        }

        return experienceDto;
    }

    private void setSuccessGlobalMessage(ExperienceDto experienceDto) {
        globalMessage = new GlobalMessages();
        globalMessage.setConfMessage(EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getDisplayValue());
        experienceDto.setGlobalMessage(globalMessage);
        LOG.info(experienceDto.getHeader() + " " + EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getDisplayValue());
    }

    public List<ExperienceDto> findAllExperiences() {
        List<Experience> experienceList = experienceService.findAllExperiences();
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);

        return experienceDtoList;
    }

    public List<ExperienceDto> findAllExperienceByCategoryId(Long id) {
        List<Cat2Ex> cat2ExList = cat2ExService.findCat2ExRelByCategoryId(id);

        List<Experience> experienceList = new ArrayList<>();

        for (Cat2Ex cat2Ex : cat2ExList) {
            Experience experience = cat2Ex.getExperience();
            experienceList.add(experience);
        }
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);
        return experienceDtoList;
    }

    public List<ExperienceDto> findExperienceByHeader(String header) {
        List<Experience> experienceList = experienceService.findExperienceHeader(header);
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);
        return experienceDtoList;
    }
}
