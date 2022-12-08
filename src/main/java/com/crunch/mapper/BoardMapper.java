package com.crunch.mapper;

import com.crunch.domain.BoardDTO;

public interface BoardMapper {

    int boardSelectCount();

    BoardDTO boardSelectList();

    void boardInsert(BoardDTO boardDTO);

    BoardDTO boardSelectNotice();

    void boardHit(int postID);

    BoardDTO boardSelectByPostID(int postID);

    void boardDelete(int postID);

    void boardUpdate(BoardDTO boardDTO);

}
