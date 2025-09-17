package Codify.submit.repository;

import Codify.submit.domain.Subjects;
import Codify.submit.web.dto.SubjectResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
    boolean existsByUserUuidAndSubjectName(UUID userUuid, String subjectName);

    // 기존 과목 조회하기: 필요한 데이터만 조회하기 위함
    @Query("SELECT new Codify.submit.web.dto.SubjectResponseDto(s.subjectId, s.subjectName) " +
            "FROM Subjects s WHERE s.userUuid = :userUuid ORDER BY s.subjectId")
    List<SubjectResponseDto> findSubjectsByUserUuid(@Param("userUuid") UUID userUuid);

    Optional<Subjects> findBySubjectIdAndUserUuid(Long subjectId, UUID userUuid);

}