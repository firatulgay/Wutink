package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.GlobalMessages;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.service.Cat2ExService;
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
