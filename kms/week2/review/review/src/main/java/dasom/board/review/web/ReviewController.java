package dasom.board.review.web;

import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.UserService;
import dasom.board.review.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;



@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/reviews")
    public String menu(){
        return "Menu/menu";
    }

    @GetMapping(value = "/reviews/new")
    public String createForm(Model model){
        //List<UserInfo> members = userService.findMembers();

        //로그인 정보 서버에서 가지고 있겠지
        model.addAttribute("reviewform",new ReviewForm());
        return "reviews/createReviewForm";
    }

    @PostMapping(value = "/reviews/new")
    public String create(@Valid ReviewForm reviewForm, BindingResult result){
        System.out.println("들어왔습니다.");

        //대충 BaordDB에 저장하는 내용.
        System.out.println(reviewForm.getComment() + reviewForm.getTitle());
        return "redirect:Menu/menu";
    }

    /*
    @PostMapping(value="/reviews/new")
    public String reviewPost(@Valid ReviewForm reviewForm, BindingResult result,@RequestParam("memberId") Long memberId){
        if(result.hasErrors()){
            return "reviews/createReviewForm";
        }
        System.out.println("hello" + userService.findOne(memberId).getName() + reviewForm.getTitle());
        return "redirect:/";
    }
     */


}
