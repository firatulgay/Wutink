package com.fulgay.wutink.facades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.converter.ExperienceConverter;
import com.fulgay.wutink.converter.ExperienceDtoConverter;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceCategoryContainerDto;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.dtos.response.experience.UserExperienceResponseDto;
import com.fulgay.wutink.service.Cat2ExService;
import com.fulgay.wutink.service.CategoryService;
import com.fulgay.wutink.service.ExperienceService;
import com.fulgay.wutink.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    @Autowired
    private UserService userService;

    private GlobalMessages globalMessage;

    @Transactional(rollbackFor = Exception.class)
    public ExperienceDto save(ExperienceDto experienceDto) {

        try {
            Experience experience = experienceConverter.convert(experienceDto);
            experienceService.save(experience);

            List<Long> categoryIdList = experienceDto.getCategoryIdList();

            categoryIdList.forEach(categoryId -> {
                Category category = categoryService.findById(categoryId);
                cat2ExFacade.saveCat2ExRel(experience, category);
            });

            setSuccessGlobalMessage(experienceDto);
            experienceDto = experienceDtoConverter.convert(experience);

        } catch (Exception e) {
            LOG.error(EnumErrorMessage.EXPERIENCE_COULDNT_SAVE.getValue(), e);
            GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,EnumErrorMessage.EXPERIENCE_COULDNT_SAVE.getValue());
            experienceDto.setGlobalMessage(globalMessage);
        }

        return experienceDto;
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

    public List<ExperienceDto> findExperiencesByCategoryIdWithLikeAndCommentCount(Long id) {
        List<ExperienceDto> experienceDtoList = cat2ExService.findExperiencesByCategoryIdWithLikeAndCommentCount(id);
        String pattern = "dd MMMMM yyyy";
        experienceDtoList.forEach(experienceDto -> experienceDto.setCreationTimeStr(new SimpleDateFormat(pattern, new Locale("tr", "TR")).format(experienceDto.getCreationTimeDate())));

        return experienceDtoList;
    }

    public List<ExperienceDto> findExperienceByHeader(String header) {
        List<Experience> experienceList = experienceService.findExperienceByHeader(header);
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceList);
        return experienceDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteExperienceById(Long id) {
        Experience experience = experienceService.findById(id);
        experienceService.delete(experience);
        cat2ExService.deleteRelByExperience(experience);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateExperience(ExperienceDto experienceDto) {
        Experience experience = experienceService.findById(experienceDto.getId());

        experience.setDescription(experienceDto.getDescription());
        experience.setHeader(experienceDto.getHeader());

        experienceService.update(experience);
        updateCat2ExRel(experienceDto, experience);

        LOG.info("Experience updated successfully with id : "+ experience.getId());
    }

    private void updateCat2ExRel(ExperienceDto experienceDto, Experience experience) {

        if (!CollectionUtils.isEmpty(experienceDto.getCategoryIdList())){
            List<Category> newCategoryList = new ArrayList<>();

            experienceDto.getCategoryIdList().forEach(categoryId ->{
                newCategoryList.add(categoryService.findById(categoryId));
            });

            List<Cat2Ex> cat2ExList = cat2ExService.findAllByExperience(experience);
            if (!CollectionUtils.isEmpty(cat2ExList)){
                cat2ExList.forEach(cat2Ex -> cat2ExService.delete(cat2Ex));
            }

            newCategoryList.forEach(category -> {
                Cat2Ex newCat2ExRel = new Cat2Ex();
                newCat2ExRel.setExperience(experience);
                newCat2ExRel.setCategory(category);

                cat2ExService.save(newCat2ExRel);
            });
        }
    }

    public ExperienceDto findExperienceById(Long id) {
        return experienceDtoConverter.convert(experienceService.findById(id));
    }

    private void setSuccessGlobalMessage(ExperienceDto experienceDto) {
        globalMessage = new GlobalMessages(EnumMessageType.CONF_MESSAGE,EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getValue());
        experienceDto.setGlobalMessage(globalMessage);
        LOG.info(experienceDto.getHeader() + " " + EnumSuccessMessage.EXPERIENCE_SAVE_SUCCESS.getValue());
    }


    public UserExperienceResponseDto findAllExperiencesByUsernameGroupByCategory(String username) throws JsonProcessingException {
        List<ExperienceDto> experienceDtoList = experienceDtoConverter.convertToList(experienceService.findAllExperiencesByUsername(username));
        UserExperienceResponseDto userExperienceResponseDto = new UserExperienceResponseDto();
        List<ExperienceCategoryContainerDto> experienceCategoryContainerDtoList = new ArrayList<>();

        List<Long> allCategoryIdList = new ArrayList<>();
        for (ExperienceDto experienceDto : experienceDtoList) {
            for (Long categoryId : experienceDto.getCategoryIdList()) {
                if (!allCategoryIdList.contains(categoryId))
                    allCategoryIdList.add(categoryId);
            }
        }

        for (Long categoryId: allCategoryIdList) {
            ExperienceCategoryContainerDto experienceCategoryContainerDto = new ExperienceCategoryContainerDto();
            experienceCategoryContainerDto.setCategoryId(categoryId);

            List<ExperienceDto> experienceDtoListByCategory = new ArrayList<>();
            for (ExperienceDto experienceDto : experienceDtoList) {
                for (Long catId : experienceDto.getCategoryIdList()) {
                    if (catId.equals(categoryId)){
                        experienceDtoListByCategory.add(experienceDto);
                    }
                }
            }
            experienceCategoryContainerDto.setExperienceDtoList(experienceDtoListByCategory);
            experienceCategoryContainerDtoList.add(experienceCategoryContainerDto);
        }
        userExperienceResponseDto.setExperienceContainerResponseDtoList(experienceCategoryContainerDtoList);
        return userExperienceResponseDto;
    }

    public List<ExperienceDto> findAllExperiencesByPage(Pageable pageable) {

        Page<ExperienceDto> allExperiencesByPage = experienceService.findAllExperiencesByPage(pageable);
        List<ExperienceDto> experienceDtoList = allExperiencesByPage.getContent();

        String pattern = "dd MMMMM yyyy";
        experienceDtoList.forEach(experienceDto -> experienceDto.setCreationTimeStr(new SimpleDateFormat(pattern, new Locale("tr", "TR")).format(experienceDto.getCreationTimeDate())));

        return experienceDtoList;

    }
}
