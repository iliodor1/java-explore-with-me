package ru.practicum.main.mappers.compilation;

import org.mapstruct.*;
import ru.practicum.main.dto.compilation.NewCompilationDto;
import ru.practicum.main.dto.compilation.CompilationDto;
import ru.practicum.main.mappers.event.EventMapper;
import ru.practicum.main.models.compilation.Compilation;
import ru.practicum.main.services.event.EventService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventService.class, EventMapper.class})
public interface CompilationMapper {
    @Mapping(source = "pinned", target = "pinned", defaultValue = "false")
    @Mapping(target = "id", ignore = true)
    Compilation toCompilation(NewCompilationDto newCompilationDto);

    CompilationDto toDto(Compilation compilation);

    List<CompilationDto> toDtos(List<Compilation> compilations);
}
