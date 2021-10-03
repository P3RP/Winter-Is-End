package dasom.board.review.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "board")
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userinfo_code")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
