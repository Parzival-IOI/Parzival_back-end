package com.parzival_backend.Parzival.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestDto {
    private String id;
    private String title;
    private String language;
    private String description;
    private String link;
    private String image;
}
