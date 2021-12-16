package ua.com.alevel.exception;

public class UnprocessableEntityException extends RestException {

    public UnprocessableEntityException(String msg) {
        super(msg);
    }
}
