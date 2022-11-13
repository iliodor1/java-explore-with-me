package ru.practicum.main.controllers.publ;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.services.comment.CommentService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("events/{eventId}/comments")
@Validated
public class PublicCommentController {

    private final CommentService service;

    @GetMapping
    public List<CommentDto> getEventComments(
            @PathVariable @NotNull Long eventId,
            @RequestParam(required = false, defaultValue = "true") Boolean newFirst,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return service.getEventComments(eventId, newFirst, from, size);
    }

}
