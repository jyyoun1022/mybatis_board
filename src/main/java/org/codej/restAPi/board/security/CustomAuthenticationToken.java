package org.codej.restAPi.board.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

@Getter
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private String type;
    private CustomUserDetails principal;

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String type, CustomUserDetails principal) {
        super(authorities);
        this.type = type;
        this.principal = principal;
        setAuthenticated(true);
    }



    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }


}
