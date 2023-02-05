package com.api.djobs.config;

import com.api.djobs.jwt.exception.CustomAuthenticationEntryPoint;
import com.api.djobs.jwt.filter.JwtFilter;
import com.api.djobs.jwt.provider.TokenProvider;
import com.api.djobs.user.repository.RefreshTokenRepository;
import com.api.djobs.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

// Spring Security 설정.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final TokenProvider tokenProvider;
    private final CustomAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

//    private final CustomOAuth2UserService oAuth2UserService;

    private final ObjectMapper objectMapper;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

//    private final RedisUtil redisUtil;

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public JwtFilter tokenAuthenticationFilter() {
        return new JwtFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */

//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }


    /*
     * Oauth 인증 성공 핸들러
     * */
//    @Bean
//    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
//        return new OAuth2AuthenticationSuccessHandler(
//                tokenProvider,
//                appProperties,
//                refreshTokenRepository,
//                oAuth2AuthorizationRequestBasedOnCookieRepository(),
//                redisUtil
//        );
//    }

    /*
     * Oauth 인증 실패 핸들러
     * */
//    @Bean
//    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
//        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
//    }
    /*
     * Cors 설정
     * */



    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico", "/v2/api-docs", "/swagger-resources/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger*/**");

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
                .cors()

                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/auth/**/**", "/user/**/**", "/chat/**", "/todos/**/**", "/mail/**", "/oauth/**","/like/**/**", "/edits/**","/todonoti/**/**/**","login/oauth2/**/**",
                        "/auth/**", "/user/**/**", "/chat/**", "/todos/**/**", "/mail/**", "/todoreply/**/**", "/department", "/team", "/teams", "/upload/**/**", "/files/**/**/**").permitAll()
                .anyRequest().authenticated().and().build();

//                .apply(new JwtSecurityConfig(tokenProvider));
    }

    // 암호를 암호화하거나, 사용자가 입력한 암호가 기존 암호랑 일치하는지 검사할 때 이 Bean을 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
