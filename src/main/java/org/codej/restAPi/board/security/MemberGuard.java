package org.codej.restAPi.board.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codej.restAPi.board.entity.RoleType;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Log4j2
public class MemberGuard {

    private final AuthHelper authHelper;

    public boolean check(Long id){
        return authHelper.isAuthenticated() && authHelper.isAccessTokenType() && hasAuthority(id);
    }
    private boolean hasAuthority(Long id) {
        Long memberId = authHelper.extractMemberId();
        Set<RoleType> memberRoles = authHelper.extractMemberRole();
        return id.equals(memberId) || memberRoles.contains(RoleType.ADMIN);
    }
}
