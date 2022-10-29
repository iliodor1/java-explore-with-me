package ru.practicum.main.request.mapper;

import org.mapstruct.*;
import ru.practicum.main.request.dto.ParticipationRequestDto;
import ru.practicum.main.request.model.ParticipationRequest;

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
