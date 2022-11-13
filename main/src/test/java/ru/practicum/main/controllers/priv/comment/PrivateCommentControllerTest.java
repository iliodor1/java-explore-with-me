package ru.practicum.main.controllers.priv.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.dto.comment.RequestCommentDto;
import ru.practicum.main.models.comment.CommentStatus;
import ru.practicum.main.services.comment.CommentService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PrivateCommentController.class)
class PrivateCommentControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService service;

    @Autowired
    private MockMvc mvc;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private CommentDto commentDto;

    @BeforeEach
    private void createCommentDto() {
        commentDto = new CommentDto(
                1L,
                "Some comment",
                CommentStatus.PUBLISHED,
                1L,
                1L,
                "User Name",
                LocalDateTime.now()
                             .format(formatter)
        );
    }

    @Test
    void whenCreate_thenReturnCreatedCommentStatus2xx() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto("Some comment");

        when(service.create(1L, 1L, requestCommentDto)).thenReturn(commentDto);

        mvc.perform(post("/users/1/events/1/comments")
                   .content(mapper.writeValueAsString(commentDto))
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(commentDto.getId()), Long.class))
           .andExpect(jsonPath("$.text", is(commentDto.getText()), String.class))
           .andExpect(jsonPath("$.userName", is(commentDto.getUserName()), String.class))
           .andExpect(jsonPath("$.status", is(commentDto.getStatus()
                                                        .toString())));
    }

    @Test
    void whenUpdate_thenReturnUpdatedCommentStatus2xx() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto("Updated comment");
        commentDto = new CommentDto(
                1L,
                "Updated comment",
                CommentStatus.PUBLISHED,
                1L,
                1L,
                "User Name",
                LocalDateTime.now()
                             .format(formatter)
        );

        when(service.update(1L, 1L, 1L, requestCommentDto)).thenReturn(commentDto);

        mvc.perform(patch("/users/1/events/1/comments/" + commentDto.getId())
                   .content(mapper.writeValueAsString(commentDto))
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(commentDto.getId()), Long.class))
           .andExpect(jsonPath("$.text", is(commentDto.getText()), String.class))
           .andExpect(jsonPath("$.userName", is(commentDto.getUserName()), String.class))
           .andExpect(jsonPath("$.status", is(commentDto.getStatus()
                                                        .toString())));
    }

    @Test
    void whenDelete_thenDeleteCommentStatus2xx() throws Exception {
        mvc.perform(delete("/users/1/events/1/comments/" + commentDto.getId()))
           .andExpect(status().isOk());
    }

    @Test
    void whenGetByEvent_thenReturnCommentStatus2xx() throws Exception {
        when(service.getByEvent(1L, 1L, 1L)).thenReturn(commentDto);

        mvc.perform(get("/users/1/events/1/comments/" + commentDto.getId()))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(commentDto.getId()), Long.class))
           .andExpect(jsonPath("$.text", is(commentDto.getText()), String.class))
           .andExpect(jsonPath("$.userName", is(commentDto.getUserName()), String.class))
           .andExpect(jsonPath("$.status", is(commentDto.getStatus()
                                                        .toString())));
    }

    @Test
    void whenGetAllEvent_thenReturnListOfCommentsStatus2xx() throws Exception {
        List<CommentDto> commentDtos = List.of(commentDto);

        when(service.getUserComments(1L, 1L)).thenReturn(commentDtos);


        mvc.perform(get("/users/1/events/1/comments"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()").value(commentDtos.size()));
    }

}
