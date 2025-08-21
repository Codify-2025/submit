package Codify.submit.repository;

import Codify.submit.domain.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
    boolean existsByUserUuidAndSubjectName(UUID userUuid, String subjectName);

    // 기존 과목 조회하기: 필요한 데이터만 조회하기 위함
    @Query("select s.subjectName from Subjects s where s.userUuid = :userUuid order by s.subjectName asc")
    List<String> findSubjectNamesByUserUuid(UUID userUuid);
}