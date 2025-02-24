package com.example.briefingapi.security.handler.annotation;

import com.example.briefingapi.member.implement.MemberQueryAdapter;
import com.example.briefingapi.security.provider.TokenProvider;
import com.example.briefingcommon.entity.Member;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberQueryAdapter memberQueryAdapter;
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthMember authUser = parameter.getParameterAnnotation(AuthMember.class);
        if (authUser == null) return false;
        if (parameter.getParameterType().equals(Member.class) == false) {
            return false;
        }
        return true;
    }

    /*
        NOTE - 사용자 추출 방법 변경
        기존) 스프링 시큐리티 컨텍스트에서 유저정보를 가져와 세팅해주었습니다.
        현재) httpRequest의 authorization header에서 액세스 토큰을 가져와 사용자 정보를 반환합니다.
        변경 배경)
        permitAll()을 통해 인증 유무에 상관없이 제공되는 API에서 JwtRequestFilter까지 도달하지 않는 문제인해
        authentication 객체가 세팅이 되지 않기 때문에 기존 방법으로는 토큰을 물고 있는 사용자라도 AuthMember를 통해 추출하면 null이 반환되었습니다.
    */
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String jwt = tokenProvider.resolveToken(request);
        if (StringUtils.hasText(jwt)
                && tokenProvider.validateToken(jwt, TokenProvider.TokenType.ACCESS)) {
            // 토큰에서 사용자 ID (subject) 추출
            String userId = tokenProvider.getAuthentication(jwt).getName();
            return memberQueryAdapter.findById(Long.valueOf(userId));
        }
        return null; // 토큰이 없거나 유효하지 않은 경우
    }
}
