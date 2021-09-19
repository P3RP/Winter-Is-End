package dasom.board.review.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewForm {
    private Long id;

    private String title;
    private String comment;
}
