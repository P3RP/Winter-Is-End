package dasom.board.review.web;

import dasom.board.review.Domain.Member;
import dasom.board.review.Domain.Review;
import dasom.board.review.Service.MemberService;
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
    private final MemberService memberService;

    @GetMapping(value = "/reviews/new")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members",members);
        model.addAttribute("reviewform",new ReviewForm());
        return "reviews/createReviewForm";
    }

    @PostMapping(value="/reviews/new")
    public String reviewPost(@Valid ReviewForm reviewForm, BindingResult result,@RequestParam("memberId") Long memberId){
        if(result.hasErrors()){
            return "reviews/createReviewForm";
        }
        System.out.println("hello" + memberService.findOne(memberId).getName() + reviewForm.getTitle());
        return "redirect:/";
    }


}
