package Codify.submit.repository;

import Codify.submit.domain.Subjects;
import org.springframework.data.jpa.repository.*;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {

}