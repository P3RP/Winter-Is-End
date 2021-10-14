package dasom.board.review.DTO;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
public class BoardDTO {

    private Long code;
    private String id;

    private String title;
    private String comment;
    private String now;
}
