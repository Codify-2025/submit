package Codify.submit.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentRequestDto {

    @NotBlank
    @Size(max = 255)
    private String assignmentName;

    @NotNull
    private LocalDateTime startDate;

    // @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Positive
    private Long week;
}
