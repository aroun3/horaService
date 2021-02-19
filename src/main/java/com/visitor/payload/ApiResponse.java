package com.visitor.payload;

/**
 * Created by zola on 19/08/17.
 */
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse( Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
