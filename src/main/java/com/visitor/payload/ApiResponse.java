package com.visitor.payload;

/**
 * Created by zola on 19/08/17.
 */
public class ApiResponse {
    private String apiVersion = "v1";
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse( String apiVersion,Boolean success, String message, Object data) {
        this.success = success;
        this.setMessage(message);
        this.data = data;
        this.apiVersion = apiVersion;
    }

    public ApiResponse( Boolean success, String message, Object data) {
        this.success = success;
        this.setMessage(message);
        this.data = data;
        this.apiVersion = "v1";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
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
