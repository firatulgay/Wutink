package com.fulgay.wutink.controllers;

import com.fulgay.wutink.facades.ExperienceOperationsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@RestController
@RequestMapping("operations")
public class ExperienceOperationsController {

    @Autowired
    private ExperienceOperationsFacade experienceOperationsFacade;

    @GetMapping("/like")
    public boolean likeExprience(@PathParam("experienceId") Long experienceId,@PathParam("username") String username) {
        experienceOperationsFacade.likeExprience(experienceId, username);
        return true;
    }

    @GetMapping("/unlike")
    public void unlikeExprience(@PathParam("experienceId") Long experienceId,@PathParam("username") String username) {
        experienceOperationsFacade.unlikeExperience(experienceId, username);
    }

    @PostMapping("/comment")
    public void commentExprience(Long experienceId, Long userId, String comment) {

    }
}
