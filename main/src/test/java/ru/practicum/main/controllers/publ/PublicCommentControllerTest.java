package ru.practicum.main.controllers.publ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PublicCommentController.class)
class PublicCommentControllerTest {

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
    void whenGetAllEvent_thenReturnListOfCommentsStatus2xx() throws Exception {
        List<CommentDto> commentDtos = List.of(commentDto);

        when(service.getEventComments(1L, true, 0, 10)).thenReturn(commentDtos);


        mvc.perform(get("/events/1/comments"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()").value(commentDtos.size()));
    }
}
