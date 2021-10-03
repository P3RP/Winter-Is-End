package dasom.board.review.web;

import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserInfoDto infoDto){
        System.out.println(infoDto.getEmail() + infoDto.getAuth() + "infoDto!!!" + infoDto.getPassword());
        userService.save(infoDto);
        return "redirect:/login";
    }

    //코드 리뷰 하자.
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
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
