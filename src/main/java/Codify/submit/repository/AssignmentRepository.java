package Codify.submit.repository;

import Codify.submit.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    // 같은 과목에서 같은 과제명이 존재하는지 확인
    boolean existsBySubjectIdAndAssignmentName(Long subjectId, String assignmentName);

    // 특정 사용자의 특정 과목에서 같은 과제명이 존재하는지 확인 (더 안전한 검증)
    boolean existsByUserUuidAndSubjectIdAndAssignmentName(UUID userUuid, Long subjectId, String assignmentName);
}