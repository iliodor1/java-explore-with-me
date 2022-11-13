package ru.practicum.main.services.comment;


import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.dto.comment.RequestCommentDto;
import ru.practicum.main.models.comment.CommentStatus;

import java.util.List;

public interface CommentService {
    /**
     * Comment creator adds a new Comment. Available for comment author
     * @param userId  Comment author identifier
     * @param eventId event identifier to which the comment was left
     * @param requestCommentDto {@link RequestCommentDto} - new object with comment text.
     * @return {@link CommentDto} that was successfully added.
     */
    CommentDto create(Long userId, Long eventId, RequestCommentDto requestCommentDto);

    /**
     * Comment creator updates a comment. Available for comment author
     * @param userId Comment author identifier
     * @param eventId event identifier to which the comment was left
     * @param commentId comment identifier to update
     * @param requestCommentDto {@link RequestCommentDto} with new comment
     * @return {@link CommentDto} that was successfully updated.
     */
    CommentDto update(Long userId, Long eventId, Long commentId, RequestCommentDto requestCommentDto);

    /**
     * Comment creator deletes a comment. Available for comment author
     * @param userId Comment author identifier
     * @param eventId event identifier to which the comment was left
     * @param commentId  comment identifier to delete
     */
    void delete(Long userId, Long eventId, Long commentId);

    /**
     * Comment creator gets own comment by id. Available for comment creator
     * @param userId Comment author identifier
     * @param eventId event identifier to which the comment was left
     * @param commentId comment id to get the comment
     * @return {@link CommentDto} that was successfully got.
     */
    CommentDto getByEvent(Long userId, Long eventId, Long commentId);

    /**
     * Comment creator gets own comments by eventId. Available for comment creator
     * @param userId Comment author identifier
     * @param eventId event identifier to which the comments were left
     * @return list of successfully received {@link CommentDto}
     */
    List<CommentDto> getUserComments(Long userId, Long eventId);

    /**
     * This method blocks the user's comment by the administrator. Available for administrator
     * @param commentId Comment identifier.
     * @return {@link CommentDto} blocked comment
     */
    CommentDto blockComment(Long commentId);

    /**
     * Method for displaying comments for the event where status {@link CommentStatus} 'PUBLISHED' or 'EDITED'. Available to all
     * @param eventId  event identifier to which the comments were left
     * @param newFirst TRUE (default value) output new comments firs
     *                    <p> or FALSE output old comments first
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     * @return list of successfully received  {@link CommentDto}
     */
    List<CommentDto> getEventComments(Long eventId, Boolean newFirst, Integer from, Integer size);

}
