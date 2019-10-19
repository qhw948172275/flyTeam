package com.cdyykj.xzhk.controller.api.commons;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("defaultAuthorizationServerTokenServices")
    AuthorizationServerTokenServices authorizationServerTokenServices;

    @ApiOperation(value = "用户名密码获取token")
    @PostMapping("/oauth/user/token")
    public void getUserTokenInfo(
            @ApiParam(required = true, name = "username", value = "账号") @RequestParam(value = "username") String username,
            @ApiParam(required = true, name = "password", value = "密码") @RequestParam(value = "password") String password,
            HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getHeader("client_id");
        String clientSecret = request.getHeader("client_secret");

        try {

            if (clientId == null || "".equals(clientId)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_id信息");
            }

            if (clientSecret == null || "".equals(clientSecret)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_secret信息");
            }

            ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
            } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
            }


            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(),
                    "password");

            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            UsernamePasswordAuthenticationToken clientToken = new UsernamePasswordAuthenticationToken(clientId, password);
            SecurityContextHolder.getContext().setAuthentication(clientToken);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);


            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
                    .createAccessToken(oAuth2Authentication);

            oAuth2Authentication.setAuthenticated(true);
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            json.putPOJO("token",oAuth2AccessToken);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.beanToJson(JsonResultUtils.buildJsonOK(json)));
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
