package org.codej.restAPi.board.controller;

import lombok.RequiredArgsConstructor;
import org.codej.restAPi.board.dto.sign.SignInRequest;
import org.codej.restAPi.board.dto.sign.SignUpRequest;
import org.codej.restAPi.board.service.sign.SignService;
import org.codej.restAPi.board.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.codej.restAPi.board.util.Response.success;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/api/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest req){
        signService.signUp(req);
        return success();
    }

    @PostMapping("/api/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody SignInRequest req) {
        return success(signService.signIn(req));
    }
}
