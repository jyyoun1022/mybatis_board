//package org.codej.restAPi.board.security;
//
//import lombok.extern.log4j.Log4j2;
//import org.codej.restAPi.board.entity.RoleType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//@Log4j2
//public class AuthHelper {
//
//    public boolean isAuthenticated(){
//        return getAuthentication() instanceof
//                CustomAuthenticationToken && getAuthentication().isAuthenticated();
//    }
//    public Long extractMemberId(){
//        return Long.valueOf(getUserDetails().getMemberId());
//    }
//    public Set<RoleType> extractMemberRole(){
//        return getUserDetails().getAuthorities()
//                .stream()
//                .map(authority -> authority.getAuthority())
//                .map(strAuth -> RoleType.valueOfLabel(strAuth))
//                .collect(Collectors.toSet());
//    }
//    public boolean isAccessTokenType(){
//        return "access".equals(((CustomAuthenticationToken)getAuthentication()).getType());
//    }
//    public boolean isRefreshTokenType() {
//        return "refresh".equals(((CustomAuthenticationToken) getAuthentication()).getType());
//    }
//    private CustomUserDetails getUserDetails() {
//        return (CustomUserDetails) getAuthentication().getPrincipal();
//    }
//
//    private Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//}
