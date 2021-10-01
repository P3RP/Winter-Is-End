package com.freckie.week3.payload.board;

import com.freckie.week3.model.BoardDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardListResponse {
    private ArrayList<BoardDTO> boards;
}

