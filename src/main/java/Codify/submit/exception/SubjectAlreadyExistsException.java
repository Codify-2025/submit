package Codify.submit.exception;

import Codify.submit.exception.baseException.BaseException;

public class SubjectAlreadyExistsException extends BaseException {
    public SubjectAlreadyExistsException() {
        super(ErrorCode.SUBJECT_ALREADY_EXISTS);
    }
}