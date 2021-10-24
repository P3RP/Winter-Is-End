package dasom.board.review.Repository;

import dasom.board.review.Domain.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

//스프링 빈으로 등록, JPA 예외를 스프링 기반 예외로 예외 변환 -> 무슨 소리임?ㅋㅋ
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByEmail(String email);
    // 옛날 코드






    // 엔티티 매니저 주입
//    @PersistenceContext
//    EntityManager em;

    /*
    DB에 member 객체 저장
     */
//    public void save(UserInfo member){
//        em.persist(member);
//    }

    /*
    한 명의 멤버 객체를 찾는다.
     */
//    public UserInfo findOne(Long id){
//        return em.find(UserInfo.class,id);
//    }

    /*
    멤버 객체 전부 찾는다.
     */

//    public List<UserInfo> findAll(){
//        return em.createQuery("select m from UserInfo m", UserInfo.class)
//                .getResultList();
//    }


    /*
    이름으로 찾을 것.
     */
//    public List<UserInfo> findByName(String name){
//        return em.createQuery("select m from UserInfo m where m.name = :name", UserInfo.class)
//                .setParameter("name",name)
//                .getResultList();
//    }

}
