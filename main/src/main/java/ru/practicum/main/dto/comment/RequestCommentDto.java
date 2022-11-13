package ru.practicum.main.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.practicum.main.models.comment.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity
 *
 * @author Eldar Gainanov
 */
@Data
public class RequestCommentDto implements Serializable {
    /**
     * The comment text.
     */
    @NotBlank
    @Size(max = 7000)
    private final String text;

    @JsonCreator
    public RequestCommentDto(@JsonProperty("text") String text) {
        this.text = text;
    }

}
