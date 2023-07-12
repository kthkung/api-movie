package org.sso.base.exception;

public class BaseException extends Exception {
    private int code = 0;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
