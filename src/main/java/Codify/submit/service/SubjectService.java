package Codify.submit.service;

import Codify.submit.domain.Subjects;
import Codify.submit.exception.InvalidSubjectNameException;
import Codify.submit.exception.SubjectAlreadyExistsException;
import Codify.submit.exception.UserNotFoundException;
import Codify.submit.repository.SubjectRepository;
import Codify.submit.web.dto.SubjectRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public Long create(UUID userUuid, SubjectRequestDto subjectRequestDto) {
        String raw = subjectRequestDto.getSubjectName();
        if (raw == null || raw.trim().isEmpty()) {
            throw new InvalidSubjectNameException();
        }
        String name = raw.trim();

        try {
            Subjects saved = subjectRepository.save(new Subjects(userUuid, name));
            return saved.getSubjectId();
        } catch (DataIntegrityViolationException e) {
            // custom exception
            // 제약이름으로만 잡히지 않는 에러가 있어서 MySQL 에러코드로도 처리 (1062=중복, 1452=FK 위반)
            Throwable root = NestedExceptionUtils.getMostSpecificCause(e);
            if (root instanceof java.sql.SQLIntegrityConstraintViolationException sqlx) {
                int code = sqlx.getErrorCode();
                // 중복된 과목은 스프링 시큐리티 구현 후 추가 예정
                // if (code == 1062) throw new SubjectAlreadyExistsException();
                if (code == 1452) throw new UserNotFoundException();
            }
            // 그 외: 전역 핸들러
            throw e;
        }
    }
}