package ru.practicum.main.controllers.priv.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.models.comment.CommentStatus;
import ru.practicum.main.services.comment.CommentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminCommentController.class)
class AdminCommentControllerTest {


    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService service;

    @Autowired
    private MockMvc mvc;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final CommentDto commentDto = new CommentDto(
            1L,
            "Some comment",
            CommentStatus.BLOCKED,
            1L,
            1L,
            "User Name",
            LocalDateTime.now().format(formatter));

    @Test
    void whenBlockComment_thenReturnBlockedCommentStatus2xx() throws Exception {
        when(service.blockComment(1L)).thenReturn(commentDto);

        mvc.perform(patch("/admin/comments/" + commentDto.getId() + "/blocked"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(commentDto.getId()), Long.class))
           .andExpect(jsonPath("$.status", is(commentDto.getStatus().toString())));
    }
}
