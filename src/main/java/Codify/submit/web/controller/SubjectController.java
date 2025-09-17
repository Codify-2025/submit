package Codify.submit.web.controller;

import Codify.submit.service.SubjectService;
import Codify.submit.web.dto.SubjectRequestDto;
import Codify.submit.web.dto.SubjectResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/submit/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    // 새로운 과목 생성하기
    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(
            @RequestHeader("USER-UUID") String userUuidHeader, // 임시, 스프링 시큐리티 구현 후 대체 예정
            @Valid @RequestBody SubjectRequestDto subjectRequestDto
    ){
        final UUID userUuid = UUID.fromString(userUuidHeader);
        SubjectResponseDto subjectResponseDto = subjectService.createSubject(userUuid, subjectRequestDto);
        return ResponseEntity.ok(subjectResponseDto);
    }

    // 기존 과목 조회하기
    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> listSubject(
            @RequestHeader("USER-UUID") String userUuidHeader
    ) {
        final UUID userUuid = UUID.fromString(userUuidHeader);
        List<SubjectResponseDto> subjects = subjectService.listSubjectNameByUser(userUuid);
        return ResponseEntity.ok(subjects);
    }
}
