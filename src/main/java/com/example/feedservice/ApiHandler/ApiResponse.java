package com.example.feedservice.ApiHandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ApiResponse<T> {
    private String errorCode;
    private T resultData;
    private String errorDesc;
    private boolean result;

    public ApiResponse(T resultData) {
        this.resultData = resultData;
        this.result = true;
    }

    public ApiResponse(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}
