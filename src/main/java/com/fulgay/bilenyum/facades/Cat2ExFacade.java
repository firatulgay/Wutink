package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.domain.Cat2Ex;
import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.service.Cat2ExService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cat2ExFacade {
    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

    @Autowired
    private Cat2ExService cat2ExService;


    private GlobalMessages globalMessage;

    public void saveCat2ExRel(Experience experience, Category category){


        Cat2Ex cat2Ex = new Cat2Ex();
        cat2Ex.setCategory(category);
        cat2Ex.setExperience(experience);

        cat2ExService.save(cat2Ex);

    }




}
