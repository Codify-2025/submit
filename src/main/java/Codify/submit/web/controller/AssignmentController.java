package Codify.submit.web.controller;


import Codify.submit.service.AssignmentService;
import Codify.submit.web.dto.AssignmentRequestDto;
import Codify.submit.web.dto.AssignmentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/submit")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping("/subjects/{subjectId}/assignments")
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @RequestHeader("USER-UUID") String userUuidHeader,
            @PathVariable Long subjectId,
            @Valid @RequestBody AssignmentRequestDto requestDto
    ) {
        final UUID userUuid = UUID.fromString(userUuidHeader);
        final Long assignmentId = assignmentService.createAssignment(userUuid, subjectId, requestDto);
        return ResponseEntity.ok(AssignmentResponseDto.of(assignmentId));
    }
}
