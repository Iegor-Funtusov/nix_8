package ua.com.alevel.exception;

public abstract class RestException extends RuntimeException {

    public RestException(String msg) {
        super(msg);
    }
}
