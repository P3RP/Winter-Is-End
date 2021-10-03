package dasom.board.review.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvcConfigurer 공부
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/signup").setViewName("signup");
    }
}
