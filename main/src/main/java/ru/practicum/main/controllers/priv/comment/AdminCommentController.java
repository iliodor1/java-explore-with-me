package ru.practicum.main.controllers.priv.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.services.comment.CommentService;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/comments")
@Validated
public class AdminCommentController {
    private final CommentService service;


    @PatchMapping("{commentId}/blocked")
    public CommentDto blockComment(@PathVariable @NotNull Long commentId) {
        return service.blockComment(commentId);
    }
}
