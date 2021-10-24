package dasom.board.review.Config;

import dasom.board.review.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    //암호화에 필요한 PasswordEncoder를 Bean에 등록
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //authenticationManager를 Bean으로 등록
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //인증을 무시할 경로 css js img는 무조건 접근해야하므로 제외
    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/img/**","/css/**","/js/**","/h2-console/**");
    }

    //http 관련 인증
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable() //csrf보안 토큰 disable 처리
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증으로 세션 사용x
            .and()
                .authorizeRequests()
                .antMatchers("/login","/signup","/user","/reviews/**","/loginProcess").permitAll() //누구나 접근 허용
                .antMatchers("/").hasRole("USER") //USER, ADMIN만 접근 가능.
                .antMatchers("/admin","/swagger-ui.html").hasRole("ADMIN") //Admin만 접근 가능.
                //.anyRequest().authenticated() //나머지 요청들은 권한의 종류에 상관없이 권한이 있어야 접근 가능.
            .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 설정.
//                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/") // 로그인 성공시 라다이렉션 페이지
            .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉션 페이지
                .invalidateHttpSession(true) // 세션 날리기
            .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //memberService에서 implements해서 loadUserByUsername()을 구현해야한다.
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}
