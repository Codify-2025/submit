package Codify.submit.service;

import Codify.submit.domain.Subjects;
import Codify.submit.exception.InvalidSubjectNameException;
import Codify.submit.exception.SubjectAlreadyExistsException;
import Codify.submit.exception.UnauthenticatedException;
import Codify.submit.exception.UserNotFoundException;
import Codify.submit.repository.SubjectRepository;
import Codify.submit.repository.UserRepository;
import Codify.submit.web.dto.SubjectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createSubject(UUID userUuid, SubjectRequestDto subjectRequestDto) {
        String name = normalize(subjectRequestDto.getSubjectName());

        // 유효한 입력인지 판단
        if (userUuid == null) {
            throw new UnauthenticatedException();
        }

        // 1. 로그인은 했지만 DB에 존재하지 않는 경우
        if (!userRepository.existsById(userUuid)) {
            throw new UserNotFoundException();
        }

        // 2. 같은 사용자(교수)가 동일한 과목을 생성하는 경우
        if (subjectRepository.existsByUserUuidAndSubjectName(userUuid, name)) {
            throw new SubjectAlreadyExistsException();
        }

        Subjects saved = subjectRepository.save(new Subjects(userUuid, name));
        return saved.getSubjectId();
    }

    private String normalize(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            throw new InvalidSubjectNameException();
        }
        return raw.trim();
    }

    @Transactional(readOnly = true)
    public List<String> listSubjectNameByUser(UUID userUuid) {
        if (userUuid == null) {
            throw new UnauthenticatedException();
        }

        // 1. 로그인은 했지만 DB에 존재하지 않는 경우
        if (!userRepository.existsById(userUuid)) {
            throw new UserNotFoundException();
        }
        return subjectRepository.findSubjectNamesByUserUuid(userUuid);
    }
}