package com.parzival_backend.Parzival.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Projects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectModel {
    @Transient
    public static final String SEQUENCE_NAME = "projects_sequence";
    @Id
    private String id;
    private String title;
    private String language;
    private String description;
    private String link;
    private String image;

    public ProjectModel(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.language = requestDto.getLanguage();
        this.description = requestDto.getDescription();
        this.link = requestDto.getLink();
        this.image = requestDto.getImage();
    }
}
