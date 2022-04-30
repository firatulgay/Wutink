package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.dtos.PageSizeDto;
import com.fulgay.wutink.facades.CommentFacade;
import com.fulgay.wutink.facades.ExperienceOperationsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@RestController
@RequestMapping("operations")
public class ExperienceOperationsController {

    @Autowired
    private ExperienceOperationsFacade experienceOperationsFacade;

    @Autowired
    private CommentFacade commentFacade;

    @GetMapping("/like")
    public boolean likeExperience(@PathParam("experienceId") Long experienceId) {
        experienceOperationsFacade.likeExprience(experienceId);
        return true;
    }

    @GetMapping("/unlike")
    public void unlikeExperience(@PathParam("experienceId") Long experienceId) {
        experienceOperationsFacade.unlikeExperience(experienceId);
    }

    @PostMapping("/doComment")
    public void commentExperience(@RequestBody CommentDto commentDto) {
        commentFacade.saveComment(commentDto);
    }

    @GetMapping("/deleteComment")
    public void deleteCommentExperience(@PathParam("commentId") Long commentId) {
        commentFacade.deleteCommentById(commentId);
    }

    @GetMapping("/getCommentsByExperienceId")
    public List<CommentDto> getCommentsByExperienceId(@PathParam("experienceId") Long experienceId,
                                                      @PathParam("firstIndexOfPage") int firstIndexOfPage,
                                                      @PathParam("offsetSize") int offsetSize) {
        return commentFacade.findCommentsByExperienceId(experienceId,firstIndexOfPage,offsetSize);
    }

    @PostMapping("/updateComment")
    public void updateComment(@RequestBody CommentDto commentDto) {
        commentFacade.updateComment(commentDto);
    }
}
