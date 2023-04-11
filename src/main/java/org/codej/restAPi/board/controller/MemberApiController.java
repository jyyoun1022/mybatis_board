package org.codej.restAPi.board.controller;

import lombok.RequiredArgsConstructor;
import org.codej.restAPi.board.dto.member.MemberUpdateDto;
import org.codej.restAPi.board.service.MemberService;
import org.codej.restAPi.board.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


//    @PostMapping
//    public ResponseEntity<Response> joinMember(@RequestBody MemberJoinDto dto) throws CustomException {
//        memberService.joinMember(dto);
//        return new ResponseEntity<>(Response.builder().data(SUCCESS_CODE).message(SUCCESS_CODE.getMessage()).build()
//                ,HttpStatus.OK);
//    }
//    @PatchMapping
//    public ResponseEntity<Response> updateMember(@RequestBody MemberUpdateDto dto){
//        memberService.updateNickname(dto);
//        return new ResponseEntity<>(Response.builder().data(SUCCESS_CODE).message(SUCCESS_CODE.getMessage()).build(),
//                HttpStatus.OK);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Response> withdrawMember(@PathVariable int id){
//        memberService.withdrawMember(id);
//        return new ResponseEntity<>(Response.builder().data(SUCCESS_CODE).message(SUCCESS_CODE.getMessage()).build(),
//                HttpStatus.OK);
//    }
    @GetMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@PathVariable("id") Long id) {
        return Response.success(memberService.read(id));
    }

    @DeleteMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response withdraw(@PathVariable("id") Long id) {
        //TODO: 손봐야함
        memberService.withdraw(id);
        return Response.success();
    }

    @PatchMapping("/api/members")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response updateNickname(@RequestBody MemberUpdateDto dto) {
        memberService.updateNickname(dto);
        return Response.success();
    }
}
