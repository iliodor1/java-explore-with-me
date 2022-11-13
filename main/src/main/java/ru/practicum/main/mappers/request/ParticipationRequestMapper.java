package ru.practicum.main.mappers.request;

import org.mapstruct.*;
import ru.practicum.main.dto.request.ParticipationRequestDto;
import ru.practicum.main.models.request.ParticipationRequest;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ParticipationRequestMapper {
    @Mapping(source = "event.id", target = "event")
    @Mapping(source = "user.id", target = "requester")
    @Mapping(target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @InheritInverseConfiguration(name = "participationRequestDtoToRequest")
    ParticipationRequestDto toDto(ParticipationRequest participationRequest);

    List<ParticipationRequestDto> toDtos(List<ParticipationRequest> requests);

}
