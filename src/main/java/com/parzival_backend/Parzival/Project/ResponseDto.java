package com.parzival_backend.Parzival.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String id;
    private String title;
    private String language;
    private String description;
    private String link;
    private String image;
}
