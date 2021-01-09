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

    @GetMapping("/getExperiences")
    public List<ExperienceDto> findAllExperiences(){
        List<ExperienceDto> allExperiences = experienceFacade.findAllExperiences();
        return allExperiences;
    }

//    @GetMapping("/getExperienceById/{id}")
//    public ExperienceDto findExperienceById(@PathVariable("id") Long id){
//        ExperienceDto experienceById = experienceFacade.findExperienceById(id);
//        return experienceById;
//    }
//
//    @GetMapping("/getExperienceByExperienceName")
//    public ExperienceDto findExperienceByExperienceName(@PathParam("experienceName") String experienceName){
//        ExperienceDto experienceDto = experienceFacade.findExperienceByExperienceName(experienceName);
//        return experienceDto;
//    }

    @GetMapping("/getAllExperienceByCategoryId/{id}")
    public List<ExperienceDto> findAllExperienceByCategoryId(@PathVariable("id") Long id){
        List<ExperienceDto> experienceDtoList = experienceFacade.findAllExperienceByCategoryId(id);
        return experienceDtoList;
    }
}
