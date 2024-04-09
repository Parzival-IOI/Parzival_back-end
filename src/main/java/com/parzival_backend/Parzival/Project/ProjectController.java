package com.parzival_backend.Parzival.Project;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/")
    public List<ResponseDto> index() {
        try {
            List<ResponseDto> responseDtos = projectService.index();

            return responseDtos;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Server Error",
                    ex
            );
        }
    }

    @GetMapping("/show/{id}")
    public Optional<ProjectModel> show(@PathVariable("id") String id) {
        return projectService.show(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody RequestDto requestDto) {
        this.projectService.create(requestDto);
    }

    @PutMapping("/update")
    public void update(@RequestBody RequestDto requestDto) {
        this.projectService.update(requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        this.projectService.delete(id);
    }
}
