package Codify.submit.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponseDto {
    private Long assignmentId;
    private String message;

    public static AssignmentResponseDto of(Long assignmentId) {
        return AssignmentResponseDto.builder()
                .assignmentId(assignmentId)
                .message("과제가 성공적으로 생성되었습니다.")
                .build();
    }
}
