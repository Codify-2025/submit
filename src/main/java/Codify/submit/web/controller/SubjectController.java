package Codify.submit.web.controller;

import Codify.submit.exception.ApiSuccessResponse;
import Codify.submit.exception.ErrorCode;
import Codify.submit.exception.UnauthenticatedException;
import Codify.submit.exception.baseException.BaseException;
import Codify.submit.service.SubjectService;
import Codify.submit.web.dto.SubjectRequestDto;
import Codify.submit.web.dto.SubjectResponseDto;
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
    public ResponseEntity<ApiSuccessResponse<SubjectResponseDto>> createSubject(
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

        Long id = subjectService.createSubject(userUuid, subjectRequestDto);
        SubjectResponseDto apiPayload = new SubjectResponseDto(id);
        return ResponseEntity.ok(ApiSuccessResponse.ok("과목 생성 성공",apiPayload));
    }

    // 기존 과목 조회하기
    @GetMapping
    public ResponseEntity<ApiSuccessResponse<List<String>>> listSubject(
            @RequestHeader("USER-UUID") String userUuidHeader
    ) {
        if (userUuidHeader == null || userUuidHeader.isBlank()) {
            throw new UnauthenticatedException();
        }
        final UUID userUuid;
        try {
            userUuid = UUID.fromString(userUuidHeader);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new BaseException(ErrorCode.INVALID_INPUT_VALUE);
        }

        List<String> subjects = subjectService.listSubjectNameByUser(userUuid);
        return ResponseEntity.ok(ApiSuccessResponse.ok("과목 목록 조회 성공", subjects));
    }
}
