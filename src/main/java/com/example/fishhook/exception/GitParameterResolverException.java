package com.example.fishhook.exception;

/**
 * 解析异常
 * @author jojojo
 */
public class GitParameterResolverException extends RuntimeException {
    public GitParameterResolverException() {
    }

    public GitParameterResolverException(String message) {
        super(message);
    }

    public GitParameterResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitParameterResolverException(Throwable cause) {
        super(cause);
    }

    public GitParameterResolverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
