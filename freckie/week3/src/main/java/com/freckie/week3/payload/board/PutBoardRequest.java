package com.freckie.week3.payload.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Optional;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PutBoardRequest {
    private Optional<Integer> readRole;
    private Optional<Integer> writeRole;
    private Optional<String> name;
}
