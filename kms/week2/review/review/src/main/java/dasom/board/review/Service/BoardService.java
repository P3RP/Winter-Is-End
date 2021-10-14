package dasom.board.review.Service;

import dasom.board.review.Config.JwtTokenProvider;
import dasom.board.review.DTO.BoardDTO;
import dasom.board.review.DTO.ReviewDTO;
import dasom.board.review.DTO.UserInfoDto;
import dasom.board.review.Domain.Board;
import dasom.board.review.Domain.Review;
import dasom.board.review.Domain.UserInfo;
import dasom.board.review.Repository.BoardRepository;
import dasom.board.review.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;
    public void saveboard(Long reviewcode, HttpServletRequest httpServletRequest){
        String token = jwtTokenProvider.jwtgetToken(httpServletRequest);
        String userPk = jwtTokenProvider.getUserPk(token);
        UserInfo userInfo = userService.loadUserByUsername(userPk);

        //리뷰 객체 찾기
        Review review = reviewService.findOne(reviewcode);

        boardRepository.save(Board.builder()
                .userInfo(userInfo)
                .review(review)
                .build());
    }

    //board 객체 순서대로 다 가져옴.
    public List<Board> findallBoard(){
        return boardRepository.findAll();
    }
}
