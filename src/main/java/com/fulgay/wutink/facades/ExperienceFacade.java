package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.EnumErrorMessage;
import com.fulgay.wutink.commons.EnumSuccessMessage;
import com.fulgay.wutink.commons.GlobalMessages;
import com.fulgay.wutink.converter.ExperienceConverter;
import com.fulgay.wutink.converter.ExperienceDtoConverter;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.CategoryDto;
import com.fulgay.wutink.dtos.ExperienceDto;
import com.fulgay.wutink.service.Cat2ExService;
import com.fulgay.wutink.service.CategoryService;
import com.fulgay.wutink.service.ExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExperienceFacade {

    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

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

    @Transactional(rollbackFor = Exception.class)
    public ExperienceDto save(ExperienceDto experienceDto) {

        try {
            Experience experience = experienceConverter.convert(experienceDto);
            experienceService.save(experience);

            CategoryDto categoryDto = experienceDto.getCategoryDto();
            Category category = categoryService.findById(categoryDto.getId());

            cat2ExFacade.saveCat2ExRel(experience, category);
            setSuccessGlobalMessage(experienceDto);

            experienceDto = experienceDtoConverter.convert(experience);

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_COULDNT_SAVE.getValue());
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
        List<Experience> experienceList = experienceService.findExperienceByHeader(header);
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);
        return experienceDtoList;
    }
}
