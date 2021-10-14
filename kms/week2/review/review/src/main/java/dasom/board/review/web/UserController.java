package dasom.board.review.web;

import dasom.board.review.Config.JwtTokenProvider;
import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/user")
    public String signup(UserInfoDto infoDto,HttpServletRequest httpServletRequest){
        //String token = jwtTokenProvider.createToken(infoDto.getEmail(), infoDto.getAuth());
        //System.out.println("Token = !!!! " + token);

        /*
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String hname = "";
        String hvalue = "";
//        while(headerNames.hasMoreElements()){
//            hname = headerNames.nextElement();
//            hvalue = httpServletRequest.getHeader(hname);
//            System.out.println("headerName : " +hname);
//            System.out.println("headerValue : " + hvalue);
//        }
//        System.out.println(infoDto.getEmail() + infoDto.getAuth() + "infoDto!!!" + infoDto.getPassword());
        */

        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginview(Model model){
        model.addAttribute("userdto",new UserInfoDto());
        return "/login";
    }


    @PostMapping("/loginProcess")
    public String loginpro(UserInfoDto userInfoDto, HttpServletResponse response){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        UserInfo userInfo = userService.loadUserByUsername(userInfoDto.getEmail());
        if(encode.matches(userInfoDto.getPassword(),userInfo.getPassword())==false){
            System.out.println("login process error");
            return "redirect:/login";
        }

        String token = jwtTokenProvider.createToken(userInfo.getEmail(), userInfo.getAuth());
        System.out.println("login token = " + token);
        System.out.println("userInfo.getEmail() = " + userInfo.getEmail());
        response.setHeader("X-AUTH-TOKEN",token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN",token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        System.out.println("로그인 성공");
        return "redirect:/main";

    }

    @GetMapping(value = "/main")
    public String mainpage(Model model, HttpServletRequest httpServletRequest ){
        String token = jwtTokenProvider.jwtgetToken(httpServletRequest);
        String userEmail = jwtTokenProvider.getUserPk(token);
        UserInfo userInfo = userService.loadUserByUsername(userEmail);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(userInfo.getUsername());
        userInfoDto.setAuth(userInfo.getAuth());

        model.addAttribute("userdto", userInfoDto);
        return "/main";
    }

    //코드 리뷰 하자.
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }


    // 예전 코드

//     @GetMapping(value = "/members/new")
//    public String createForm(Model model){
//        model.addAttribute("memberForm",new MemberForm());
//        return "members/createMemberForm";
//    }

//    @PostMapping(value="/members/new")
//    public String create(@Valid MemberForm form, BindingResult result){

//        if(result.hasErrors()){
//            return "members/createMemberForm";
//        }

//        UserInfo member = new UserInfo();
//        member.setName(form.getName());

//        memberService.join(member);

//        return "redirect:/";
//    }

//    @GetMapping(value = "/members")
//    public String list(Model model){
//        List<UserInfo> members = memberService.findMembers();
//        model.addAttribute("members",members);
//        return "members/memberList";
//    }
}
