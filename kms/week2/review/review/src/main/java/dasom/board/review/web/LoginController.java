package dasom.board.review.web;

import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model){
        System.out.println("로그인 화면에 들어옴");
        List<UserInfo> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "home";
    }

}
*/