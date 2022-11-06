package ru.practicum.main.controllers.priv.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.dto.comment.RequestCommentDto;
import ru.practicum.main.services.comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/comments")
@Validated
public class PrivateCommentController {
    private final CommentService service;

    @PostMapping
    public CommentDto create(@PathVariable @NotNull Long userId,
                             @PathVariable @NotNull Long eventId,
                             @RequestBody @Valid RequestCommentDto requestCommentDto) {
        return service.create(userId, eventId, requestCommentDto);
    }

    @PatchMapping("{commentId}")
    public CommentDto update(@PathVariable @NotNull Long userId,
                             @PathVariable @NotNull Long eventId,
                             @PathVariable @NotNull Long commentId,
                             @RequestBody @Valid RequestCommentDto requestCommentDto) {
        return service.update(userId, eventId, commentId, requestCommentDto);
    }

    @DeleteMapping("{commentId}")
    public void delete(@PathVariable @NotNull Long userId,
                       @PathVariable @NotNull Long eventId,
                       @PathVariable @NotNull Long commentId) {
        service.delete(userId, eventId, commentId);
    }

    @GetMapping("{commentId}")
    public CommentDto getByEvent(@PathVariable @NotNull Long userId,
                                 @PathVariable @NotNull Long eventId,
                                 @PathVariable @NotNull Long commentId) {
        return service.getByEvent(userId, eventId, commentId);
    }

    @GetMapping
    public List<CommentDto> getAllByUser(@PathVariable @NotNull Long userId,
                                         @PathVariable @NotNull Long eventId) {
        return service.getUserComments(userId, eventId);
    }

}
