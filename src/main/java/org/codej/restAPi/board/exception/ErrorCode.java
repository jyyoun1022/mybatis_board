package org.codej.restAPi.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_PARAMETER(400,"파라미터 값을 확인해주세요"),
    NOT_CHANGE_NICKNAME(400,"닉네임이 기존과 동일합니다."),
    DUPLICATE_NICKNAME(409,"이미 존재하는 닉네임입니다."),
    DUPLICATE_EMAIL(409, "이미 존재하는 이메일입니다."),
    NON_UNIQUE(409,"이값은 중복이 불가능합니다."),
    LOGIN_FAILURE(400,"로그인이 실패하였습니다."),
    NOT_FOUND_MEMBER(404, "존재하는 회원이 없습니다."),

    NOT_MATCH_PASSWORD(400,"비밀번호가 맞지 않습니다.");
    private final int status;
    private final String message;
}
