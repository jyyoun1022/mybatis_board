package org.codej.restAPi.board.service.sign;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codej.restAPi.board.dto.sign.SignInRequest;
import org.codej.restAPi.board.dto.sign.SignInResponse;
import org.codej.restAPi.board.dto.sign.SignUpRequest;
import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Objects;

import static org.codej.restAPi.board.exception.ErrorCode.*;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public int signUp(SignUpRequest req) {
        validateSignUpInfo(req);
        int age = ageCalculate(req.getBirth());
        req.setAge(age);
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        log.info("ENCODED PASSWORD ::: {}",encodedPassword);
        req.setPassword(encodedPassword);
        memberMapper.save(req);
        return req.getReturnValue();
    }

    public SignInResponse signIn(SignInRequest req) {
        validateSignInInfo(req);
//        String password = memberMapper.getPassword(req.getEmail());
        validatePassword(req);
        String subject = createSubject(req.getEmail());
        String accessToken = tokenService.createAccessToken(subject);
        String refreshToken = tokenService.createRefreshToken(subject);
        return new SignInResponse(accessToken,refreshToken);
    }

    private void validateSignInInfo(SignInRequest req) {
        if (Objects.isNull(req.getEmail()) || Objects.isNull(req.getPassword())){
            throw new CustomException(INVALID_PARAMETER);
        }
    }
    private void validateSignUpInfo(SignUpRequest req) {
        if (Objects.isNull(req.getBirth()) || Objects.isNull(req.getNickname())
                || Objects.isNull(req.getPassword()) || Objects.isNull(req.getEmail())
                || Objects.isNull(req.getName())) {
            throw new CustomException(INVALID_PARAMETER);
        }
        if (memberMapper.existsByNickname(req.getNickname())){
            throw new CustomException(DUPLICATE_NICKNAME);
        }
        if (memberMapper.existsByEmail(req.getEmail())){
            throw new CustomException(DUPLICATE_EMAIL);
        }
    }
    private void validatePassword(SignInRequest req){
        if(!passwordEncoder.matches(req.getPassword(), memberMapper.getPassword(req.getEmail()))) {
            throw new CustomException(NOT_MATCH_PASSWORD);
        }
    }
    private String createSubject(String email){
        int memberId = memberMapper.getMemberId(email);
        return String.valueOf(memberId);
    }
    private int ageCalculate(String birth){
        Calendar current = Calendar.getInstance();
        int currentYear = current.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH) + 1;
        int currentDay = current.get(Calendar.DAY_OF_MONTH);


        int birthYear = Integer.parseInt(birth.split("-")[0]);
        int birthMonth = Integer.parseInt(birth.split("-")[1]);
        int birthDay = Integer.parseInt(birth.split("-")[2]);

        int age = currentYear - birthYear;
        if (birthMonth*100+birthDay > currentMonth*100 +currentDay)
            age--;
        return age;
    }


}
