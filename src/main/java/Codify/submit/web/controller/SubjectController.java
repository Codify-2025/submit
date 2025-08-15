package Codify.submit.web.controller;


import Codify.submit.service.SubjectService;
import Codify.submit.web.dto.SubjectRequestDto;
import Codify.submit.web.dto.SubjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/submit/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public SubjectResponseDto create(
            @RequestHeader("USER-UUID") String userUuidHeader, // 임시, 스프링 시큐리티 구현 후 대체 예정
            @RequestBody SubjectRequestDto subjectRequestDto
    ) {
        UUID userUuid = UUID.fromString(userUuidHeader);
        Long id = subjectService.create(userUuid, subjectRequestDto);
        return new SubjectResponseDto(id, "과목이 성공적으로 생성되었습니다.");
    }
}
