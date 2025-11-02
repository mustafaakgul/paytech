package org.fintech.paytech.dto;

public class GenericResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public GenericResponse() {
    }

    public GenericResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> GenericResponse<T> empty() {
        return success(null);
    }

    public static <T> GenericResponse<T> success(T data) {
        return new builder<T>()
                .success(true)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> success(T data, String message) {
        return new builder<T>()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> error(T data) {
        return new builder<T>()
                .success(false)
                .message("Error")
                .data(data)
                .build();
    }

    public static class builder<T> {
        private boolean success;
        private String message;
        private T data;

        public builder() {
        }

        public builder(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public GenericResponse<T> build() {
            return new GenericResponse<T>(success, message, data);
        }
    }
}
