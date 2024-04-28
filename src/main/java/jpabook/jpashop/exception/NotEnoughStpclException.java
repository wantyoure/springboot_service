package jpabook.jpashop.exception;

public class NotEnoughStpclException extends RuntimeException {

    public NotEnoughStpclException() {
        super();
    }

    public NotEnoughStpclException(String message) {
        super(message);
    }

    public NotEnoughStpclException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStpclException(Throwable cause) {
        super(cause);
    }

}
