package com.cdyykj.xzhk.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createRestApiForSchool() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("platform")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cdyykj.xzhk.controller.api.platform"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket createRestApiForSystem() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("wechat-MP")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cdyykj.xzhk.controller.api.wechat"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemesWx())
                .securityContexts(securityContextsWx());

    }
    @Bean
    public Docket createRestApiForCommons() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("commons")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cdyykj.xzhk.controller.api.commons"))
                .paths(PathSelectors.any())
                .build();

    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("西藏航空企业号开发 Swagger2 构建 RESTful API")
                .version("1.0")
                .description("API 描述")
                .build();
    }
//
//    /**
//     * 这个类决定了你使用哪种认证方式，这里使用密码模式
//     */
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/console" + "/oauth/token");
//
//        return new OAuthBuilder()
//                .name("spring_oauth")
//                .grantTypes(Collections.singletonList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//    }
//
//    /**
//     * 这里设置 swagger2 认证的安全上下文
//     */
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
//                .forPaths(PathSelectors.any())
//                .build();
//    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");
        return authorizationScopes;
    }


    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    private List<ApiKey> securitySchemesWx() {
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContextsWx() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuthWx())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("all", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    List<SecurityReference> defaultAuthWx() {
        AuthorizationScope authorizationScope = new AuthorizationScope("all", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
    }

}
