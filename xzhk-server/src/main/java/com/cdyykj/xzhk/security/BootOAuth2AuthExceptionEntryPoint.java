package com.cdyykj.xzhk.security;

import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.commons.result.JsonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 用户登录异常捕获
 */
@Component
public class BootOAuth2AuthExceptionEntryPoint implements WebResponseExceptionTranslator {

    private Logger logger = LoggerFactory.getLogger(BootOAuth2AuthExceptionEntryPoint.class);


    public ResponseEntity translate(Exception e) throws Exception {
        if(e instanceof  UsernameNotFoundException){
            return ResponseEntity.ok(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_414,e.getMessage()));
        }
        if(e instanceof InvalidGrantException){
            return ResponseEntity.ok(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_415,"密码错误"));
        }
        if (e instanceof OAuth2Exception) {

            return ResponseEntity.ok(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_500,"程序异常"));
        }
        if(e instanceof org.springframework.security.authentication.InternalAuthenticationServiceException){
            return ResponseEntity.ok(JsonResultUtils.buildJsonFail(SystemConstants.ERROR_CODE_418,e.getMessage()));
        }
        throw e;
    }

}

