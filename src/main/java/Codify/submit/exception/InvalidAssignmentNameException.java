package Codify.submit.exception;

import Codify.submit.exception.baseException.BaseException;

public class InvalidAssignmentNameException extends BaseException {
    public InvalidAssignmentNameException() { super("과제명이 비어있습니다.", ErrorCode.INVALID_INPUT_VALUE); }
}