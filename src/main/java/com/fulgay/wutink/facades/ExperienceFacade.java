package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.converter.ExperienceConverter;
import com.fulgay.wutink.converter.ExperienceDtoConverter;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import com.fulgay.wutink.service.Cat2ExService;
import com.fulgay.wutink.service.CategoryService;
import com.fulgay.wutink.service.ExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExperienceFacade {

    private static final Logger LOG = Logger.getLogger(ExperienceFacade.class);

    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Cat2ExFacade cat2ExFacade;
    @Autowired
    private Cat2ExService cat2ExService;
    @Autowired
    private ExperienceDtoConverter experienceDtoConverter;
    @Autowired
    private ExperienceConverter experienceConverter;

    private GlobalMessages globalMessage;

    public ExperienceDto save(ExperienceDto experienceDto) {

        try {
            Experience experience = experienceConverter.convert(experienceDto);
            experienceService.save(experience);

            Long categoryId = experienceDto.getCategoryId();
            Category category = categoryService.findById(categoryId);

            cat2ExFacade.saveCat2ExRel(experience, category);
            setSuccessGlobalMessage(experienceDto);

            experienceDto = experienceDtoConverter.convert(experience);

        } catch (Exception e) {
            LOG.error(EnumErrorMessage.EXPERIENCE_COULDNT_SAVE.getValue() + " -> " + e.getMessage());
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.EXPERIENCE_COULDNT_SAVE.getValue());
            experienceDto.setGlobalMessage(globalMessage);
        }

        return experienceDto;
    }

    private void setSuccessGlobalMessage(ExperienceDto experienceDto) {
        globalMessage = new GlobalMessages();
        globalMessage.setConfMessage(EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getValue());
        experienceDto.setGlobalMessage(globalMessage);
        LOG.info(experienceDto.getHeader() + " " + EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getValue());
    }

    public List<ExperienceDto> findAllExperiences() {
        List<Experience> experienceList = experienceService.findAll();
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);

        return experienceDtoList;
    }

    public List<ExperienceDto> findAllExperienceByCategoryId(Long id) {
        List<Experience> experienceList = cat2ExService.findExperienceByCategoryId(id);
        return experienceDtoConverter.convertToList(experienceList);
    }

    public List<ExperienceDto> findExperienceByHeader(String header) {
        List<Experience> experienceList = experienceService.findExperienceByHeader(header);
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);
        return experienceDtoList;
    }
}
