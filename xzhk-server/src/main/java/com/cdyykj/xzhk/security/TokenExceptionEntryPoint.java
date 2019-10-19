package com.cdyykj.xzhk.security;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.commons.result.JsonResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token失效异常捕获
 */
@Component
public class TokenExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
   // private Logger logger = LoggerFactory.getLogger(BootOAuth2AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if (e.getCause() instanceof InvalidTokenException) {
            response.getWriter().write(JsonUtils.beanToJson(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_416,"token失效")));
        }else {
            response.getWriter().write(JsonUtils.beanToJson(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_417,"无权访问")));
        }
    }
}
