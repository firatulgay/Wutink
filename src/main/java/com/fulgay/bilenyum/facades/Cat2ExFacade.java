package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.service.Cat2ExService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Cat2ExFacade {
    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

    @Autowired
    private Cat2ExService cat2ExService;

    @Autowired
    CategoryFacade categoryFacade;



    private GlobalMessages globalMessage;

    public void saveCat2ExRel(ExperienceDto experienceDto){
        CategoryDto categoryDto = experienceDto.getCategoryDto();

        CategoryDto category = categoryFacade.findCategoryById(categoryDto.getId());

    }




}
