package Codify.submit.service;

import Codify.submit.domain.Assignment;
import Codify.submit.domain.Subjects;
import Codify.submit.exception.*;
import Codify.submit.repository.AssignmentRepository;
import Codify.submit.repository.SubjectRepository;
import Codify.submit.repository.UserRepository;
import Codify.submit.web.dto.AssignmentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createAssignment(UUID userUuid, Long subjectId, AssignmentRequestDto requestDto) {
        validateUser(userUuid);

        // 과목 소유권 확인
        final Subjects subject = subjectRepository.findById(subjectId)
                .orElseThrow(SubjectNotFoundException::new);
        if (!subject.getUserUuid().equals(userUuid)) {
            throw new UnauthenticatedException();
        }

        // 입력 검증
        final String assignmentName  = normalizeAssignment(requestDto.getAssignmentName());
        // 기간 검증
        final var start = requestDto.getStartDate();
        LocalDateTime end   = requestDto.getEndDate();

        // 자동 설정
        if (end == null) {
            end = start.toLocalDate().plusDays(7).atTime(23, 59, 59);
        }

        if (start.isAfter(end)) {
            throw new InvalidDateRangeException(); // "시작일이 종료일보다 늦을 수 없습니다."
        }
        if (requestDto.getWeek() == null || requestDto.getWeek() <= 0) {
            throw new InvalidAssignmentNameException(); // 별도 에러코드 만들면 더 좋음 (e.g., INVALID_WEEK)
        }

        // 중복 과제명 방지
        if (assignmentRepository.existsByUserUuidAndSubjectIdAndAssignmentName(
                userUuid, subject.getSubjectId(), assignmentName)) {
            throw new AssignmentAlreadyExistsException();
        }

        final Timestamp startTS = Timestamp.valueOf(start);
        final Timestamp endTS   = Timestamp.valueOf(end);

        // 주차/기간 설정
        final Assignment assignment = Assignment.builder()
                .userUuid(userUuid)
                .subjectId(subject.getSubjectId())
                .assignmentName(assignmentName)
                .startDate(startTS)
                .endDate(endTS)
                .week(requestDto.getWeek())
                .build();

        return assignmentRepository.save(assignment).getAssignmentId();
    }

    private void validateUser(UUID userUuid) {
        if (userUuid == null) throw new UnauthenticatedException();
        if (!userRepository.existsById(userUuid)) throw new UserNotFoundException();
    }
    private String normalizeSubject(String s) {
        if (s == null || s.trim().isEmpty()) throw new InvalidSubjectNameException();
        return s.trim();
    }
    private String normalizeAssignment(String s) {
        if (s == null || s.trim().isEmpty()) throw new InvalidAssignmentNameException();
        return s.trim();
    }
}
