package com.security.app.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Appliction : Backend Cource", 
				"This project is develop by learn code  with Suresh", 
				"1.0", "Terms of Service", new Contact("Suresh", 
						"https://", "suresh.kumar8409@gmail.com"), 
				"Licence of Apis", "API licence Url",Collections.emptyList());
	}
	
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	
	private List<SecurityReference> sf(){
		AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
    /*@Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(false)
            .apiInfo(new ApiInfoBuilder()
                .title("NEOM PAY Customer REST API")
                .description("NEOM PAY Customer REST Service")
                .version("1.0")
                .build())
               .globalRequestParameters(getGlobalParameterList())
            .tags(new Tag(ServiceTags.CUSTOMER_SERVICE, "Customer service"),
                    new Tag(ServiceTags.RAYAH_SERVICE, "Rayah service"),
                    new Tag(ServiceTags.CACHE_SERVICE, "Cache service"))
            .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }



    private List<RequestParameter> getGlobalParameterList() {
        return Stream.concat(
                        ApiHeaderConstants.getOptionalApiHeaders().stream()
                                .map(header -> new RequestParameterBuilder()
                                        .name(header)
                                        .in(ParameterType.HEADER)
                                        .required(false)
                                        .build()),
                        ApiHeaderConstants.getMandatoryApiHeaders().stream()
                                .map(header -> new RequestParameterBuilder()
                                        .name(header)
                                        .in(ParameterType.HEADER)
                                        .required(true)
                                        .build()))
                .collect(Collectors.toList());
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ServiceTags {
        public static final String CUSTOMER_SERVICE = "Customer Service";
        public static final String RAYAH_SERVICE="Rayah Service";
        public static final String CACHE_SERVICE="Cache Service";
    }*/
}