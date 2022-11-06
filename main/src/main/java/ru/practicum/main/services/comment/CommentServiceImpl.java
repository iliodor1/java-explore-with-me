package ru.practicum.main.services.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.dto.comment.RequestCommentDto;
import ru.practicum.main.exceptions.ConflictRequestException;
import ru.practicum.main.exceptions.NotFoundException;
import ru.practicum.main.mappers.comment.CommentMapper;
import ru.practicum.main.models.comment.Comment;
import ru.practicum.main.models.comment.CommentStatus;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.State;
import ru.practicum.main.models.user.User;
import ru.practicum.main.repositories.comment.CommentRepository;
import ru.practicum.main.services.event.EventService;
import ru.practicum.main.services.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public CommentDto create(Long userId, Long eventId, RequestCommentDto requestCommentDto) {
        Comment comment = mapper.toComment(requestCommentDto);

        Event event = eventService.getEvent(eventId);

        if (!event.getState()
                  .equals(State.PUBLISHED)) {
            log.error("Event id={} has no 'PUBLISHED' state.", event.getId());
            throw new ConflictRequestException("Comment can leave only under the published event.");
        }

        comment.setEvent(event);
        User user = userService.getUser(userId);
        comment.setUser(user);

        comment.setCreatedOn(LocalDateTime.now());
        comment.setStatus(CommentStatus.PUBLISHED);

        return mapper.toDto(repository.save(comment));
    }

    @Override
    public CommentDto update(Long userId, Long eventId, Long commentId ,RequestCommentDto commentDto) {
        Comment comment = findCommentById(commentId);

        User user = userService.getUser(userId);
        checkUserOwnerComment(comment, user);
        Event event = eventService.getEvent(eventId);
        checkEventContainsComment(comment, event);

        if (comment.getStatus()
                   .equals(CommentStatus.BLOCKED)) {
            log.error("User cannot edit blocked comment id={}", comment.getId());
            throw new ConflictRequestException(String.format(
                    "User cannot edit blocked comment id=%s", comment.getId()
            ));
        }
        comment.setStatus(CommentStatus.EDITED);

        Comment updatedComment
                = repository.save(mapper.updateDtoToComment(commentDto, comment));

        return mapper.toDto(updatedComment);
    }

    @Override
    public void delete(Long userId, Long eventId, Long commentId) {
        Comment comment = findCommentById(commentId);

        User user = userService.getUser(userId);
        checkUserOwnerComment(comment, user);
        Event event = eventService.getEvent(eventId);
        checkEventContainsComment(comment, event);

        repository.delete(comment);
    }

    @Override
    public CommentDto getByEvent(Long userId, Long eventId, Long commentId) {
        Comment comment
                = repository.findByIdAndEventIdAndUserId(commentId, eventId, userId)
                            .orElseThrow(() -> {
                                log.error(
                                        "Comment id={} not found, where userId={} and eventId={}",
                                        commentId,
                                        userId,
                                        eventId
                                );
                                throw new NotFoundException(
                                        String.format("Comment id=%s not found.", commentId));
                            });

        return mapper.toDto(comment);
    }

    @Override
    public List<CommentDto> getUserComments(Long userId, Long eventId) {
        List<Comment> comments = repository.findByEventIdAndUserId(eventId, userId);

        return mapper.toDtos(comments);
    }

    @Override
    public CommentDto blockComment(Long commentId) {
        Comment comment = findCommentById(commentId);
        comment.setStatus(CommentStatus.BLOCKED);

        return mapper.toDto(repository.save(comment));
    }

    @Override
    public List<CommentDto> getEventComments(Long eventId, Boolean newFirst, Integer from, Integer size) {
        Sort sortByDateTime = newFirst
                ? Sort.by(Sort.Direction.DESC, "createdOn")
                : Sort.by(Sort.Direction.ASC, "createdOn");

        Pageable pageable = PageRequest.of(from / size, size, sortByDateTime);
        eventService.getEvent(eventId);

        return mapper.toDtos(repository.findEventComments(eventId, CommentStatus.BLOCKED, pageable));
    }

    private Comment findCommentById(Long commentId) {
        return repository.findById(commentId)
                         .orElseThrow(() -> {
                             log.error("Comment id={} doesn't exist.", commentId);
                             throw new NotFoundException(
                                     String.format("Comment id=%s doesn't exist.", commentId));
                         });
    }

    private void checkUserOwnerComment(Comment comment, User user) {
        if (!comment.getUser()
                    .equals(user)) {
            log.error("User id={} can only edit own comment", user.getId());
            throw new ConflictRequestException(
                    String.format("User id=%s can only edit own comment", user.getId())
            );
        }
    }

    private void checkEventContainsComment(Comment comment, Event event) {
        if (!comment.getEvent()
                    .equals(event)) {
            log.error(
                    "Event id={} does not contain the comment id={}",
                    event.getId(),
                    comment.getId()
            );

            throw new ConflictRequestException(
                    String.format(
                            "Event id=%s does not contain the comment id=%s",
                            event.getId(),
                            comment.getId()
                    )
            );
        }
    }

}
