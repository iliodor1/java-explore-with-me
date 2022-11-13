package ru.practicum.main.services.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.mappers.comment.CommentMapper;
import ru.practicum.main.models.category.Category;
import ru.practicum.main.models.comment.Comment;
import ru.practicum.main.models.comment.CommentStatus;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.State;
import ru.practicum.main.models.user.User;
import ru.practicum.main.repositories.comment.CommentRepository;
import ru.practicum.main.services.event.EventService;
import ru.practicum.main.services.user.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceUnitTest {
    @Mock
    CommentRepository repository;
    @Mock
    EventService eventService;
    @Mock
    UserService userService;

    @Mock
    CommentMapper commentMapper;

    @InjectMocks
    CommentServiceImpl service;

    Comment comment;

    @BeforeEach
    private void createComment() {
        comment = createComment(1L);
    }

    @Test
    void whenCreate_thenCallCommentRepository() {
        Comment newComment = new Comment();
        newComment.setText("text");
        CommentDto commentDto = createCommentDto(comment);

        when(commentMapper.toComment(any())).thenReturn(newComment);
        when(eventService.getEvent(anyLong())).thenReturn(comment.getEvent());
        when(repository.save(any())).thenReturn(comment);
        when(commentMapper.toDto(any())).thenReturn(commentDto);

        CommentDto savedCommentDto =
                service.create(comment.getUser()
                                      .getId(), comment.getEvent()
                                                       .getId(), any());

        verify(repository, times(1)).save(any());

        assertEquals(commentDto.getId(), savedCommentDto.getId());
        assertEquals(commentDto.getUserName(), savedCommentDto.getUserName());
        assertEquals(commentDto.getStatus(), savedCommentDto.getStatus());

    }

    @Test
    void whenUpdate_thenCallCommentRepository() {
        Comment newComment = comment;
        newComment.setText("updated text");

        when(repository.findById(anyLong())).thenReturn(Optional.of(comment));

        when(userService.getUser(anyLong())).thenReturn(comment.getUser());
        when(eventService.getEvent(anyLong())).thenReturn(comment.getEvent());
        when(commentMapper.updateDtoToComment(any(), any())).thenReturn(newComment);
        when(repository.save(any())).thenReturn(newComment);

        service.update(
                comment.getUser()
                       .getId(),
                comment.getEvent()
                       .getId(),
                comment.getId(),
                any()
        );

        verify(repository, times(1)).save(any());
    }

    @Test
    void whenDelete_thenCallCommentRepository() {
        when(userService.getUser(anyLong())).thenReturn(comment.getUser());
        when(eventService.getEvent(anyLong())).thenReturn(comment.getEvent());
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        service.delete(comment.getId(), comment.getEvent()
                                               .getId(), comment.getUser()
                                                                .getId());

        verify(repository, times(1)).delete(any());
    }

    @Test
    void whenGetByEvent_thenCallCommentRepository() {
        when(repository.findByIdAndEventIdAndUserId(anyLong(), anyLong(), anyLong()))
                .thenReturn(Optional.ofNullable(comment));

        service.getByEvent(comment.getId(), comment.getEvent()
                                                   .getId(), comment.getUser()
                                                                    .getId());

        verify(repository, times(1)).findByIdAndEventIdAndUserId(
                comment.getId(), comment.getEvent()
                                        .getId(), comment.getUser()
                                                         .getId()
        );
    }

    @Test
    void whenGetUserComments_thenCallCommentRepository() {
        when(repository.findByEventIdAndUserId(anyLong(), anyLong())).thenReturn(List.of(comment));
        when(commentMapper.toDtos(any())).thenReturn(List.of(createCommentDto(comment)));

        List<CommentDto> commentDtos
                = service.getUserComments(comment.getUser()
                                                 .getId(), comment.getEvent()
                                                                  .getId());

        assertEquals(1, commentDtos.size());
    }

    @Test
    void whenBlockComment_thenCallCommentRepository() {
        comment.setStatus(CommentStatus.BLOCKED);
        CommentDto commentDto = createCommentDto(comment);

        when(repository.save(any())).thenReturn(comment);
        when(commentMapper.toDto(any())).thenReturn(commentDto);
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        CommentDto blockedCommentDto = service.blockComment(comment.getId());

        verify(repository, times(1)).save(comment);

        assertEquals(CommentStatus.BLOCKED, blockedCommentDto.getStatus());
    }

    private Event createEvent(Long id, Category category, User initiator) {
        Event event = new Event();

        event.setId(id);
        event.setAnnotation("annotation" + id);
        event.setCategory(category);
        event.setCreatedOn(LocalDateTime.now());
        event.setDescription("description" + id);
        event.setEventDate(LocalDateTime.now()
                                        .plusDays(20));
        event.setInitiator(initiator);
        event.setLat(0f);
        event.setLon(0f);
        event.setPaid(false);
        event.setParticipantLimit(0);
        event.setPublishedOn(LocalDateTime.now());
        event.setState(State.PUBLISHED);
        event.setTitle("title" + id);

        return event;
    }

    private Category createCategory(Long id) {
        Category category = new Category();

        category.setId(id);
        category.setName("name" + id);

        return category;
    }

    private User createUser(Long id) {
        User user = new User();

        user.setId(id);
        user.setName("name" + id);
        user.setEmail(id + "emaiL@email.com");

        return user;
    }

    private Comment createComment(Long id) {
        Category category = createCategory(1L);
        User user = createUser(1L);
        Event event = createEvent(1L, category, user);

        Comment createdComment = new Comment();

        createdComment.setId(id);
        createdComment.setText("text" + id);
        createdComment.setCreatedOn(LocalDateTime.now());
        createdComment.setEvent(event);
        createdComment.setUser(user);
        createdComment.setStatus(CommentStatus.PUBLISHED);

        return createdComment;
    }

    private CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getStatus(),
                comment.getEvent()
                       .getId(),
                comment.getUser()
                       .getId(),
                comment.getUser()
                       .getName(),
                comment.getCreatedOn()
                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

}
