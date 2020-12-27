package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExperiencePopulator {

    private List<ExperienceDto> experienceDtoList;
    private Experience experience;
    private ExperienceDto experienceDto;
    private List<Experience> experienceList;

    public List<ExperienceDto> populateExperienceDtoList(List<Experience> experienceList) {
        if (experienceList != null && experienceList.size() > 0) {
            experienceDtoList = new ArrayList<>();

            for (Experience experience : experienceList) {
                ExperienceDto experienceDto = populateExperienceDto(experience);
                experienceDtoList.add(experienceDto);
            }
        }
        return experienceDtoList;
    }

    public ExperienceDto populateExperienceDto(Experience experience) {
        experienceDto = new ExperienceDto();

        if (experience != null) {
        }
        return experienceDto;
    }

    public Experience populateExperience(ExperienceDto experienceDto) {
        experience = new Experience();
        if (experienceDto != null) {
            experience.setId(experienceDto.getId());
        }
        return experience;
    }

    public List<Experience> populateExperienceList(List<ExperienceDto> experienceDtoList) {
        if (experienceDtoList != null && experienceDtoList.size() > 0) {
            experienceList = new ArrayList<>();

            for (ExperienceDto experienceDto : experienceDtoList) {
                Experience experience = populateExperience(experienceDto);
                this.experienceList.add(experience);
            }
        }
        return experienceList;
    }
}
