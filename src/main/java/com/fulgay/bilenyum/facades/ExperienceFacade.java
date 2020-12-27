package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.populators.ExperiencePopulator;
import com.fulgay.bilenyum.service.ExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceFacade {

    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private ExperiencePopulator experiencePopulator;

}
