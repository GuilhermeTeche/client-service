package br.com.itau.clientapi.exceptions;

import java.util.List;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(List<String> message) {
        super(message.toString());
    }
}
