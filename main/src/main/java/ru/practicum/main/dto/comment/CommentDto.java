package ru.practicum.main.dto.comment;

import lombok.Data;
import ru.practicum.main.models.comment.Comment;
import ru.practicum.main.models.comment.CommentStatus;

import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity
 *
 * @author Eldar Gainanov
 */
@Data
public class CommentDto implements Serializable {
    /**
     * The comment identifier.
     */
    private final Long id;
    /**
     * The comment text.
     */
    private final String text;
    /**
     * The comment status {@link CommentStatus}.
     */
    private final CommentStatus status;
    /**
     * The event id to which the comment was left
     */
    private final Long eventId;
    /**
     * Comment author id
     */
    private final Long userId;
    /**
     * Name and last of the author of the comment
     */
    private final String userName;
    /**
     * Date and time the comment was created.
     */
    private final String createdOn;
}
