package com.fulgay.bilenyum.controllers;

import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.facades.ExperienceFacade;
import com.fulgay.bilenyum.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @Autowired
    ExperienceFacade experienceFacade;

    @PostMapping("/saveExperience")
    public ExperienceDto saveExperience(@RequestBody ExperienceDto experienceDto){
        ExperienceDto savedExperience = experienceFacade.save(experienceDto);
        return savedExperience;
    }

    @GetMapping("/getAllExperiences")
    public List<ExperienceDto> findAllExperiences(){
        List<ExperienceDto> allExperiences = experienceFacade.findAllExperiences();
        return allExperiences;
    }

    @GetMapping("/getAllExperiencesByHeader/{header}")
    public List<ExperienceDto> findExperienceByExperienceName(@PathVariable("header") String header){
        List<ExperienceDto> experienceDtoList = experienceFacade.findExperienceByHeader(header);
        return experienceDtoList;
    }

    @GetMapping("/getAllExperienceByCategoryId/{id}")
    public List<ExperienceDto> findAllExperienceByCategoryId(@PathVariable("id") Long id){
        List<ExperienceDto> experienceDtoList = experienceFacade.findAllExperienceByCategoryId(id);
        return experienceDtoList;
    }
}
