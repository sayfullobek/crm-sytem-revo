package it.revo.revoservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiResponse<T> implements Serializable {
    private T body;
    private Integer status;
    private Integer total;
    private boolean success;
    private ApiErrorRespone error;

    public ApiResponse() {
    }

    public ApiResponse(Integer statusCode) {
        this.status = statusCode;
        this.success = true;
    }

    public ApiResponse(T body) {
        this(body, 200, null);
    }

    public ApiResponse(T body, Integer status) {
        this(body, status, null);
    }


    public ApiResponse(T body, @NonNull final HttpStatus status, Integer total) {
        this(body, status.value(), total);
    }

    public ApiResponse(T body, @NonNull final Integer status, Integer total) {
        this.body = body;
        this.success = true;
        this.status = status;
        this.total = total;
    }


    public ApiResponse(ApiErrorRespone error, @NonNull final HttpStatus status) {
        this(error, status.value());
    }

    public ApiResponse(ApiErrorRespone error, @NonNull final Integer status) {
        this.error = error;
        this.success = false;
        this.status = status;
    }


}
