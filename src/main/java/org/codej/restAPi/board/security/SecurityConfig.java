package org.codej.restAPi.board.security;

import lombok.RequiredArgsConstructor;
import org.codej.restAPi.board.exception.CustomAccessDeniedHandler;
import org.codej.restAPi.board.exception.CustomAuthenticationEntryPoint;
import org.codej.restAPi.board.service.sign.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final CustomUserDetailService userDetailsService;
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST,"/api/sign-in","/api/sign-up").permitAll()
                        .antMatchers(HttpMethod.GET,"?api/**").permitAll()
                        .antMatchers(HttpMethod.DELETE,"/api/members/**/**").access("memberGuard.check(#id)")// @<빈이름>.<메서드명>(<인자,#id로하면 {id}로 들어감)
                        .anyRequest().hasAnyRole("ADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 5
                //인증된 사용자가 권한 부족 등의 사유로 접근이 거부되었을때 작동할 핸들러
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 6
                // 인증되지 않은 사용자의 접근이 거부되었을 때 작동할 핸들러
                .and() // 7
                .addFilterBefore(new JwtAuthenticationFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
                // 토큰으로 사용자 인증하기 위해 직접 정의한 JwtAuthenticationFIlter를
                // UsernamePasswordAuthenticationFilter의 이전 위치에 등록!
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
