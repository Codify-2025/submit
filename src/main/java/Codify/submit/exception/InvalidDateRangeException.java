package Codify.submit.exception;

import Codify.submit.exception.baseException.BaseException;

public class InvalidDateRangeException extends BaseException {
  public InvalidDateRangeException() {
    super("시작일이 종료일보다 늦을 수 없습니다.", ErrorCode.INVALID_INPUT_VALUE);
  }
}