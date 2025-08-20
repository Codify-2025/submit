package Codify.submit.repository;

import Codify.submit.domain.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
    boolean existsByUserUuidAndSubjectName(UUID userUuid, String subjectName);
}