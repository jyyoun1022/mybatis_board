package org.codej.restAPi.board.exception;

import lombok.extern.log4j.Log4j2;
import org.codej.restAPi.board.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandlers {

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity handleMemberException(CustomException ex){
//        return new ResponseEntity(new Response(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()),
//                HttpStatus.valueOf(ex.getErrorCode().getStatus()));
//    }
    @ExceptionHandler(CustomException.class)
    public Response handlerMemberException(CustomException ex){
        log.info(ex.getStackTrace());
        return Response.failure(ex.getErrorCode().getStatus(),ex.getErrorCode().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handlerValidation(MethodArgumentNotValidException e){
        log.info("e ::: {}",e.getMessage());
        return Response.failure(-1003,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response authenticationEntryPoint(){
        return Response.failure(401,"인증되지 않은 사용자입니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response accessDeniedException(){
        return Response.failure(403,"접근이 거부되었습니다.");
    }

}
