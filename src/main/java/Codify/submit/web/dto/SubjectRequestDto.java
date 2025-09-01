package Codify.submit.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {
    @NotBlank
    @Size(max = 255)
    private String subjectName;
}