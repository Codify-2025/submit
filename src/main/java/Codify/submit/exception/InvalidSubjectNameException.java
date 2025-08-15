package Codify.submit.exception;

import Codify.submit.exception.baseException.BaseException;

public class InvalidSubjectNameException extends BaseException {
    public InvalidSubjectNameException() {
        super(ErrorCode.EMPTY_SUBJECT_NAME);
    }
}
