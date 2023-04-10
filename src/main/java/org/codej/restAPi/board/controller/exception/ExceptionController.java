package org.codej.restAPi.board.controller.exception;

import org.codej.restAPi.board.exception.AccessDeniedException;
import org.codej.restAPi.board.exception.AuthenticationEntryPointException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/exception/entry-point")
    public void entryPoint(){
        throw new AuthenticationEntryPointException();
    }
    @GetMapping("/exception/access-denied")
    public void accessDenied() {
        throw new AccessDeniedException();
    }
}
