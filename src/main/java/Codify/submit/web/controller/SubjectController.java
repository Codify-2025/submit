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
        Long id = subjectService.createSubject(userUuid, subjectRequestDto);
        return ResponseEntity.ok(new SubjectResponseDto(id));
    }

    // 기존 과목 조회하기
    @GetMapping
    public ResponseEntity<List<String>> listSubject(
            @RequestHeader("USER-UUID") String userUuidHeader
    ) {
        final UUID userUuid = UUID.fromString(userUuidHeader);
        List<String> subjects = subjectService.listSubjectNameByUser(userUuid);
        return ResponseEntity.ok(subjects);
    }
}
