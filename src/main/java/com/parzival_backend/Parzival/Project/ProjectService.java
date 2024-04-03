package com.parzival_backend.Parzival.Project;

import java.util.ArrayList;
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

    public List<ResponseDto> index() throws Exception {
        List<ProjectModel> projectModels = this.projectRepository.findAll();
        List<ResponseDto> responseDtos = new ArrayList<>();
        for(ProjectModel projectModel : projectModels) {
            responseDtos.add(new ResponseDto(
                    projectModel.getId(),
                    projectModel.getTitle(),
                    projectModel.getLanguage(),
                    projectModel.getDescription(),
                    projectModel.getLink(),
                    projectModel.getImage()
            ));
        }
        return responseDtos;
    }

    public Optional<ProjectModel> show(String id) {
        return this.projectRepository.findById(id);
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
