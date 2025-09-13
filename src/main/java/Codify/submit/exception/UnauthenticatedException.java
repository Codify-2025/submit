package Codify.submit.exception;

import Codify.submit.exception.baseException.BaseException;

public class UnauthenticatedException extends BaseException {
    public UnauthenticatedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
