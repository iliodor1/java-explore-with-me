package ru.practicum.main.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilation.dto.CompilationDto;
import ru.practicum.main.compilation.dto.NewCompilationDto;
import ru.practicum.main.compilation.mapper.CompilationMapper;
import ru.practicum.main.compilation.model.Compilation;
import ru.practicum.main.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
public class AdminCompilationController {
    private final CompilationService service;
    private final CompilationMapper mapper;

    @PostMapping
    public CompilationDto create(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        Compilation compilation = mapper.toCompilation(newCompilationDto);

        return mapper.toDto(service.create(compilation));
    }

    @PatchMapping("{compId}/events/{eventId}")
    public void addEvent(@PathVariable Long compId, @PathVariable Long eventId) {
        service.addEvent(compId, eventId);
    }

    @PatchMapping("{compId}/pin")
    public void pin(@PathVariable Long compId) {
        service.pin(compId);
    }

    @DeleteMapping("{compId}")
    public void delete(@PathVariable(name = "compId") Long id) {
        service.delete(id);
    }

    @DeleteMapping("{compId}/events/{eventId}")
    public void deleteEvent(@PathVariable Long compId, @PathVariable Long eventId) {
        service.deleteEvent(compId, eventId);
    }

    @DeleteMapping("{compId}/pin")
    public void deletePinned(@PathVariable Long compId) {
        service.deletePinned(compId);
    }

}
