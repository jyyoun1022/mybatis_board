package org.codej.restAPi.board.service.sign;

import lombok.RequiredArgsConstructor;
import org.codej.restAPi.board.dto.member.Member;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.codej.restAPi.board.security.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberMapper.findByEmail(email).orElseGet(() -> new Member(null, null, null, null, null, null, null, null));
        return new CustomUserDetails(
                member.getEmail(),
                member.getRole().stream().map(memberRole -> memberRole.getRoleType())
                        .map(roleType -> roleType.toString())
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
        );
    }
}
