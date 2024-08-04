package com.desafio.view;

import jdk.jshell.spi.ExecutionControl;

public class ClienteException extends RuntimeException {
    public ClienteException (String message) {
        super(message);
    }
}
