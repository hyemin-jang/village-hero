package kr.pe.villagehero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerConfig {
	@Bean
    public Docket swaggerApi() {
    	
    	//Docket : Swagger 설정의 핵심이 되는 Bean
    	//apiInfo() : 제목, 설명 등 문서에 대한 정보들을 보여주기 위해 호출
    	//apis() : 컨트롤러가 존재하는 패키지를 basepackage로 지정하여, 애노테이션이 선언된 API를 문서화
    	//useDefaultResponseMessages(false) : false로 설정한 경우 컨트롤러에 
    	//@ApiResponse로 선언된 status에 한해서만 문서에 표현
    	//true로 설정한 경우 401/403 등의 status 값도 문서에 표현됨
        return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(ApiIgnore.class)
        		.apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("kr.pe.villagehero.controller"))
                .build()
                .useDefaultResponseMessages(false); 
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Village Hero 사용을 위한 Doc 입니다")
                .description("테스트")
                .license("license : playdata").licenseUrl("http://www.google.com")
                .version("1").build();
    }
}
