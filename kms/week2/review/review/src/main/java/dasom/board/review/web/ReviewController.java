package dasom.board.review.web;

import dasom.board.review.Config.JwtTokenProvider;
import dasom.board.review.DTO.BoardDTO;
import dasom.board.review.DTO.ReviewDTO;
import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.Board;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Service.BoardService;
import dasom.board.review.Service.UserService;
import dasom.board.review.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BoardService boardService;

    @GetMapping("/reviews")
    public String menu(Model model) {
        //DB에 저장된 회원정보 다 가져옴.
        List<Board> boards = boardService.findallBoard();

        //정보를 가지고 새로운 boardDTO객체를 만듦.
        List<BoardDTO> boardDTOs = new ArrayList<>();
        for(Board board : boards){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setCode(board.getId());
            boardDTO.setId(board.getUserInfo().getEmail());
            boardDTO.setTitle(board.getReview().getTitle());
            boardDTO.setComment(board.getReview().getComment());
            boardDTO.setNow(board.getLocalDateTime());
            boardDTOs.add(boardDTO);
        }
        model.addAttribute("boards",boardDTOs);
        return "Menu/menu";
    }

    @GetMapping(value = "/reviews/new")
    public String createForm(Model model, HttpServletRequest httpServletRequest) {
        String userPk = jwtTokenProvider.getUserPk(jwtTokenProvider.jwtgetToken(httpServletRequest));
        UserInfo userInfo = userService.loadUserByUsername(userPk);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(userInfo.getEmail());


        model.addAttribute("reviewform", new ReviewDTO());
        model.addAttribute("userdto",userInfoDto);
        return "reviews/createReviewForm";
    }

    @PostMapping(value = "/reviews/new")
    public String create(@Valid ReviewDTO reviewDTO, HttpServletRequest httpServletRequest) {
        System.out.println("들어왔습니다.");

        //대충 BaordDB에 저장하는 내용.
        System.out.println(reviewDTO.getComment() + reviewDTO.getTitle());
        Long reviewcode = reviewService.saveReview(reviewDTO);

        boardService.saveboard(reviewcode, httpServletRequest);

        return "redirect:";
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