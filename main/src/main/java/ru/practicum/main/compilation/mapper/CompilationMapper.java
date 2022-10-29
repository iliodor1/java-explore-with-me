package ru.practicum.main.compilation.mapper;

import org.mapstruct.*;
import ru.practicum.main.compilation.dto.NewCompilationDto;
import ru.practicum.main.compilation.dto.CompilationDto;
import ru.practicum.main.compilation.model.Compilation;
import ru.practicum.main.event.mapper.EventMapper;
import ru.practicum.main.event.service.EventService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventService.class, EventMapper.class})
public interface CompilationMapper {
    @Mapping(source = "pinned", target = "pinned", defaultValue = "false")
    @Mapping(target = "id", ignore = true)
    Compilation toCompilation(NewCompilationDto newCompilationDto);

    CompilationDto toDto(Compilation compilation);

    List<CompilationDto> toDtos(List<Compilation> compilations);
}
