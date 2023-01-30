package br.com.itau.clientapi.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionNotFoundException extends RuntimeException {
}
