package com.parzival_backend.Parzival.Project;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "com.parzival_backend.Parzival.Project")
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ResponseDto> index() {
        List<ProjectModel> projectModels = this.projectRepository.findAll();
        return projectModels.stream().map(this::responseDtoIndex).toList();
    }

    private ResponseDto responseDtoIndex(ProjectModel projectModel) {
        return ResponseDto.builder()
                .id(projectModel.getId())
                .title(projectModel.getTitle())
                .description(projectModel.getDescription())
                .image(projectModel.getImage())
                .link(projectModel.getLink())
                .build();
    }

    public Optional<ResponseDto> show(String id) {
        Optional<ProjectModel> projectModel = this.projectRepository.findById(id);
        return projectModel.map(model -> ResponseDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .image(model.getImage())
                .link(model.getLink())
                .build());
    }

    public void create(RequestDto requestDto) {
        ProjectModel projectModel = new ProjectModel(requestDto);
        this.projectRepository.save(projectModel);
    }

    public void update(RequestDto requestDto) {
        ProjectModel projectModel = new ProjectModel(requestDto);
        this.projectRepository.save(projectModel);
    }

    public void delete(String id) {
        this.projectRepository.deleteById(id);
    }
}
