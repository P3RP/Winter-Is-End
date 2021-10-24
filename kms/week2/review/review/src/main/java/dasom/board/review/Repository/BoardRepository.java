package dasom.board.review.Repository;

import dasom.board.review.Domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Board board){
        //System.out.println("크크크크루" + board.getReview().getTitle() + board.getUserInfo().getEmail());
        em.persist(board);
    }

    public List<Board> findAll(){
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }
}
