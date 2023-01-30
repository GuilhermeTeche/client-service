package br.com.itau.clientapi.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionBadRequestException extends RuntimeException {

    public TransactionBadRequestException(String s) {
        super(s);
    }
}
