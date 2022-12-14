package ru.practicum.main.controllers.publ;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.compilation.CompilationDto;
import ru.practicum.main.mappers.compilation.CompilationMapper;
import ru.practicum.main.services.compilation.CompilationService;

import java.util.List;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class PublicCompilationController {
    private final CompilationService service;
    private final CompilationMapper mapper;

    @GetMapping("{compId}")
    public CompilationDto get(@PathVariable(name = "compId") Long id) {
        return mapper.toDto(service.get(id));
    }

    @GetMapping
    public List<CompilationDto> getCompilations(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return mapper.toDtos(service.getCompilations(pinned, from, size));
    }

}
