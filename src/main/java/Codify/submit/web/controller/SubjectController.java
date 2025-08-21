package Codify.submit.web.controller;

import Codify.submit.exception.ErrorCode;
import Codify.submit.exception.UnauthenticatedException;
import Codify.submit.exception.baseException.BaseException;
import Codify.submit.service.SubjectService;
import Codify.submit.web.dto.SubjectRequestDto;
import Codify.submit.web.dto.SubjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/submit/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseDto> create(
            @RequestHeader("USER-UUID") String userUuidHeader, // 임시, 스프링 시큐리티 구현 후 대체 예정
            @RequestBody SubjectRequestDto subjectRequestDto
    ){
        // 유효한 입력인지 판단
        if (userUuidHeader == null || userUuidHeader.isBlank()) {
            throw new UnauthenticatedException();
        }
        final UUID userUuid;
        try {
            userUuid = UUID.fromString(userUuidHeader);
        } catch (IllegalArgumentException ex) {
            throw new BaseException(ErrorCode.INVALID_INPUT_VALUE);
        }

        Long id = subjectService.create(userUuid, subjectRequestDto);
        return ResponseEntity.status(200).body(new SubjectResponseDto(id, "과목이 성공적으로 생성되었습니다."));
    }
}
