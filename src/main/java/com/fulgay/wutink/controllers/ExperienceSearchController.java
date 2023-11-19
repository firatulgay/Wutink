package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.service.ExperienceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExperienceSearchController {

    @Autowired
    private ExperienceSearchService experienceSearchService;


    @GetMapping("search/{text}")
    public List<ExperienceDto> searchExperienceByText(@PathVariable("text") String searchText){
        return experienceSearchService.searchExperienceByText(searchText);
    }
}
