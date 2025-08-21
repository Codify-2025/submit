package Codify.submit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiSuccessResponse<T> {
    private final int status;      // 예: 200
    private final boolean success; // 항상 true
    private final String message;  // 메시지
    private final T result;        // 실제 데이터

    public static <T> ApiSuccessResponse<T> of(HttpStatus status, String message, T result) {
        return new ApiSuccessResponse<>(status.value(), true, message, result);
    }
    public static <T> ApiSuccessResponse<T> ok(String message,T result) {
        return of(HttpStatus.OK, message, result);
    }
    public static <T> ApiSuccessResponse<T> ok(T result) {
        return ok("OK", result); // 기본값
    }
}
