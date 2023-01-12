package com.votacaoapi.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}