package dasom.board.review.Domain;

import dasom.board.review.DTO.ReviewDTO;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "board")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userinfo_code")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    //LocalDate는 날짜까지만, LocalDateTime은 시간까지
    @Column(name = "date_time")
    private String localDateTime;

    @Builder
    public Board(UserInfo userInfo, Review review){
        this.userInfo = userInfo;
        this.review = review;
        LocalDateTime localDateTime= LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");

        this.localDateTime = localDateTime.format(dateTimeFormatter);
    }

}
