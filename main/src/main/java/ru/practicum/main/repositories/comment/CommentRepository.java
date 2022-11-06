package ru.practicum.main.repositories.comment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.main.models.comment.Comment;
import ru.practicum.main.models.comment.CommentStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndEventIdAndUserId(Long commentId, Long eventId, Long userId);

    List<Comment> findByEventIdAndUserId(Long eventId, Long userId);

    @Query("select c from Comment c " +
            "where c.event.id = ?1 and c.status <> ?2 ")
    List<Comment> findEventComments(Long eventId, CommentStatus status, Pageable pageable);


}
