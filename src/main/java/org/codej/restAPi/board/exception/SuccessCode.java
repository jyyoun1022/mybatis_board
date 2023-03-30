package org.codej.restAPi.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessCode {

    SUCCESS_CODE(200,"성공");
    private final int status;
    private final String message;
}
