package com.fulgay.wutink.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.dtos.response.experience.UserExperienceResponseDto;
import com.fulgay.wutink.facades.ExperienceFacade;
import com.fulgay.wutink.security.annotation.DeleteGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperienceController {

    @Autowired
    private ExperienceFacade experienceFacade;

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

    @GetMapping("/getAllExperiencesByPage")
    public List<ExperienceDto> findAllExperiencesByPage(Pageable pageable) {
        return experienceFacade.findAllExperiencesByPage(pageable);
    }

    @GetMapping("/getAllExperiencesByHeader/{header}")
    public List<ExperienceDto> findExperienceByExperienceName(@PathVariable("header") String header){
        List<ExperienceDto> experienceDtoList = experienceFacade.findExperienceByHeader(header);
        return experienceDtoList;
    }

    @GetMapping("/getExperienceById/{id}")
    public ExperienceDto findExperienceById(@PathVariable("id") Long id){
        ExperienceDto experienceDto = experienceFacade.findExperienceById(id);
        return experienceDto;
    }

    @GetMapping("/getAllExperienceByCategoryId/{id}")
    public List<ExperienceDto> findExperiencesByCategoryIdWithLikeAndCommentCount(@PathVariable("id") Long id){
        return experienceFacade.findExperiencesByCategoryIdWithLikeAndCommentCount(id);
    }

    @GetMapping("/getAllExperiencesByUsername/{username}")
    public UserExperienceResponseDto findAllExperienceByUsername(@PathVariable("username") String username) throws JsonProcessingException {
        return experienceFacade.findAllExperiencesByUsernameGroupByCategory(username);
    }

    @DeleteMapping("/deleteExperienceById")
    @DeleteGuard(parameterIndex = 0)
    public void deleteExperienceById(@RequestParam("id") Long id){
        experienceFacade.deleteExperienceById(id);
    }

    @PostMapping("/updateExperience")
    public void updateExperience(@RequestBody ExperienceDto experienceDto){
        experienceFacade.updateExperience(experienceDto);
    }
}
