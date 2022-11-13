package ru.practicum.main.mappers.comment;

import org.mapstruct.*;
import ru.practicum.main.dto.comment.CommentDto;
import ru.practicum.main.dto.comment.RequestCommentDto;
import ru.practicum.main.models.comment.Comment;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toComment(RequestCommentDto requestCommentDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(target = "createdOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDtos(List<Comment> comments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment updateDtoToComment(RequestCommentDto requestCommentDto, @MappingTarget Comment comment);

}
