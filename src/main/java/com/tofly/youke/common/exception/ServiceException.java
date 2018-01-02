package com.tofly.youke.common.exception;

/**
 * @author lyrics
 * @date 2017-12-22
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -4311085087514783162L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
