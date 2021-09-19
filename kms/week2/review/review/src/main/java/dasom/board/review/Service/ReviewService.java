package dasom.board.review.Service;

import dasom.board.review.Domain.Review;
import dasom.board.review.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(Review review){
        reviewRepository.save(review);
    }

    public List<Review> findReview(){
        return reviewRepository.findAll();
    }

    public Review findOne(Long reviewId){
        return reviewRepository.findOne(reviewId);
    }
}
