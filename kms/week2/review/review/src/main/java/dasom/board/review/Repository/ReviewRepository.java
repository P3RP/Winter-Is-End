package dasom.board.review.Repository;

import dasom.board.review.Domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review){
        if(review.getId() == null){
            em.persist(review);
        } else{
            em.merge(review);
        }
    }

    public Review findOne(Long id){
        return em.find(Review.class,id);
    }

    public List<Review> findAll(){
        return em.createQuery("select i from Review i",Review.class).getResultList();
    }
}
