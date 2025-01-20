package com.Forumhub.demo.infra.exception;

public class UnauthorizedModificationException extends RuntimeException{

    public UnauthorizedModificationException(String message) {
        super(message);
    }
}
