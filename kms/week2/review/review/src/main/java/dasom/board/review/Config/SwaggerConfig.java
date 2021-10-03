package dasom.board.review.Config;

import com.google.common.net.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String API_NAME = "User API";
    private static final String API_Version="0.0.1";
    private static final String API_DESCRIPTION= "User API 명세서";

    @Bean
    public Docket api(){
        // API 테스트할 때 모든 API에 전역 파라미터를 설정.
        Parameter parameterBuilder = new ParameterBuilder()
                .name(HttpHeaders.AUTHORIZATION)
                .description("Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> globalParameters = new ArrayList<>();
        globalParameters.add(parameterBuilder);

        //Swagger를 적용할 클래스의 package명
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParameters)
                .apiInfo(apiinfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("dasom.board.review"))
                .paths(PathSelectors.any())
                .build();


    }

    //API에 대한 정보
    private ApiInfo apiinfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_Version)
                .description(API_DESCRIPTION)
                .build();
    }
}
